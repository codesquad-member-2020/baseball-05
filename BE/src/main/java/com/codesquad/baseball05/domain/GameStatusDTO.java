package com.codesquad.baseball05.domain;

import com.codesquad.baseball05.domain.dto.CurrentPlayersDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GameStatusDTO {

    @JsonProperty("isFirstHalf")
    private boolean isFirstHalf;

    private Team homeTeam;

    private Team awayTeam;

    private CurrentPlayersDTO currentPlayers;

    private Inning inning;

    private List<Plate> plates;
}
