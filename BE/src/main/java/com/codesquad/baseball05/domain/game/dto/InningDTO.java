package com.codesquad.baseball05.domain.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InningDTO {

    private Long id;

    @JsonProperty("isFirstHalf")
    private boolean isFirstHalf;
}
