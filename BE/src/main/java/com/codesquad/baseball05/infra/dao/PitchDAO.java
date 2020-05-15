package com.codesquad.baseball05.infra.dao;

import com.codesquad.baseball05.domain.game.dto.AllTablesDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;

@Repository
public class PitchDAO {

    private final JdbcTemplate jdbcTemplate;

    public PitchDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Object pitch(SelectDAO selectDao, Long matchesId) throws SQLException {
        AllTablesDTO allTablesDTO = selectDao.makeUserMatchesDTO(matchesId);
        allTablesDTO.setHomeAndOffense();

        return null;
    }
}
