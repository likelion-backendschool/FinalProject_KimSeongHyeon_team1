package com.example.mutbooks.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
/* authLevel에 따라 권한이 다름. */
public enum Role {
    USER(3),
    ADMIN(7);

    private final int authLevel;
}
