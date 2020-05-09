package com.codesquad.baseball05.ui;

import com.codesquad.baseball05.infra.dao.MatchesDao;
import com.codesquad.baseball05.infra.dao.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MatchController {

    private final MatchesDao matchesDao;

    private final UserDao userDao;

    @GetMapping("/api/matches")
    public Object listMatches() {
        return matchesDao.listMatches(userDao);
    }
}
