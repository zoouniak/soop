package com.example.soop.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String loginId;

    @Column
    private String nickname;

    @Column
    private String phone;

    @Column
    private String wallet;

    @Column
    private String privateKey;

    @Builder
    public User(String loginId, String nickname) {
        this.loginId = loginId;
        this.nickname = nickname;
    }

    public void updatePhone(String phone) {
        this.phone = phone;
    }
}
