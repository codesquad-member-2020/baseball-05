package com.codesquad.baseball05.infra;

import com.codesquad.baseball05.domain.game.dto.RoundRecordDTO;
import com.codesquad.baseball05.infra.dao.TeamDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TeamDAOTest {
    @Autowired
    private TeamDAO teamDAO;

    @Test
    void isExistedTeam() {
    }

    @Test
    void findByTeamName() {
    }

    @Test
    void findPlayerByTeamId() {
    }

    @Test
    void findPlayerRecord() {
        List<RoundRecordDTO> roundRecordDTOList = teamDAO.findPlayerRecord(1L);
        assertThat(roundRecordDTOList).isNotNull();
    }
}
