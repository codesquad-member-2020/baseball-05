package com.codesquad.baseball05.ui;

import com.codesquad.baseball05.application.BoardService;
import com.codesquad.baseball05.domain.dto.RosterBoardDTO;
import com.codesquad.baseball05.domain.dto.ScoreBoardDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/scores")
    public List<ScoreBoardDTO> viewScoreBoard(@RequestParam Long matchId) {
        return boardService.showScoreBoard(matchId);
    }

    @GetMapping("/rosters")
    public List<RosterBoardDTO> viewRosters(@RequestParam Long matchId) {
        return boardService.showRosterBoard(matchId);
    }
}
