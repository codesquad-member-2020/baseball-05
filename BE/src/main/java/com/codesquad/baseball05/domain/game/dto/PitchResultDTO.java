package com.codesquad.baseball05.domain.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PitchResultDTO {

    private GameTeamDTO homeTeam;

    private GameTeamDTO awayTeam;

    private CurrentPlayerDTO players;
//
//    private InningDTO inning;
//
//    private List<GameBatterDTO> plates;
}
