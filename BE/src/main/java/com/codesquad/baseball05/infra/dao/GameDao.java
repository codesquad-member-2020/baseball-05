package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.game.dto.*;
import com.codesquad.baseball05.domain.matches.entity.Matches;
import com.codesquad.baseball05.domain.team.entity.Player;
import com.codesquad.baseball05.domain.team.entity.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.rmi.ServerError;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public Object pitch(UserMatchesDTO userMatchesDTO) {
        Matches matches = userMatchesDTO.getMatches();

        GameTeamDTO homeTeam = makeTeamDTO(userMatchesDTO.getUserB(), matches);
        GameTeamDTO awayTeam = makeTeamDTO(userMatchesDTO.getUserA(), matches);

        if(isHomeTeam(userMatchesDTO.getUserA(), matches)) {
            homeTeam = makeTeamDTO(userMatchesDTO.getUserA(), matches);
            awayTeam = makeTeamDTO(userMatchesDTO.getUserB(), matches);
        }

        CurrentPlayerDTO players = new CurrentPlayerDTO(makePitcherDTO(userMatchesDTO), makeBatterDTO(userMatchesDTO));
        return new PitchResultDTO(homeTeam, awayTeam, players);
    }


    private GameTeamDTO makeTeamDTO(UserTeamDTO userTeamDTO, Matches matches) {
        boolean isHomeTeam = isHomeTeam(userTeamDTO, matches);

        String sql = "SELECT i.half AS i_half, " +
                "h2.point AS h2_point " +
                "FROM matches m " +
                "INNER JOIN game g ON m.id = g.matches_id " +
                "INNER JOIN inning i ON g.id = i.game_id " +
                "LEFT OUTER JOIN half h2 ON i.second_half_id = h2.id";;

        if(isHomeTeam) {
            sql = "SELECT i.half AS i_half, " +
                    "h1.point AS h1_point " +
                    "FROM matches m " +
                    "INNER JOIN game g ON m.id = g.matches_id " +
                    "INNER JOIN inning i ON g.id = i.game_id " +
                    "LEFT OUTER JOIN half h1 ON i.first_half_id = h1.id";
        }

        RowMapper<GameTeamDTO> pitchResultDtoRowMapper = (rs, rowNum) -> {
            String teamName = userTeamDTO.getTeam().getName();
            return new GameTeamDTO(teamName, getTotalPoint(rs), isOffense(rs, isHomeTeam));
        };

        return this.jdbcTemplate.queryForObject(sql, pitchResultDtoRowMapper);
    }

    private boolean isHomeTeam(UserTeamDTO userTeamDTO, Matches matches) {
        String homeTeam = matches.getHomeTeam();
        String userTeam = userTeamDTO.getTeam().getName();
        return homeTeam.equals(userTeam);
    }

    private GamePitcherDTO makePitcherDTO(UserMatchesDTO userMatchesDTO) {
        String sql = "SELECT i.half AS i_half " +
                "FROM matches m " +
                "INNER JOIN game g ON m.id = g.matches_id " +
                "INNER JOIN inning i ON g.id = i.game_id";

        RowMapper<Boolean> pitcherRowMapper = new RowMapper<Boolean>() {
            @Override
            public Boolean mapRow(ResultSet rs, int rowNum) throws SQLException {
                return isOffense(rs, isHomeTeam(userMatchesDTO.getUserA(), userMatchesDTO.getMatches()));
            }
        };

        Boolean isOffense = this.jdbcTemplate.queryForObject(sql, pitcherRowMapper);

        List<Player> players = isOffense ? userMatchesDTO.getUserA().getPlayers() : userMatchesDTO.getUserB().getPlayers();

        Player player = players.stream().filter(p -> p.getIsPitcher()).findFirst().orElseThrow(() -> new RuntimeException("투수가 없습니다!"));

        List<Record> records = player.getRecords();

        return new GamePitcherDTO(player.getName(), records.get(records.size()-1).getPitch());
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

    private boolean isTopHalf(ResultSet rs) throws SQLException {
        return rs.getString("i_half").equals("초");
    }

    private int getTotalPoint(ResultSet rs) throws SQLException {
        return isTopHalf(rs) ? rs.getInt("h1_point") : rs.getInt("h2_point");
    }

    private boolean isOffense(ResultSet rs, boolean isHomeTeam) throws SQLException {
        if (isTopHalf(rs) && !isHomeTeam) {
            return true;
        } else return !isTopHalf(rs) && isHomeTeam;
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
