package com.codesquad.baseball05.ui;

import com.codesquad.baseball05.domain.game.dto.UserMatchesDTO;
import com.codesquad.baseball05.infra.dao.GameDao;
import com.codesquad.baseball05.infra.dao.SelectDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
@RequestMapping("/api/games")
@RequiredArgsConstructor
@RestController
public class GameController {

    private final GameDao gameDao;

    private final SelectDao selectDao;

    @PatchMapping("")
    public ResponseEntity<HttpStatus> terminateGame(@RequestBody Map<String, Long> gameIdMap) {
        gameDao.terminateGame(gameIdMap.get("gameId"));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/rounds")
    public Object ready(@RequestParam Long gameId) {
        UserMatchesDTO userMatchesDTO = selectDao.makeUserMatchesDTO(gameId);
        return gameDao.ready();
    }

    @PostMapping("/rounds")
    public Object pitch(@RequestParam Long gameId) throws SQLException {
        UserMatchesDTO userMatchesDTO = selectDao.makeUserMatchesDTO(gameId);
        return gameDao.pitch(userMatchesDTO, gameId);
    }
}
