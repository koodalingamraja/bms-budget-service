package com.budgetfriendly.bmsbudgetservice.config;

import com.budgetfriendly.bmsbudgetservice.dto.UserDTO;

public class UserContextHolder {

    private static final ThreadLocal<UserDTO> USER_CONTEXT = new ThreadLocal<>();

    public static void setUserDto(UserDTO userDto) {
        USER_CONTEXT.set(userDto);
    }

    public static UserDTO getUserDto() {
        return USER_CONTEXT.get();
    }

    public static void clear() {
        USER_CONTEXT.remove();
    }
}
