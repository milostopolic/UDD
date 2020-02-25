package com.ftn.es.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ftn.es.model.Korisnik;
import com.ftn.es.repositoryjpa.KorisnikRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    KorisnikRepository korisnikRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Korisnik sysUser= korisnikRepository.findByUsername(username);
        if(sysUser == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }else {
            return sysUser;
        }
    }

    // Funkcija pomocu koje korisnik menja svoju lozinku
    public void changePassword(String oldPassword, String newPassword) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();

        if (authenticationManager != null) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
        } else {
            return;
        }
        Korisnik systemUser = (Korisnik) loadUserByUsername(username);
        systemUser.setPassword(passwordEncoder.encode(newPassword));
        
        this.korisnikRepository.save(systemUser);

    }
}
