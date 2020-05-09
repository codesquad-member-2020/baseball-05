package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.dto.MatchesDTO;
import com.codesquad.baseball05.domain.dto.MatchesTeamDTO;
import com.codesquad.baseball05.domain.entity.Matches;
import com.codesquad.baseball05.domain.entity.User;
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
public class MatchesDao {

    private final JdbcTemplate jdbcTemplate;

    public MatchesDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<MatchesDTO> listMatches(UserDao userDao, TeamDao teamDao) {
        List<MatchesDTO> matchesDTOList = new ArrayList<>();

        List<Matches> matchesList = findAll();

        List<User> hasTeamUsers = new ArrayList<>();

        for (Matches match : matchesList) {

            log.info("matches : {}", match);

            //match.getUserAid == null 일 경우 userA(teamA)에 유저가 입장하지 않았다는 뜻
            if (match.getAUserId() != 0) {
                User userA = userDao.findUserById(match.getAUserId());
                hasTeamUsers.add(userA);
            }

            if (match.getBUserId() != 0) {
                log.info("id : {}", match.getBUserId());
                User userB = userDao.findUserById(match.getBUserId());
                hasTeamUsers.add(userB);
            }

            MatchesTeamDTO teamA = new MatchesTeamDTO(
                    match.getHomeTeam(),
                    null
            );

            MatchesTeamDTO teamB = new MatchesTeamDTO(
                    match.getAwayTeam(),
                    null
            );

            for (User user: hasTeamUsers) {
                String teamName = teamDao.findTeamById(user.getTeam_id()).getName();

                if(match.getHomeTeam().equals(teamName)) {
                    teamA.setUserName(user.getUserId());
                }

                else {
                    teamB.setUserName(user.getUserId());
                }
            }

            log.info("teamA : {}", teamA);
            log.info("teamB : {}", teamB);

            MatchesDTO matchesDTO = new MatchesDTO(
                    match.getId(),
                    isPossibleEnterGame(match),
                    teamA,
                    teamB
            );

            matchesDTOList.add(matchesDTO);
        }

        return matchesDTOList;
    }

    private boolean isPossibleEnterGame(Matches match) {
        return (match.getAUserId() == null) || (match.getBUserId() == null);
    }

    private List<Matches> findAll() {
        String sql = "SELECT m.id, m.a_user_id, m.b_user_id, m.home_team, m.away_team FROM matches m";

        RowMapper<Matches> matchesRowMapper = new RowMapper<Matches>() {
            @Override
            public Matches mapRow(ResultSet rs, int rowNum) throws SQLException {
                Matches matches = new Matches();
                matches.setId(rs.getLong("id"));
                matches.setAUserId(rs.getLong("a_user_id"));
                matches.setBUserId(rs.getLong("b_user_id"));
                matches.setHomeTeam(rs.getString("home_team"));
                matches.setAwayTeam(rs.getString("away_team"));
                return matches;
            }
        };

        return this.jdbcTemplate.query(sql, matchesRowMapper);
    }
}
