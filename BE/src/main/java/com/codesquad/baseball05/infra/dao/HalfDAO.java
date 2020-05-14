package com.codesquad.baseball05.infra.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class HalfDAO {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public HalfDAO(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Integer getPointById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);

        String getPointByIdSql = "SELECT point FROM half WHERE id = :id";
        return namedParameterJdbcTemplate.queryForObject(getPointByIdSql, namedParameters, Integer.class);
    }
}
