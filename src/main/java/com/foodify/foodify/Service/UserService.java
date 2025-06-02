package com.foodify.foodify.Service;

import com.foodify.foodify.IO.UserRequest;
import com.foodify.foodify.IO.UserResponse;

public interface UserService {

    UserResponse registerUser(UserRequest request);
}
