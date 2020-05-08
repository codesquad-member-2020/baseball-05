package com.codesquad.baseball05.ui;

import com.codesquad.baseball05.infra.dao.MatchDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MatchController {

    private final MatchDao matchDao;

    @PostMapping("/api/matches")
    public Object listMatches(@RequestParam Boolean isOffense) {
        return matchDao.listMatches();
    }
}
