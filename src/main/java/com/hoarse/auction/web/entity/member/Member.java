package com.hoarse.auction.web.entity.member;

import com.hoarse.auction.web.entity.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "Member")
public class Member {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MemberId")
    private Long id;

    @Column
    private String name;

    @Column(length = 11, unique = true)
    private String phone;

    @Column
    private String password;

    @Column(length = 45, unique = true)
    private String username; //email

    @Enumerated(EnumType.STRING)
    private Role role;


    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

    public boolean checkPassword(PasswordEncoder passwordEncoder, String inputPassword) {

        return passwordEncoder.matches(inputPassword, password);
    }
}
