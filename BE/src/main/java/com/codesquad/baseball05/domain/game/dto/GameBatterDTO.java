package com.codesquad.baseball05.domain.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameBatterDTO {

    private String name;

    private int mounts;

    private int hits;
}
