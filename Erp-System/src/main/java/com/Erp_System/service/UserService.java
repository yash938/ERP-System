package com.Erp_System.service;

import com.Erp_System.response.PaegableResponse;
import com.Erp_System.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto updateUser(int userId,UserDto user);

    UserDto findUserById(int userId);

    PaegableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    UserDto findUserByEmail(String email);

    void deleteUser(int userId);
}
