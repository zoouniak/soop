package com.example.soop.user.service;

import com.example.soop.global.exception.ExceptionCode;
import com.example.soop.global.exception.UserException;
import com.example.soop.user.domain.User;
import com.example.soop.user.domain.repository.UserRepository;
import com.example.soop.user.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse getUserInfo(final Long userId) {
        System.out.println(userId);
        User user = findUser(userId);
        return new UserResponse(user.getId(),
                user.getNickname(),
                user.getPhone());
    }

    @Transactional
    public void saveUserPhone(final Long userId, final String phone) {
        User user = findUser(userId);
        user.updatePhone(phone);
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserException(ExceptionCode.NO_SUCH_USER));
    }
}
