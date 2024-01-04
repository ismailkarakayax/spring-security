package com.ismail.jwttoken.dto;

public record AuthRequest(
    String username,
    String password
) {
}
