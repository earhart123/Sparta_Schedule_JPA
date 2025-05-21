package com.sparta.scheduleJPA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id값 자동 증가
    @Column(unique = true, nullable = false, name = "user_id")
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Email
    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 16)
    private String password;

    /**
     * 기본생성자
     */
    public User() {}
    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
