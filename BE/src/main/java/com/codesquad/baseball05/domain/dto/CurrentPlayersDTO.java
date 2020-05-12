package com.codesquad.baseball05.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurrentPlayersDTO {

    private PitcherDTO pitcher;

    private BatterDTO batter;
}
