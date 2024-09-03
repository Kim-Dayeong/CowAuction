package com.hoarse.auction.web.config.security;
import com.hoarse.auction.web.entity.member.Member;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class SecurityUser extends User{

    private final Member member;

    public SecurityUser(Member member){
        super(member.getId().toString(), member.getPassword(),
                AuthorityUtils.createAuthorityList(member.getRole().toString()));
        this.member = member;
    }

    public Member getMember() {
        return member;
    }





}
