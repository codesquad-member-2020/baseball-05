package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.game.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public Object pitch() {
        return null;
    }

    public Object readPitchResult(String team) {
        String sql = "SELECT m.home_team AS m_home_team, " +
                "m.away_team AS m_away_team, " +
                "i.half _ AS i_half, " +
                "h1.point AS h1_point, " +
                "h2.point AS h2_point " +
                "FROM matches m " +
                "INNER JOIN user u ON m.a_user_id = u.id " +
                "INNER JOIN game g ON m.id = g.matches_id " +
                "INNER JOIN inning i ON g.id = i.game_id " +
                "LEFT OUTER JOIN half h1 ON i.first_half_id = h1.id " +
                "LEFT OUTER JOIN half h2 ON i.second_half_id = h2.id " +
                "INNER JOIN plate p ON h1.id = p.half_id OR h2.id = p.half_id";

        RowMapper<PitchResultDTO> pitchResultDtoRowMapper = new RowMapper<PitchResultDTO>() {
            @Override
            public PitchResultDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                String teamName = rs.getString(team);
                GameTeamDTO teamDTO = new GameTeamDTO(teamName, getTotalPoint(rs), isOffense(rs, teamName));
                PlayerDTO pitcher = new PlayerDTO();
                PlayerDTO batter = new PlayerDTO();
                InningDTO inning = new InningDTO();
                List<PlateDTO> plates = new ArrayList<>();
                return new PitchResultDTO(homeTeam, awayTeam, pitcher, batter, inning, plates);
            }
        };

        return this.jdbcTemplate.queryForObject(sql, pitchResultDtoRowMapper);
    }

    private boolean isTopHalf(ResultSet rs) throws SQLException {
        String half = rs.getString("i_half");
        return half.equals("초");
    }

    private int getTotalPoint(ResultSet rs) throws SQLException {
        return isTopHalf(rs) ? rs.getInt("h1_point") : rs.getInt("h2_point");
    }

    private boolean isOffense(ResultSet rs, String teamName) throws SQLException {
        if(isTopHalf(rs) && rs.getString("m_away_team").equals(teamName)) {
            return true;
        } else if(!isTopHalf(rs) && rs.getString("m_home_team").equals(teamName)) {
            return true;
        }
        return false;
    }

    private void disconnectTeamWithUser(Long gameId) {
        String sql = "UPDATE matches m INNER JOIN user u ON m.a_user_id = u.id OR m.b_user_id = u.id " +
                "SET u.team_id = null " +
                "WHERE m.id = " + gameId;

        this.jdbcTemplate.execute(sql);
    }

    private void disconnectMatchWithUser(Long gameId) {
        String sql = "UPDATE matches m " +
                "SET m.a_user_id = null, b_user_id = null " +
                "WHERE m.id = " + gameId;

        this.jdbcTemplate.execute(sql);
    }
}
