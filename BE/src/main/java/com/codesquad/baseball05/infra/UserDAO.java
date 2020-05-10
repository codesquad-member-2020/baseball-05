package com.codesquad.baseball05.infra;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Boolean isSelectedTeam(String teamName) { //select 선점된 팀이면 true?
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("teamName", teamName);

        //Todo : 존재하지 않는 팀 정보일 경우 에러처리
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
}
