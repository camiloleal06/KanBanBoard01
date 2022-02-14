package org.kanban;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.kanban.dtos.TareaUsuarioDTO;
import org.kanban.entities.Tarea;
import org.kanban.entities.Usuario;
import org.kanban.repository.TareaRepository;
import org.kanban.repository.UsuarioRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;




@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TareaTest {


         @Autowired
		TareaRepository tareaRepository;
         @Autowired
		UsuarioRepositiry usuarioRepository;
	    
		@Test
		public void TestExistenTareas(){
		 List<Tarea> lista = tareaRepository.findAll();
		 assertThat(lista).size().isGreaterThan(0);
		}
		
		@Test
		@Rollback(false)
		public void TestGuardarTarea() {
			int usuario_id = 2;
			Tarea tarea = new Tarea();
			tarea.setTitle("DIRECCIONAMIENTO");
			tarea.setDescription("DIRECCIONAR TODAS LAS IP");
	        Usuario usuario = usuarioRepository.getOne(usuario_id);
			tarea.setUsuario(usuario);
			boolean existeUsuario = usuarioRepository.findById(usuario_id).isPresent();
		    Tarea tareaSaved = tareaRepository.save(tarea);
		    org.junit.jupiter.api.Assertions.assertTrue(existeUsuario);
		    org.junit.jupiter.api.Assertions.assertNotNull(tareaSaved);
		}
		
		@Test
		public void TareaByIdExiste() {
		 int id =1; String titulo = "DIRECCIONAMIENTO";
         Tarea tarea = tareaRepository.findById(id).get();		 
		 assertThat(tarea.getTitle()).isEqualTo(titulo);
		}
		
		@Test
		public void TareaExistePorUsuarioExiste() {
		 int user_id =2;  
		 List<TareaUsuarioDTO> tarea = tareaRepository.listaTareas(user_id);
		 assertThat(tarea).size().isGreaterThan(0);
		}
		
		
	
}
		