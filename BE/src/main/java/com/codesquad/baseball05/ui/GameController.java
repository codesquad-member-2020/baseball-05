package com.codesquad.baseball05.ui;

import com.codesquad.baseball05.infra.dao.GameDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequestMapping("/api/games")
@RequiredArgsConstructor
@RestController
public class GameController {

    private final GameDao gameDao;

    @PatchMapping("")
    public ResponseEntity<HttpStatus> terminateGame(@RequestBody Map<String, Long> gameIdMap) {
        gameDao.terminateGame(gameIdMap.get("gameId"));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/rounds")
    public Object ready(@RequestParam Boolean isOffense) {
        return gameDao.ready();
    }

    @PostMapping("/rounds")
    public Object pitch(@RequestParam Boolean isOffense) {
        return gameDao.pitch();
    }
}
