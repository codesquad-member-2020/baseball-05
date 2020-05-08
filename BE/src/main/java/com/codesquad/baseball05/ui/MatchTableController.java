package com.codesquad.baseball05.ui;

import com.codesquad.baseball05.infra.dao.MatchTableDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MatchTableController {

    private final MatchTableDao matchTableDao;

    @GetMapping("/api/matches")
    public Object listMatches() {
        return matchTableDao.listMatches();
    }
}
