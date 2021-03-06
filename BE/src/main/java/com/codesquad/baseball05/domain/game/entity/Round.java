package com.codesquad.baseball05.domain.game.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Round {

    @Id
    private Long id;

    private String playerName;

    private int strike;

    private int ball;

    private String hitOrOut;
}
