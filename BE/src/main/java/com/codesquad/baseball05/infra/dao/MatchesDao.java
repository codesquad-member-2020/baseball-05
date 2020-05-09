package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.dto.MatchesDTO;
import com.codesquad.baseball05.domain.dto.MatchesTeamDTO;
import com.codesquad.baseball05.domain.entity.Matches;
import com.codesquad.baseball05.domain.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MatchesDao {

    private final JdbcTemplate jdbcTemplate;

    public MatchesDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Matches> listMatches(UserDao userDao) {
        List<MatchesDTO> matchesDTOList = new ArrayList<>();

        List<Matches> matchesList = findAll();

        for (Matches match : matchesList) {

            //match.getUserAid == null 일 경우 userA(teamA)에 유저가 입장하지 않았다는 뜻
            User userA = match.getUserAid() == null ? null : userDao.findUserById(match.getUserAid());
            User userB = match.getUserBid() == null ? null : userDao.findUserById(match.getUserBid());

            String aTeamName = userA.getTeam().getName();

            String homeTeamUserName = match.getHomeTeam() == aTeamName ? userA.getUserId() : userB.getUserId();
            String awayTeamUserName = match.getAwayTeam() == aTeamName ? userA.getUserId() : userB.getUserId();

            MatchesTeamDTO teamA = new MatchesTeamDTO(
                    match.getHomeTeam(),
                    homeTeamUserName
            );

            MatchesTeamDTO teamB = new MatchesTeamDTO(
                    match.getAwayTeam(),
                    awayTeamUserName
            );

            MatchesDTO matchesDTO = new MatchesDTO(
                    match.getId(),
                    isPossibleEnterGame(match),
                    teamA,
                    teamB
            );

            matchesDTOList.add(matchesDTO);
        }

        return matchesList;
    }

    private boolean isPossibleEnterGame(Matches match) {
        return (match.getUserAid() == null) || (match.getUserBid() == null);
    }

    private List<Matches> findAll() {
        String sql = "SELECT m.id, m.home_team, m.away_team FROM matches m";

        RowMapper<Matches> matchesRowMapper = new RowMapper<Matches>() {
            @Override
            public Matches mapRow(ResultSet rs, int rowNum) throws SQLException {
                Matches matches = new Matches();
                matches.setId(rs.getLong("id"));
                matches.setHomeTeam(rs.getString("home_team"));
                matches.setAwayTeam(rs.getString("away_team"));
                return matches;
            }
        };

        return this.jdbcTemplate.query(sql, matchesRowMapper);
    }
}
