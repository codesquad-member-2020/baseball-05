package com.codesquad.baseball05.ui;

import com.codesquad.baseball05.application.GameService;
import com.codesquad.baseball05.domain.game.dto.ScoreBoardDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/games")
@RestController
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping()
    public ResponseEntity<ResponseBodyWrapper> selectTeam(@RequestBody Map<String, Object> requestBody) {
        //로그인한 user의 정보가 이미 user 테이블에 저장되어있는 상황
        //해당 팀 선점 -> 선점가능하면 선점 : success, 선점 불가능 : fail
        String resultOfSelect = "fail";
        if (gameService.selectTeam((String) requestBody.get("teamName"))) {
            resultOfSelect = "success";
        }
        return ResponseEntity.ok(ResponseBodyWrapper.ok(resultOfSelect));
    }

    @PatchMapping()
    public ResponseEntity<HttpStatus> end(@RequestBody Object gameId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/rounds")
    public ScoreBoardDTO ready(@RequestParam Boolean status) {
        return new ScoreBoardDTO("한호");
    }

    @PostMapping("/rounds")
    public ScoreBoardDTO pitch() {
        return new ScoreBoardDTO("한화");
    }
}
