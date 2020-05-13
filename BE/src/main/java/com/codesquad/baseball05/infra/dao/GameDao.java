package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.game.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Repository
public class GameDao {

    private final JdbcTemplate jdbcTemplate;


    public GameDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //MATCHES 테이블(클래스)
    //- aUserId -> null
    //- bUserId -> null
    //
    //USER 클래스
    //- tean_id -> null

    //하나의 쿼리로 하고 싶었지만 변경해야 하는 team_id는 2개인데 한 번에 2개 다 변경이 안됨
    //a_user_id, b_user_id, team_id 중 위에 있는 거 총 3개가 null 값이 되면
    //연결이 다 끊어져서 남은 team_id 1개가 null 값으로 설정 불가
    //그래서 2번 쿼리 돌림
    public void terminateGame(Long gameId) {
        disconnectTeamWithUser(gameId);
        disconnectMatchWithUser(gameId);
    }

    public Object ready() {
        return null;
    }

    public Object pitch(UserMatchesDTO userMatchesDTO) {
        GameTeamDTO homeTeam = makeTeamDTO("a", "m_home_team");
        GameTeamDTO awayTeam = makeTeamDTO("b", "m_away_team");
        CurrentPlayerDTO players = new CurrentPlayerDTO(makePitcherDTO(), makeBatterDTO());
        return new PitchResultDTO(homeTeam, awayTeam, players);
    }


    private GameTeamDTO makeTeamDTO(String user, String team) {
        String sql = "SELECT m.home_team AS m_home_team, " +
                "m.away_team AS m_away_team, " +
                "i.half AS i_half, " +
                "h1.point AS h1_point, " +
                "h2.point AS h2_point " +
                "FROM matches m " +
                "INNER JOIN user u ON m." + user + "_user_id = u.id " +
                "INNER JOIN game g ON m.id = g.matches_id " +
                "INNER JOIN inning i ON g.id = i.game_id " +
                "LEFT OUTER JOIN half h1 ON i.first_half_id = h1.id " +
                "LEFT OUTER JOIN half h2 ON i.second_half_id = h2.id";

        RowMapper<GameTeamDTO> pitchResultDtoRowMapper = (rs, rowNum) -> {
            String teamName = rs.getString(team);
            return new GameTeamDTO(teamName, getTotalPoint(rs), isOffense(rs, teamName));
        };

        return this.jdbcTemplate.queryForObject(sql, pitchResultDtoRowMapper);
    }

    private GamePitcherDTO makePitcherDTO() {
        String sql = "";

        RowMapper<GamePitcherDTO> pitcherRowMapper = (rs, rowNum) -> {
            isOffense(rs, rs.getString("m_home_team"));
            return null;
        };

        return null;
//        return this.jdbcTemplate.queryForObject(sql, pitcherRowMapper);
    }

    private GameBatterDTO makeBatterDTO() {
        String sql = "";

        RowMapper<GameBatterDTO> batterRowMapper = new RowMapper<GameBatterDTO>() {
            @Override
            public GameBatterDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return null;
            }
        };

        return null;
//        return this.jdbcTemplate.queryForObject(sql, batterRowMapper);
    }

    private boolean isTopHalf(Long matchesId) throws SQLException {
        String sql = "SELECT i.half FROM matches m INNER JOIN game g ON m.id = g.matches_id " +
                "INNER JOIN inning i ON g.id = i.game_id WHERE m.id = ?";

        RowMapper<String> rowMapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("i.half");
            }
        };
        
        return this.jdbcTemplate.queryForObject(sql, new Object[]{matchesId}, rowMapper).equals("초");
    }

    private int getTotalPoint(ResultSet rs) throws SQLException {
        return isTopHalf(rs) ? rs.getInt("h1_point") : rs.getInt("h2_point");
    }

    private boolean isOffense(ResultSet rs, String teamName) throws SQLException {
        if (isTopHalf(rs) && rs.getString("m_away_team").equals(teamName)) {
            return true;
        } else return !isTopHalf(rs) && rs.getString("m_home_team").equals(teamName);
    }

    private int disconnectTeamWithUser(Long gameId) {
        String sql = "UPDATE matches m INNER JOIN user u ON m.a_user_id = u.id OR m.b_user_id = u.id " +
                "SET u.team_id = null " +
                "WHERE m.id = ?";

        return this.jdbcTemplate.update(sql, gameId);
    }

    private int disconnectMatchWithUser(Long gameId) {
        String sql = "UPDATE matches m " +
                "SET m.a_user_id = null, b_user_id = null " +
                "WHERE m.id = ?";

        return this.jdbcTemplate.update(sql, gameId);
    }
}
