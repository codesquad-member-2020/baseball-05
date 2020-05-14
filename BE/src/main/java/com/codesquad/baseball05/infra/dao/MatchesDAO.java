package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.matches.dto.MatchesDTO;
import com.codesquad.baseball05.domain.matches.dto.MatchesTeamDTO;
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
public class MatchesDAO {

    private final JdbcTemplate jdbcTemplate;

    public MatchesDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<MatchesDTO> listMatches() {
        String sql = "SELECT DISTINCT m.id, m.home_team, m.away_team, GROUP_CONCAT(u.user_id), GROUP_CONCAT(t.name)" +
                "FROM matches m " +
                "LEFT OUTER JOIN user u ON m.user_a = u.id OR m.user_b = u.id " +
                "LEFT OUTER JOIN team t ON u.team_id = t.id " +
                "GROUP BY m.id";

        RowMapper<MatchesDTO> matchesRowMapper = new RowMapper<MatchesDTO>() {
            @Override
            public MatchesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                MatchesTeamDTO homeTeamDTO = connectTeamWithUser(rs, "home_team");
                MatchesTeamDTO awayTeamDTO = connectTeamWithUser(rs, "away_team");

                MatchesDTO matchesDTO = new MatchesDTO();
                matchesDTO.setId(rs.getLong("id"));
                matchesDTO.setSelectable(isFull(rs));
                matchesDTO.setHomeTeam(homeTeamDTO);
                matchesDTO.setAwayTeam(awayTeamDTO);
                return matchesDTO;
            }
        };

        return this.jdbcTemplate.query(sql, matchesRowMapper);
    }

    private MatchesTeamDTO connectTeamWithUser(ResultSet rs, String region) throws SQLException {
        String teamName = rs.getString(region);
        String teamUserName = null;

        if(rs.getString("GROUP_CONCAT(u.user_id)")==null) {
            return new MatchesTeamDTO(rs.getString(region), null);
        }

        String[] userIds = rs.getString("GROUP_CONCAT(u.user_id)").split(",");
        String[] teamNames = rs.getString("GROUP_CONCAT(t.name)").split(",");

        for (int i = 0; i < userIds.length; i++) {
            if(teamName.equals(teamNames[i])) {
                teamUserName = userIds[i];
            }
        }

        return new MatchesTeamDTO(rs.getString(region), teamUserName);
    }

    private boolean isFull(ResultSet rs) throws SQLException {
        String userIdsLine = rs.getString("GROUP_CONCAT(u.user_id)");
        if(rs.wasNull()) {
            return true;
        }
        return userIdsLine.split(",").length != 2;
    }
}
