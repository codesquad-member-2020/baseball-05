package com.codesquad.baseball05.infra;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class TeamDAO {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TeamDAO(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Boolean isExistedTeam(String teamName) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("teamName", teamName);

        String isExistedTeamSql = "SELECT exists(SELECT id FROM team WHERE name = :teamName)";
        return namedParameterJdbcTemplate.queryForObject(isExistedTeamSql, namedParameters, Boolean.class);
    }

}
