package com.codesquad.baseball05.domain.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerDTO {

    private String name;

    private int mount;

    private int hit;

    private boolean isPitcher;
}
