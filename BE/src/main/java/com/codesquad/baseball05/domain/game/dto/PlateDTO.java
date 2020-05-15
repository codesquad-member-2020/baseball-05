package com.codesquad.baseball05.domain.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlateDTO {

    private Long id;

    private int outs;

    private GameBatterDTO batter;

    private List<RoundDTO> rounds;
}
