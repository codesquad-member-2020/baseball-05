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
    public void terminateGame(Long matchesId) {
        disconnectTeamWithUser(matchesId);
        disconnectMatchWithUser(matchesId);
    }

    public Object ready() {
        return null;
    }

    public Object pitch(UserMatchesDTO userMatchesDTO, Long matchesId) throws SQLException {
        Matches matches = userMatchesDTO.getMatches();

        GameTeamDTO homeTeam = null;
        GameTeamDTO awayTeam = null;

        if (isHomeTeam(userMatchesDTO.getUserA(), matches)) {
            homeTeam = makeTeamDTO(userMatchesDTO.getUserA(), matches, matchesId);
            awayTeam = makeTeamDTO(userMatchesDTO.getUserB(), matches, matchesId);
        } else {
            homeTeam = makeTeamDTO(userMatchesDTO.getUserB(), matches, matchesId);
            awayTeam = makeTeamDTO(userMatchesDTO.getUserA(), matches, matchesId);
        }

        UserMatchesDTO verifiedHomeTeam = verifiedHomeTeam(userMatchesDTO, homeTeam);

        CurrentPlayerDTO players = new CurrentPlayerDTO(makePitcherDTO(verifiedHomeTeam), makeBatterDTO(verifiedHomeTeam, matchesId, 0));

        InningDTO inning = makeInningDTO(matchesId);

        List<PlateDTO> plates = makePlateDTOList(verifiedHomeTeam, matchesId);

        return new PitchResultDTO(isTopHalf(matchesId), homeTeam, awayTeam, players, inning, plates);
    }

    private List<PlateDTO> makePlateDTOList(UserMatchesDTO verifiedHomeTeam, Long matchesId) {
        String sql = "SELECT h1.outs AS h1_outs, " +
                "p.id AS p_id " +
                "FROM matches m " +
                "INNER JOIN game g ON g.matches_id = m.id " +
                "INNER JOIN inning i ON g.id = i.game_id " +
                "LEFT OUTER JOIN half h1 ON i.first_half_id = h1.id " +
                "LEFT OUTER JOIN plate p ON h1.id = p.half_id " +
                "WHERE g.id = ?";

        RowMapper<PlateDTO> plateRowMapper = (rs, rowNum) -> {
            return new PlateDTO(rs.getLong("p_id"),
                    rs.getInt("h1_outs"),
                    makeBatterDTO(verifiedHomeTeam, matchesId, rowNum),
                    makeRoundDTOList(matchesId)
                    );
        };

        return this.jdbcTemplate.query(sql, new Object[]{matchesId}, plateRowMapper);
    }

    private List<RoundDTO> makeRoundDTOList(Long matchesId) {
        String sql = "SELECT r.strike AS r_strike, " +
                "r.ball AS r_ball, " +
                "r.hit_or_out AS r_hit_or_out " +
                "FROM matches m " +
                "INNER JOIN game g ON g.matches_id = m.id " +
                "INNER JOIN inning i ON g.id = i.game_id " +
                "LEFT OUTER JOIN half h1 ON i.first_half_id = h1.id " +
                "LEFT OUTER JOIN half h2 ON i.second_half_id = h2.id " +
                "LEFT OUTER JOIN plate p ON h1.id = p.half_id OR h2.id = p.half_id " +
                "LEFT OUTER JOIN round r ON p.id = r.plate_id " +
                "WHERE g.id = ?";

        RowMapper<RoundDTO> roundRowMapper = (rs, rowNum) -> {
            return new RoundDTO(rs.getString("r_hit_or_out"),
                    rs.getInt("r_strike"),
                    rs.getInt("r_ball"));
        };

        return this.jdbcTemplate.query(sql, new Object[]{matchesId}, roundRowMapper);
    }

    private InningDTO makeInningDTO(Long matchesId) {
        String sql = "SELECT i.id AS i_id " +
                "FROM matches m " +
                "INNER JOIN game g ON m.id = g.matches_id " +
                "INNER JOIN inning i ON g.id = i.game_id " +
                "WHERE g.id = ?";

        RowMapper<InningDTO> pitchResultDtoRowMapper = (rs, rowNum) -> {
            String half = isTopHalf(matchesId) ? "초" : "말";
            return new InningDTO(rs.getLong("i_id"), half);
        };

        return this.jdbcTemplate.queryForObject(sql, new Object[]{matchesId}, pitchResultDtoRowMapper);
    }

    private UserMatchesDTO verifiedHomeTeam(UserMatchesDTO userMatchesDTO, GameTeamDTO homeTeam) {
        Matches matches = userMatchesDTO.getMatches();

        if (isHomeTeam(userMatchesDTO.getUserA(), matches)) {
            userMatchesDTO.getUserA().setHome(true);

            userMatchesDTO.getUserA().setOffense(homeTeam.isOffense());
            userMatchesDTO.getUserB().setOffense(!homeTeam.isOffense());
        } else {
            userMatchesDTO.getUserB().setHome(true);

            userMatchesDTO.getUserA().setOffense(!homeTeam.isOffense());
            userMatchesDTO.getUserB().setOffense(homeTeam.isOffense());
        }

        return userMatchesDTO;
    }

    private GameTeamDTO makeTeamDTO(UserTeamDTO userTeamDTO, Matches matches, Long matchesId) {
        String sql = "SELECT i.half AS i_half, " +
                "h1.point AS h1_point, h2.point AS h2_point " +
                "FROM matches m " +
                "INNER JOIN game g ON g.matches_id = m.id " +
                "INNER JOIN inning i ON g.id = i.game_id " +
                "LEFT OUTER JOIN half h1 ON i.first_half_id = h1.id " +
                "LEFT OUTER JOIN half h2 ON i.second_half_id = h2.id " +
                "WHERE g.id = ?";

        RowMapper<GameTeamDTO> pitchResultDtoRowMapper = (rs, rowNum) -> {
            String teamName = userTeamDTO.getTeam().getName();
            return new GameTeamDTO(teamName, getTotalPoint(rs, matchesId), isOffense(matchesId, isHomeTeam(userTeamDTO, matches)));
        };

        return this.jdbcTemplate.queryForObject(sql, new Object[]{matchesId}, pitchResultDtoRowMapper);
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

        return new GamePitcherDTO(player.getName(), records.get(records.size() - 1).getPitch());
    }

    private List<Record> findAllRecords(Long playerId) {
        String sql = "SELECT r.id, r.pitch, r.mounts, r.hit, r.strike, r.ball, r.outs, r.batting_average " +
                "FROM record r " +
                "INNER JOIN player p ON r.player_id = p.id WHERE p.id = ?";

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

    private GameBatterDTO makeBatterDTO(UserMatchesDTO verifiedHomeTeam, Long matchesId, int lastDiffPlayerIndex) {

        boolean userAOffense = verifiedHomeTeam.getUserA().isOffense();

        List<Player> players = userAOffense ? verifiedHomeTeam.getUserA().getTeam().getPlayers() : verifiedHomeTeam.getUserB().getTeam().getPlayers();

        String sql = "SELECT i.half AS i_half," +
                "h1.last_bat_player AS h1_last_bat_player, " +
                "h2.last_bat_player AS h2_last_bat_payer " +
                "FROM matches m " +
                "INNER JOIN game g ON g.matches_id = m.id " +
                "INNER JOIN inning i ON g.id = i.game_id " +
                "LEFT OUTER JOIN half h1 ON i.first_half_id = h1.id " +
                "LEFT OUTER JOIN half h2 ON i.second_half_id = h2.id " +
                "WHERE g.id = ?";

        RowMapper<GameBatterDTO> batterRowMapper = new RowMapper<GameBatterDTO>() {
            @Override
            public GameBatterDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                Player player = isTopHalf(matchesId) ? players.get(rs.getInt("h1_last_bat_player") - lastDiffPlayerIndex) : players.get(rs.getInt("h2_last_bat_player") - lastDiffPlayerIndex);
                List<Record> records = findAllRecords(player.getId());
                Record record = records.get(records.size() - 1);
                return new GameBatterDTO(player.getName(), record.getMounts(), record.getHit());
            }
        };

        return this.jdbcTemplate.queryForObject(sql, new Object[]{matchesId}, batterRowMapper);
    }

    private boolean isTopHalf(Long matchesId) throws SQLException {
        String sql = "SELECT i.half AS i_half " +
                "FROM matches m " +
                "INNER JOIN game g ON g.matches_id = m.id " +
                "INNER JOIN inning i ON g.id = i.game_id " +
                "WHERE g.id = ?";

        RowMapper<Boolean> batterRowMapper = (rs1, rowNum) -> rs1.getBoolean("i_half");

        return this.jdbcTemplate.queryForObject(sql, new Object[]{matchesId}, batterRowMapper);
    }

    private int getTotalPoint(ResultSet rs, Long matchesId) throws SQLException {
        return isTopHalf(matchesId) ? rs.getInt("h1_point") : rs.getInt("h2_point");
    }

    private boolean isOffense(Long matchesId, boolean isHomeTeam) throws SQLException {
        if (isTopHalf(matchesId) && !isHomeTeam) {
            return true;
        } else return !isTopHalf(matchesId) && isHomeTeam;
    }

    private int disconnectTeamWithUser(Long matchesId) {
        String sql = "UPDATE matches m " +
                "INNER JOIN user u ON m.a_user_id = u.id OR m.b_user_id = u.id " +
                "SET u.team_id = null " +
                "WHERE m.id = ?";

        return this.jdbcTemplate.update(sql, matchesId);
    }

    private int disconnectMatchWithUser(Long matchesId) {
        String sql = "UPDATE matches m " +
                "SET m.a_user_id = null, b_user_id = null " +
                "WHERE m.id = ?";

        return this.jdbcTemplate.update(sql, matchesId);
    }
}
