package com.codesquad.baseball05.infra;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameDAOTest {
    @Autowired
    private GameDAO gameDAO;

    @Test
    void findFirstHalfId() {
        List<Long> firstHalfId = gameDAO.findFirstHalfId(1L);
        assertThat(firstHalfId).isNotNull();
    }
}
