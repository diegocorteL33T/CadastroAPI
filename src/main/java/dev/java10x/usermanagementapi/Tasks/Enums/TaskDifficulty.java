package dev.java10x.usermanagementapi.Tasks.Enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TaskDifficulty {

    S(7.0),
    A(5.0),
    B(3.0),
    C(2.0),
    D(1.0),
    E(0.5);

    private final double difficulty;

}





