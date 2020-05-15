package com.codesquad.baseball05.domain.game.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Plate {

    @Id
    private Long id;

    private int strike;

    private int ball;

    private List<Round> rounds;
}
