package com.codesquad.baseball05.domain.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InningDTO {

    private Long id;

    private String half;
}
