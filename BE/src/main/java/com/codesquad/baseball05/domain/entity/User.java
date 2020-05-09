package com.codesquad.baseball05.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@AllArgsConstructor
public class User {

    @Id
    private Long id;

    private Team team;

    private String userId;

    private String email;
}
