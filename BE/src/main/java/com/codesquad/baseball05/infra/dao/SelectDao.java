package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.game.dto.UserMatchesDTO;
import com.codesquad.baseball05.domain.game.dto.UserTeamDTO;
import com.codesquad.baseball05.domain.matches.entity.Matches;
import com.codesquad.baseball05.domain.team.entity.Player;
import com.codesquad.baseball05.domain.team.entity.Team;
import com.codesquad.baseball05.domain.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SelectDao {

    private final JdbcTemplate jdbcTemplate;

    public SelectDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public UserMatchesDTO makeUserMatchesDTO(Long matchesId) {
        Matches matches = findMatchesById(matchesId);
        User userA = findUserById(matches.getAUserId());
        Team teamA = findTeamById(userA.getTeam_id());
        List<Player> playersAteam = findAllPlayerById(teamA.getId());

        UserTeamDTO userATeamDTO = new UserTeamDTO(userA, teamA, playersAteam);

        User userB = findUserById(matches.getBUserId());
        Team teamB = findTeamById(userB.getTeam_id());
        List<Player> playersBteam = findAllPlayerById(teamB.getId());

        UserTeamDTO userBTeamDTO = new UserTeamDTO(userB, teamB, playersBteam);

        return new UserMatchesDTO(userATeamDTO, userBTeamDTO, matches);
    }

    private Team findTeamById(Long teamId) {
        String sql = "SELECT t.id, t.name FROM team t WHERE t.id = ?";

        RowMapper<Team> teamRowMapper = (rs, rowNum) -> {
            Team team = new Team();
            team.setId(rs.getLong("id"));
            team.setName(rs.getString("name"));
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
            player.setLineUp(rs.getInt("lintUp"));
            player.setName(rs.getString("name"));
            player.setBattingAverage(rs.getDouble("battingAverage"));
            player.setIsPitcher(rs.getBoolean("isPitcher"));
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
