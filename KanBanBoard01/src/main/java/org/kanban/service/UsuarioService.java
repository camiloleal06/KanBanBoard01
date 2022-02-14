package org.kanban.service;

import java.util.List;
import java.util.Optional;

import org.kanban.dtos.CreaUsuarioDTO;
import org.kanban.dtos.UsuarioDTO;
import org.kanban.entities.Usuario;
import org.kanban.repository.UsuarioRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepositiry usuarioRepository;
   
    
	public List<Usuario> listUsuarios() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	public Optional<Usuario> findOne(int id) {
		return usuarioRepository.findById(id);
	}
	public Usuario save (Usuario u) {
		return usuarioRepository.save(u);
	}
	
	public Usuario SaveUsuario (CreaUsuarioDTO user) {
		Usuario usuario= Usuario.builder()
				.username(user.getUsername())
				.fullname(user.getFullname())
				.password(user.getPassword())
				.roles("USER")
				.build();
			return usuarioRepository.save(usuario);
	}
	
	public void delete(int id) {
		usuarioRepository.deleteById(id);
	}

	public boolean existsById(int id) {
  	 return usuarioRepository.existsById(id);
	}

	public boolean existsByUsername(String username) {
	  	 return usuarioRepository.existsByUsername(username);
		}
	
public Optional<Usuario> ByUsername(String username){
	return usuarioRepository.findByUsername(username);
}
	
}
