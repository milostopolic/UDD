package com.ftn.es.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.es.dto.LoginRequestDTO;
import com.ftn.es.dto.ProfileDTO;
import com.ftn.es.jwt.JwtTokenProvider;
import com.ftn.es.model.Korisnik;
import com.ftn.es.service.KorisnikService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    KorisnikService korisnikRepository;

    @PostMapping(value="/login",
            produces= MediaType.APPLICATION_JSON_VALUE,
            consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginUser, Device device){

        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
        Authentication authentication=authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Korisnik systemUser=(Korisnik) authentication.getPrincipal();

        if(systemUser==null){
            System.out.println("Ne moze da nadje korisnika!");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String jwt=jwtTokenProvider.generateToken(systemUser.getUsername(), device);
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        ProfileDTO profileDTO=new ProfileDTO(authentication.getName(), jwt, authorities);

        return new ResponseEntity<>(profileDTO,HttpStatus.OK);

    }

    @GetMapping("/user/logout")
    public void logout(){
        System.out.println("LOGOUT");
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
    }

    @GetMapping("/getLogged")
    public ResponseEntity<?> getLogged(HttpServletRequest request){

        Korisnik user=this.korisnikRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if(user !=null){

            List<String> authorities = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String jwt=request.getHeader("Authorization").substring(7);
            ProfileDTO profile=new ProfileDTO(user.getId(), user.getUsername(),jwt, authorities);

            return new ResponseEntity<ProfileDTO>(profile, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Fail",HttpStatus.BAD_REQUEST);
        }
    }

}
