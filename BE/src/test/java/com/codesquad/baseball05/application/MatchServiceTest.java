package com.codesquad.baseball05.application;

import com.codesquad.baseball05.domain.dto.MatchDTO;
import com.codesquad.baseball05.infra.MatchDAO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MatchServiceTest {
    private static final Logger log = LoggerFactory.getLogger(MatchServiceTest.class);

    @Autowired
    private MatchDAO matchDAO;

    @Test
    void updateUserAtMatch() {
        MatchDTO matchDTO = matchDAO.findByTeamName("한화");
        log.debug("matchDTO : {}", matchDTO.toString());
        log.debug("matchDTO userA : {} , userB : {} ", matchDTO.getUserA(), matchDTO.getUserB());
        assertThat(matchDTO).isNotNull();
    }
}
