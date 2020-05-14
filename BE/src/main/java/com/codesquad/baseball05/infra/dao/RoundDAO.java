package com.codesquad.baseball05.infra.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class RoundDAO {

    private final JdbcTemplate jdbcTemplate;

    public RoundDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Object pitch() {
        return null;
    }


}
