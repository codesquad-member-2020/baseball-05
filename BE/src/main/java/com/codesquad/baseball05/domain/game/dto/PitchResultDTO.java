package com.codesquad.baseball05.domain.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PitchResultDTO {

    private GameTeamDTO homeTeam;

    private GameTeamDTO awayTeam;

    private PitcherDTO pitcher;

    private InningDTO inning;

    private List<PlateDTO> plate;
}
