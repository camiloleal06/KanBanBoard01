package org.kanban;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.kanban.entities.Usuario;
import org.kanban.repository.UsuarioRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UsuarioTest {
    @Autowired
	UsuarioRepositiry usuarioService;
	
    
	@Test
	public void TestExistenUsuarios(){
	 List<Usuario> lista = usuarioService.findAll();
	 assertThat(lista).size().isGreaterThan(0);
	}
	
	@Test
	public void TestGuardarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setFullname("CAMILO LEAL PATIÑO");
		usuario.setPassword("2334546377857");
		usuario.setRoles("USER");
		usuario.setUsername("camiloleal");
		Usuario usuarioSaved = usuarioService.save(usuario);
		org.junit.jupiter.api.Assertions.assertNotNull(usuarioSaved);
	}
	
	@Test
	public void TestUsuarioByIdExiste() {
	 String username = "camiloleal";
	 Usuario usuario = usuarioService.findById(6).get();
	 assertThat(usuario.getUsername()).isEqualTo(username);
	}
	
	@Test
	public void TestUsuarioByIdNulo() {
	 Usuario usuario = usuarioService.findById(2).get();
	 org.junit.jupiter.api.Assertions.assertNull(usuario);
	}
	
	@Test
	 public void TestUpdateUsuarioById() {
	 String fullname = "Nancy Alvarez";
	 Usuario usuario = new Usuario();
	 usuario.setFullname(fullname);
	 usuario.setPassword("2334546377857");
	 usuario.setRoles("USER");
	 usuario.setUsername("nanpao97");
	 usuario.setId(2);
	 usuarioService.save(usuario);
	 Usuario usuarioUpdated = usuarioService.findById(2).get();
	 assertThat(usuarioUpdated.getFullname()).isEqualTo(fullname);

	}
	
	@Test
	public void TestEliminarUsuario() {
		int id=2;
		boolean existeUsuarioPorEliminar = usuarioService.findById(id).isPresent();
		usuarioService.deleteById(id);
	    org.junit.jupiter.api.Assertions.assertTrue(existeUsuarioPorEliminar);
	}
	

	
}
