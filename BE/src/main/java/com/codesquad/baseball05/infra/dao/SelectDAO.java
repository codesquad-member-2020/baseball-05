package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.game.dto.AllTablesDTO;
import com.codesquad.baseball05.domain.game.dto.UserTeamDTO;
import com.codesquad.baseball05.domain.game.entity.*;
import com.codesquad.baseball05.domain.matches.entity.Matches;
import com.codesquad.baseball05.domain.team.entity.Player;
import com.codesquad.baseball05.domain.team.entity.Team;
import com.codesquad.baseball05.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository
public class SelectDAO {

    private final JdbcTemplate jdbcTemplate;

    public SelectDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public AllTablesDTO makeUserMatchesDTO(Long matchesId) {
        Matches matches = findMatchesById(matchesId);
        User userA = findUserById(matches.getAUserId());
        Team teamA = findTeamById(userA.getTeam_id());

        UserTeamDTO userATeamDTO = new UserTeamDTO(userA, teamA);

        User userB = findUserById(matches.getBUserId());
        Team teamB = findTeamById(userB.getTeam_id());

        UserTeamDTO userBTeamDTO = new UserTeamDTO(userB, teamB);

        Game game = findGameById(matchesId);

        return new AllTablesDTO(userATeamDTO, userBTeamDTO, matches, game);
    }

    private Game findGameById(Long matchesId) {
        String sql = "SELECT g.id FROM game g WHERE g.matches_id = ?";

        RowMapper<Game> gameRowMapper = (rs, rowNum) -> {
            Game game = new Game();
            game.setId(rs.getLong("g.id"));
            game.setInnings(findAllInnings(rs.getLong("g.id")));
            return game;
        };

        return this.jdbcTemplate.queryForObject(sql, new Object[]{matchesId}, gameRowMapper);
    }

    private List<Inning> findAllInnings(Long gameId) {
        String sql = "SELECT i.id, i.first_half_id, i.second_half_id, i.half FROM inning i WHERE i.game_id = ?";

        RowMapper<Inning> inningRowMapper = (rs, rowNum) -> {
            Inning inning = new Inning();
            inning.setId(rs.getLong("i.id"));
            inning.setFirstHalf(findHalfById(rs.getLong("i.first_half_id")));
            inning.setSecondHalf(findHalfById(rs.getLong("i.second_half_id")));
            inning.setIsFirstHalf(rs.getBoolean("i.half"));
            return inning;
        };

        return this.jdbcTemplate.query(sql, new Object[]{gameId}, inningRowMapper);
    }

    private Half findHalfById(Long halfId) {
        if (halfId == 0) {
            return null;
        }

        String sql = "SELECT h.id, h.last_bat_player, h.total_plate, h.outs, h.hit, h.point FROM half h WHERE h.id = ?";

        RowMapper<Half> halfRowMapper = (rs, rowNum) -> {
            Half half = new Half();
            half.setId(rs.getLong("h.id"));
            half.setLastBatPlayer(rs.getInt("h.last_bat_player"));
            half.setTotalPlate(rs.getInt("h.total_plate"));
            half.setOuts(rs.getInt("h.outs"));
            half.setHit(rs.getInt("h.hit"));
            half.setPoint(rs.getInt("h.point"));
            half.setPlates(finaAllPlateByHalfId(rs.getLong("h.id")));
            log.info("half : {}", half);
            return half;
        };

        return this.jdbcTemplate.queryForObject(sql, new Object[]{halfId}, halfRowMapper);
    }

    private List<Plate> finaAllPlateByHalfId(Long halfId) {
        String sql = "SELECT p.id, p.strike, p.ball, p.first_baseman, p.second_baseman, p.third_baseman FROM plate p WHERE p.half_id = ?";

        RowMapper<Plate> plateRowMapper = (rs, rowNum) -> {
            Plate plate = new Plate();
            plate.setId(rs.getLong("p.id"));
            plate.setStrike(rs.getInt("p.strike"));
            plate.setBall(rs.getInt("p.ball"));
            plate.setFirstBaseMan(rs.getString("p.first_baseman"));
            plate.setSecondBaseMan(rs.getString("p.second_baseman"));
            plate.setThirdBaseMan(rs.getString("p.third_baseman"));
            plate.setRounds(findAllRoundById(rs.getLong("p.id")));
            return plate;
        };

        return this.jdbcTemplate.query(sql, new Object[]{halfId}, plateRowMapper);
    }

    private List<Round> findAllRoundById(Long plateId) {
        String sql = "SELECT r.id, r.player_name, r.strike, r.ball, r.hit_or_out FROM round r WHERE r.plate_id = ?";

        RowMapper<Round> roundRowMapper = (rs, rowNum) -> {
            Round round = new Round();
            round.setId(rs.getLong("r.id"));
            round.setPlayerName(rs.getString("r.player_name"));
            round.setStrike(rs.getInt("r.strike"));
            round.setBall(rs.getInt("r.ball"));
            round.setHitOrOut(rs.getString("r.hit_or_out"));
            return round;
        };

        return this.jdbcTemplate.query(sql, new Object[]{plateId}, roundRowMapper);
    }


    private Team findTeamById(Long teamId) {
        String sql = "SELECT t.id, t.name FROM team t WHERE t.id = ?";

        RowMapper<Team> teamRowMapper = (rs, rowNum) -> {
            Team team = new Team();
            team.setId(rs.getLong("t.id"));
            team.setName(rs.getString("t.name"));
            team.setPlayers(findAllPlayerById(teamId));
            return team;
        };

        return this.jdbcTemplate.queryForObject(sql, new Object[]{teamId}, teamRowMapper);
    }

    private List<Player> findAllPlayerById(Long teamId) {
        String sql = "SELECT p.id, p.line_up, p.name, p.batting_average, p.is_pitcher FROM player p WHERE p.team_id = ?";

        RowMapper<Player> playersRowMapper = (rs, rowNum) -> {
            Player player = new Player();
            player.setId(rs.getLong("id"));
            player.setLineUp(rs.getInt("line_up"));
            player.setName(rs.getString("name"));
            player.setBattingAverage(rs.getDouble("batting_average"));
            player.setIsPitcher(rs.getBoolean("is_pitcher"));
            player.setRecords(null);
            return player;
        };

        return this.jdbcTemplate.query(sql, new Object[]{teamId}, playersRowMapper);
    }

    private User findUserById(Long userId) {
        String sql = "SELECT u.id, u.team_id, u.user_id, u.email FROM user u WHERE u.id = ?";

        RowMapper<User> teamRowMapper = (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setTeam_id(rs.getLong("team_id"));
            user.setUserId(rs.getString("user_id"));
            user.setEmail(rs.getString("email"));
            return user;
        };

        return this.jdbcTemplate.queryForObject(sql, new Object[]{userId}, teamRowMapper);
    }

    private Matches findMatchesById(Long matchesId) {
        String sql = "SELECT m.id, m.a_user_id, m.b_user_id, m.home_team, m.away_team FROM matches m WHERE m.id = ?";

        RowMapper<Matches> matchesRowMapper = (rs, rowNum) -> {
            Matches matches = new Matches();
            matches.setId(rs.getLong("id"));
            matches.setAUserId(rs.getLong("a_user_id"));
            matches.setBUserId(rs.getLong("b_user_id"));
            matches.setHomeTeam(rs.getString("home_team"));
            matches.setAwayTeam(rs.getString("away_team"));
            return matches;
        };

        return this.jdbcTemplate.queryForObject(sql, new Object[]{matchesId}, matchesRowMapper);
    }
}
