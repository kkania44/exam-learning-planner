package education.pl.planner.core.security;

import education.pl.planner.app.user.query.UserQueryDto;
import education.pl.planner.app.user.query.UserQueryRepository;
import education.pl.planner.app.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
class CustomUserDetailsService implements UserDetailsService {

    private final UserQueryRepository userQuery;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserQueryDto user = userQuery.findByUsername(username)
                .orElseThrow(() -> new BadRequestException("Bad credentials"));

        return new User(user.getUsername(), user.getPassword(), getAuthorities("ROLE_" +user.getRole()));
    }

    private Collection<SimpleGrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }


}
