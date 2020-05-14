package com.codesquad.baseball05.ui;

import com.codesquad.baseball05.application.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameMenuController {
    private final GameService gameService;

    public GameMenuController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/start")
    public ResponseEntity<ResponseBodyWrapper> isStarted(@RequestParam Long matchId) {
        return ResponseEntity.ok(ResponseBodyWrapper.ok(gameService.isStartedGame(matchId)));
    }

}
