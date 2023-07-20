package com.vuhoanghiep.InfoShare.service;

import com.vuhoanghiep.InfoShare.model.dto.AuthResponse;
import com.vuhoanghiep.InfoShare.model.dto.LoginDTO;
import com.vuhoanghiep.InfoShare.model.dto.UserDTO;

public interface AuthService {
    public AuthResponse register(UserDTO userDTO);
    public AuthResponse login(LoginDTO loginDTO);
}
