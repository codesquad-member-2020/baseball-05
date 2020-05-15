package com.codesquad.baseball05.domain.game.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Round {

    @Id
    private Long id;

    private String playerName;

    private int strike;

    private int ball;

    public String hitOrOut;

    public Round(String playerName) {
        this.playerName = playerName;
    }
}
