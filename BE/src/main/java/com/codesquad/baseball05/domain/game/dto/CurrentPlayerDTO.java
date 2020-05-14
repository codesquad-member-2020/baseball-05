package com.codesquad.baseball05.domain.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurrentPlayerDTO {

    private GamePitcherDTO pitcher;

    private GameBatterDTO batter;
}
