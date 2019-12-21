package com.database.model.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserAccountDto {

    private final String username;
    private final String password;

    public static UserAccountDto createUserAccount(String username,String password){
        return new UserAccountDto(username,password);
    }
}
