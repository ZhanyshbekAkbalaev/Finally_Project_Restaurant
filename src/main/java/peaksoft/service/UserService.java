package peaksoft.service;

import peaksoft.dto.request.RegisterRequest;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.AuthenticationResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    AuthenticationResponse register(UserRequest userRequest);
    List<UserResponse> findAllUsers();
    SimpleResponse deleteUserById(Long userId);
    SimpleResponse updateUser(Long userId,UserRequest userRequest);
    UserResponse findById(Long userId);
    SimpleResponse answer(Long restaurantId,Long userId,String word);
}
