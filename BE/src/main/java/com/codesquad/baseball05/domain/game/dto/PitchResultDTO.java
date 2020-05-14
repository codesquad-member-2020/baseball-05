package com.codesquad.baseball05.domain.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PitchResultDTO {

    @JsonProperty("isFirstHalf")
    private boolean isFirstHalf;

    private GameTeamDTO homeTeam;

    private GameTeamDTO awayTeam;

    private CurrentPlayerDTO currentPlayers;

    private InningDTO inning;

    private List<PlateDTO> plates;
}
