package com.codesquad.baseball05.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private Long id;

    private Long team_id;

    private String userId;

    private String email;
}
