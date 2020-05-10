package com.codesquad.baseball05.domain.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlateDTO {

    private Long plate_id;

    private int outs;

    private BatterDTO batter;

    private RoundDTO round;
}
