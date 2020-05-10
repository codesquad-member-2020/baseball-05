package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.dto.MatchesDTO;
import com.codesquad.baseball05.domain.dto.MatchesTeamDTO;
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
public class MatchesDao {

    private final JdbcTemplate jdbcTemplate;

    public MatchesDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<MatchesDTO> listMatches() {
        String sql = "SELECT DISTINCT m.id, m.home_team, m.away_team, GROUP_CONCAT(u.user_id), GROUP_CONCAT(u.email), GROUP_CONCAT(t.name)" +
                "FROM matches m " +
                "LEFT OUTER JOIN user u ON m.a_user_id = u.id OR m.b_user_id = u.id " +
                "LEFT OUTER JOIN team t ON u.team_id = t.id " +
                "GROUP BY m.id";

        RowMapper<MatchesDTO> matchesRowMapper = new RowMapper<MatchesDTO>() {
            @Override
            public MatchesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                String[] userIds = rs.getString("GROUP_CONCAT(u.user_id)").split(",");
                String[] teamNames = rs.getString("GROUP_CONCAT(t.name)").split(",");

                String homeTeamUserName = null;

                if(teamNames[0] == rs.getString("home_team")) {
                    homeTeamUserName = userIds[0];
                } else if(teamNames[1] == rs.getString("home_team")) {
                    homeTeamUserName = userIds[1];
                }

                String awayTeamUserName = null;

                if(teamNames[0] == rs.getString("away_team")) {
                    awayTeamUserName = userIds[0];
                } else if(teamNames[1] == rs.getString("away_team")) {
                    awayTeamUserName = userIds[1];
                }

                MatchesTeamDTO homeTeamDTO = new MatchesTeamDTO();
                homeTeamDTO.setTeamName(rs.getString("home_team"));
                homeTeamDTO.setUserName(homeTeamUserName);

                MatchesTeamDTO awayTeamDTO = new MatchesTeamDTO();
                awayTeamDTO.setTeamName(rs.getString("away_team"));
                awayTeamDTO.setUserName(awayTeamUserName);

                MatchesDTO matchesDTO = new MatchesDTO();
                matchesDTO.setId(rs.getLong("id"));
                matchesDTO.setSelectable(isFull(userIds));
                matchesDTO.setHomeTeam(homeTeamDTO);
                matchesDTO.setAwayTeam(awayTeamDTO);
                return matchesDTO;
            }
        };

        return this.jdbcTemplate.query(sql, matchesRowMapper);
    }

    private boolean isFull(String[] userIds) {
        return userIds.length == 2;
    }
}
