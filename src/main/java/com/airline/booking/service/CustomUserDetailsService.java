package com.airline.booking.service;

import com.airline.booking.entity.User;
import com.airline.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(usernameOrEmail)
                .orElseGet(() -> userRepository.findByEmail(usernameOrEmail)
                        .orElseThrow(() ->
                                new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail)
                        ));

        Set<GrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().getName())
        );

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),           // or getEmail(), depending on your auth strategy
                user.getPassword(),
                user.isActive(),              // enabled
                true,                         // accountNonExpired
                true,                         // credentialsNonExpired
                true,                         // accountNonLocked
                authorities
        );
    }

    // Used by JWTAuthenticationFilter
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        Set<GrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority(user.getRole().getName())
        );
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                authorities);
    }


}
