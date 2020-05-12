package com.codesquad.baseball05.domain.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GameBatterDTO {

    private String name;

    private int mounts;

    private int hits;
}
