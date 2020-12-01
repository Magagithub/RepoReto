package com.everis.gatewaySecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value ="/user/{id}" )
    public Mono<User> getId(@PathVariable("id") String id){
        return userRepository.findById(id);
    }

    @PostMapping(value = "/user/add")
    public Mono<User> add(@RequestBody User u){
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        List<SimpleGrantedAuthority> listaR = new ArrayList<>();
        listaR.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        u.setRoles(listaR);
        return userRepository.save(u);}

    @GetMapping(value ="/user/list" )
    public Flux<User> get(){
        return userRepository.findAll();
    }




}
