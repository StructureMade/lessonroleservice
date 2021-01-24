package de.structuremade.ms.lessonservice.api.security.service;


import de.structuremade.ms.lessonservice.util.JWTUtil;
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

    @Autowired
    private UserRepo userRepositorie;

    @Autowired
    private SchoolRepo schoolRepository;

    @Autowired
    private RoleRepo roleRepository;

    @Autowired
    JWTUtil jwtUtil;

    private final Logger LOGGER = LoggerFactory.getLogger(UserDetailService.class);

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String jwt) throws UsernameNotFoundException {
        LOGGER.info("Get uuid from JWT Token");
        String uuid = jwtUtil.extractIdOrEmail(jwt);
        String schoolid = jwtUtil.extractSpecialClaim(jwt, "schoolid");
        User user = null;
        List<Permissions> permissions = new ArrayList<>();
        List<GrantedAuthority> auths = new ArrayList<>();
        try {
            LOGGER.info("Get User");
            user = userRepositorie.findAllById(uuid);
            if (user != null) {
                LOGGER.info("Get User- and Schoolroles");
                List<Role> userRoles = user.getRoles();
                List<Role> schoolRoles = roleRepository.findAllBySchool(schoolRepository.findAllById(schoolid));
                LOGGER.info("Check if User have an Role which have access to School");
                for (Role schoolRole : schoolRoles) {
                    for (Role userRole : userRoles) {
                        if (userRole.getId().equals(schoolRole.getId())) {
                            LOGGER.info("User have access to an Role");
                            permissions.addAll(userRole.getPermissions());
                        }
                    }
                }
                LOGGER.info("Get Permissions of Role and add Permissions to User Authorization");
                for (Permissions permission : permissions) {
                    LOGGER.debug(permission.getId());
                    auths.add(new SimpleGrantedAuthority(permission.getName()));
                }
                for (GrantedAuthority authority : auths) {
                    LOGGER.debug(String.valueOf(authority));
                }
                return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), auths);
            } else {
                LOGGER.info("User is null");
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("Couldn't find any User", e.fillInStackTrace());
            if (user != null) {
                return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
            }
            return null;
        }
    }
}
