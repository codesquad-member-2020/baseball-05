package com.codesquad.baseball05.domain.game.entity;

import com.codesquad.baseball05.domain.team.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    private Long id;

    private List<Inning> innings;
}
