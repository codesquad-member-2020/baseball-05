package com.codesquad.baseball05.domain.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InningDTO {

    private Long inning_id;

    private String topOrBottom;

    private String offenseOrDefense;
}
