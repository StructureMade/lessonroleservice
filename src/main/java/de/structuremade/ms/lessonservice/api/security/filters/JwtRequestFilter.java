package de.structuremade.ms.lessonservice.api.security.filters;

import de.structuremade.ms.lessonservice.api.security.service.UserDetailService;
import de.structuremade.ms.lessonservice.util.JWTUtil;
import de.structuremade.ms.lessonservice.util.database.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    private UserDetailService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        LOGGER.info("Get AuthHeader");
        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader != null) {
            String jwt = authHeader.substring(7);
            LOGGER.info("Cookies and authHeader aren't null, now checking for required Cookie");
            if (/*!jwtUtil.isTokenInBlacklist(jwt)*/true) {
                if (!jwtUtil.isTokenExpired(jwt)) {
                    LOGGER.info("Validated the JWT, with Reason valid!");
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        LOGGER.info("Init Userdetails");
                        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwt);
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        LOGGER.info("Authenticate user");
                        usernamePasswordAuthenticationToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
