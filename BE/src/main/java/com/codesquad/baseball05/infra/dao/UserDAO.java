package com.codesquad.baseball05.infra.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDAO {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserDAO(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Boolean isSelectedTeam(String teamName) { //select 선점된 팀이면 true?
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("teamName", teamName);

        String isSelectedTeamSql = "SELECT exists( SELECT id  FROM user WHERE team_id = (" +
                                    " SELECT id FROM team WHERE name = :teamName ))";
        return namedParameterJdbcTemplate.queryForObject(isSelectedTeamSql, namedParameters, Boolean.class);
    }

    public void choiceTeam(String userId, String teamName) { //update
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("teamName", teamName)
                .addValue("userId", userId);

        String choiceTeamSql = "UPDATE user SET team_id = (SELECT id from team where name=:teamName) WHERE user_id = :userId";
        namedParameterJdbcTemplate.update(choiceTeamSql, namedParameters);
    }

    public Long findByUserId(String userId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("userId", userId);

        String findByUserIdSql = "SELECT id FROM user WHERE user_id = :userId";
        return namedParameterJdbcTemplate.queryForObject(findByUserIdSql, namedParameters, Long.class);
    }

    public void deleteTeam(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);

        String deleteTeamSql = "UPDATE user SET team_id=NULL WHERE id = :id";
        namedParameterJdbcTemplate.update(deleteTeamSql, namedParameters);
    }
}
