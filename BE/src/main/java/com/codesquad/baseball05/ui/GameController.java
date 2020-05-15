package com.codesquad.baseball05.ui;

import com.codesquad.baseball05.application.GameService;
import com.codesquad.baseball05.domain.game.dto.PitchResultDTO;
import com.codesquad.baseball05.infra.dao.GameDAO;
import com.codesquad.baseball05.infra.dao.PitchDAO;
import com.codesquad.baseball05.infra.dao.SelectDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RequestMapping("/games")
@RequiredArgsConstructor

@RestController
public class GameController {
    private final GameService gameService;

    private final GameDAO gameDAO;

    private final PitchDAO pitchDAO;

    private final SelectDAO selectDao;

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

    @PatchMapping("")
    public ResponseEntity<HttpStatus> terminateGame(@RequestBody Map<String, Long> gameIdMap) {
        gameDAO.terminateGame(gameIdMap.get("gameId"));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/rounds")
    public PitchResultDTO ready(@RequestParam Long matchesId) throws SQLException {
        return gameDAO.ready(selectDao, matchesId);
    }

    @PostMapping("/rounds")
    public ResponseEntity<HttpStatus> pitch(@RequestParam Long matchesId) throws SQLException {
        pitchDAO.pitch(selectDao, matchesId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
