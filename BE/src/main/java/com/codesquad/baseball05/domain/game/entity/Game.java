package com.codesquad.baseball05.domain.game.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@AllArgsConstructor
public class Game {

    @Id
    private Long id;

    private List<Inning> innings;
}
