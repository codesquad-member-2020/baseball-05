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

        GameTeamDTO homeTeam = null;
        GameTeamDTO awayTeam = null;

        if(isHomeTeam(userMatchesDTO.getUserA(), matches)) {
            homeTeam = makeTeamDTO(userMatchesDTO.getUserA(), matches);
            awayTeam = makeTeamDTO(userMatchesDTO.getUserB(), matches);
        }

        else {
            homeTeam = makeTeamDTO(userMatchesDTO.getUserB(), matches);
            awayTeam = makeTeamDTO(userMatchesDTO.getUserA(), matches);
        }

        UserMatchesDTO verifiedHomeTeam = verifiedHomeTeam(userMatchesDTO, homeTeam);

        CurrentPlayerDTO players = new CurrentPlayerDTO(makePitcherDTO(verifiedHomeTeam), makeBatterDTO(verifiedHomeTeam));
        return new PitchResultDTO(homeTeam, awayTeam, players);
    }

    private UserMatchesDTO verifiedHomeTeam(UserMatchesDTO userMatchesDTO, GameTeamDTO homeTeam) {
        Matches matches = userMatchesDTO.getMatches();

        if(isHomeTeam(userMatchesDTO.getUserA(), matches)) {
            userMatchesDTO.getUserA().setHome(true);

            userMatchesDTO.getUserA().setOffense(homeTeam.isOffense());
            userMatchesDTO.getUserB().setOffense(!homeTeam.isOffense());
        }

        else {
            userMatchesDTO.getUserB().setHome(true);

            userMatchesDTO.getUserA().setOffense(!homeTeam.isOffense());
            userMatchesDTO.getUserB().setOffense(homeTeam.isOffense());
        }

        return userMatchesDTO;
    }

    private GameTeamDTO makeTeamDTO(UserTeamDTO userTeamDTO, Matches matches) {
        String sql = "SELECT i.half AS i_half, " +
                "h1.point AS h1_point, h2.point AS h2_point " +
                "FROM matches m " +
                "INNER JOIN game g ON m.id = g.matches_id " +
                "INNER JOIN inning i ON g.id = i.game_id " +
                "LEFT OUTER JOIN half h1 ON i.first_half_id = h1.id " +
                "LEFT OUTER JOIN half h2 ON i.second_half_id = h2.id";

        RowMapper<GameTeamDTO> pitchResultDtoRowMapper = (rs, rowNum) -> {
            String teamName = userTeamDTO.getTeam().getName();
            return new GameTeamDTO(teamName, getTotalPoint(rs), isOffense(rs, isHomeTeam(userTeamDTO, matches)));
        };

        return this.jdbcTemplate.queryForObject(sql, pitchResultDtoRowMapper);
    }

    private boolean isHomeTeam(UserTeamDTO userTeamDTO, Matches matches) {
        String homeTeam = matches.getHomeTeam();
        String userTeam = userTeamDTO.getTeam().getName();
        return homeTeam.equals(userTeam);
    }

    private GamePitcherDTO makePitcherDTO(UserMatchesDTO verifiedHomeTeam) {
        List<Player> players = verifiedHomeTeam.getUserA().isOffense() ? verifiedHomeTeam.getUserA().getTeam().getPlayers() : verifiedHomeTeam.getUserB().getTeam().getPlayers();

        Player player = players.stream().filter(Player::getIsPitcher).findFirst().orElseThrow(() -> new RuntimeException("투수가 없습니다!"));

        List<Record> records = findAllRecords(player.getId());

        return new GamePitcherDTO(player.getName(), records.get(records.size()-1).getPitch());
    }

    private List<Record> findAllRecords(Long playerId) {
        String sql = "SELECT r.id, r.pitch, r.mounts, r.hit, r.strike, r.ball, r.outs, r.batting_average " +
                "FROM record r INNER JOIN player p ON r.player_id = p.id WHERE p.id = ?";

        RowMapper<Record> recordRowMapper = new RowMapper<Record>() {
            @Override
            public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
                Record record = new Record();
                record.setId(rs.getLong("id"));
                record.setPitch(rs.getInt("pitch"));
                record.setMounts(rs.getInt("mounts"));
                record.setHit(rs.getInt("hit"));
                record.setStrike(rs.getInt("strike"));
                record.setBall(rs.getInt("ball"));
                record.setOuts(rs.getInt("outs"));
                record.setBattingAverage(rs.getDouble("batting_average"));
                return record;
            }
        };

        return this.jdbcTemplate.query(sql, new Object[]{playerId}, recordRowMapper);
    }

    private GameBatterDTO makeBatterDTO(UserMatchesDTO verifiedHomeTeam) {

        boolean userAOffense = verifiedHomeTeam.getUserA().isOffense();

        List<Player> players = userAOffense ? verifiedHomeTeam.getUserA().getTeam().getPlayers() : verifiedHomeTeam.getUserB().getTeam().getPlayers();

        String sql = "SELECT i.half AS i_half," +
                "h1.last_bat_player AS h1_last_bat_player, " +
                "h2.last_bat_player AS h2_last_bat_payer " +
                "FROM matches m " +
                "INNER JOIN game g ON m.id = g.matches_id " +
                "INNER JOIN inning i ON g.id = i.game_id " +
                "LEFT OUTER JOIN half h1 ON i.first_half_id = h1.id " +
                "LEFT OUTER JOIN half h2 ON i.second_half_id = h2.id";

        RowMapper<GameBatterDTO> batterRowMapper = new RowMapper<GameBatterDTO>() {
            @Override
            public GameBatterDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                Player player = isTopHalf(rs) ? players.get(rs.getInt("h1_last_bat_player")) : players.get(rs.getInt("h2_last_bat_player"));
                List<Record> records = findAllRecords(player.getId());
                Record record = records.get(records.size()-1);
                return new GameBatterDTO(player.getName(), record.getMounts(), record.getHit());
            }
        };

        return this.jdbcTemplate.queryForObject(sql, batterRowMapper);
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
