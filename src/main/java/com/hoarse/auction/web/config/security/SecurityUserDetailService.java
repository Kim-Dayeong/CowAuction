package com.hoarse.auction.web.config.security;

import com.hoarse.auction.web.entity.member.Member;
import com.hoarse.auction.web.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class SecurityUserDetailService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optional = Optional.ofNullable(memberRepository.findByUsername(username));
        if(!optional.isPresent()) {
            throw new UsernameNotFoundException(username + " 사용자 없음");
        } else {
            Member member = optional.get();
            return new SecurityUser(member);
        }

    }
}
