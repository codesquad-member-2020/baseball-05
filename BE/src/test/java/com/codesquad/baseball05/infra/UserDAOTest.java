package com.codesquad.baseball05.infra;

import com.codesquad.baseball05.infra.dao.UserDAO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserDAOTest {
    private static final Logger log = LoggerFactory.getLogger(UserDAOTest.class);

    @Autowired
    private UserDAO userDAO;

    @Test
    void isSelectedTeam() {
    }

    @Test
    void choiceTeam() {
    }

    @Test
    void findById() {
        String userId = "ever";
        Long user = userDAO.findByUserId(userId);
        assertThat(user).isEqualTo(1);
    }
}
