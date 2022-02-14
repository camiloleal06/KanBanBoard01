package org.kanban.service;
import java.util.ArrayList;
import java.util.List;

import org.kanban.entities.Usuario;
import org.kanban.repository.UsuarioRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceDetail implements UserDetailsService {

    @Autowired
    UsuarioRepositiry userRepository;
	
    @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

     Usuario appUser = 
                 userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
	List lista = new ArrayList<>();
	lista.add("USER");
	lista.add("ADMIN");
		
    //Crear El objeto UserDetails que va a ir en sesion y retornarlo.
    UserDetails user = (UserDetails) new User(appUser.getUsername(), appUser.getPassword(), lista);
         return user;
    }
}