package de.structuremade.ms.lessonservice.api.security.service;


import de.structuremade.ms.lessonservice.util.JWTUtil;
import de.structuremade.ms.lessonservice.util.database.entity.Permissions;
import de.structuremade.ms.lessonservice.util.database.entity.Role;
import de.structuremade.ms.lessonservice.util.database.entity.User;
import de.structuremade.ms.lessonservice.util.database.repo.RoleRepo;
import de.structuremade.ms.lessonservice.util.database.repo.SchoolRepo;
import de.structuremade.ms.lessonservice.util.database.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserDetailService.class);

    @Autowired
    JWTUtil jwtUtil;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String jwt) throws UsernameNotFoundException {
        LOGGER.info("Get uuid from JWT Token");
        List<GrantedAuthority> auths = new ArrayList<>();
        try {
                LOGGER.info("Get Permissions of Role and add Permissions to User Authorization");
            for (String name : jwtUtil.extractSpecialClaim(jwt, "perms").split(",")) {
                LOGGER.debug(name);
                auths.add(new SimpleGrantedAuthority(name));
            }
            for (GrantedAuthority authority : auths) {
                LOGGER.debug(String.valueOf(authority));
            }
            return new org.springframework.security.core.userdetails.User(jwtUtil.extractIdOrEmail(jwt), "encrypted", auths);
        } catch (Exception e) {
            LOGGER.error("Couldn't find any User", e.fillInStackTrace());
            return new org.springframework.security.core.userdetails.User(jwtUtil.extractIdOrEmail(jwt), "encrypted", new ArrayList<>());

        }
    }
}
