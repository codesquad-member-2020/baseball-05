package com.codesquad.baseball05.ui;

import com.codesquad.baseball05.infra.dao.RoundDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/games/rounds")
@RequiredArgsConstructor
@RestController
public class RoundController {

    private final RoundDao roundDao;

    @PostMapping("")
    public Object pitch() {
        return roundDao.pitch();
    }
}