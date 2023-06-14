package com.dbwp031.dylc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TodoPostDto {
    private Long id;
    private String context;
    private boolean done;
}
