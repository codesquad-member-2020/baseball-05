package com.codesquad.baseball05.domain.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BatterDTO {

    private String name;

    private int mount;

    private int hit;

    private boolean isPitcher;
}
