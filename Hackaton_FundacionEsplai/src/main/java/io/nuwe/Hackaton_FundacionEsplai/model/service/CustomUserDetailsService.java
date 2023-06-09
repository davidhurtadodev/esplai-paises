package io.nuwe.Hackaton_FundacionEsplai.model.service;

import io.nuwe.Hackaton_FundacionEsplai.model.domain.Role;
import io.nuwe.Hackaton_FundacionEsplai.model.exception.UserNotFoundException;
import io.nuwe.Hackaton_FundacionEsplai.model.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserRepository userRepository;

    @Autowired
    public CustomUserDetailsService (IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        io.nuwe.Hackaton_FundacionEsplai.model.domain.User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User's email not found or incorrect"));

        Role role = user.getRole();

        return new User(user.getEmail(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(role.getName())));
    }

}
