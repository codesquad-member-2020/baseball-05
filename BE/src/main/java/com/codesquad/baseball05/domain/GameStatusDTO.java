package com.codesquad.baseball05.domain;

import com.codesquad.baseball05.domain.dto.CurrentPlayersDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GameStatusDTO {

    private Team homeTeam;

    private Team awayTeam;

    private CurrentPlayersDTO currentPlayers;

    private Inning inning;

    private List<Plate> plates;
}
