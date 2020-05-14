package com.codesquad.baseball05.domain.game.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InningDTO {
    private Long id;
    private Long gameId;
    private Long firstHalfId;
    private Long secondHalfId;
    private String half;
}
