package com.codesquad.baseball05.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@AllArgsConstructor
public class Round {

    @Id
    private Long id;

    private String playerName;

    private int strike;

    private int ball;

    private String hitOrOut;
}
