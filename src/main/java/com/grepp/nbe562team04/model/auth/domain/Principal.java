package com.grepp.nbe562team04.model.auth.domain;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class Principal extends User {

    public Principal(String username, String password,
        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static Principal createPrincipal(com.grepp.nbe562team04.model.user.entity.User member,
        List<SimpleGrantedAuthority> authorities){
        return new Principal(member.getEmail(), member.getPassword(), authorities);
    }
}