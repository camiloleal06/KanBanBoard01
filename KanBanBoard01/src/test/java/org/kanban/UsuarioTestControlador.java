package org.kanban;

import org.junit.jupiter.api.Test;
import org.kanban.controller.UsuarioController;
import org.kanban.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
@WebMvcTest(UsuarioController.class)
public class UsuarioTestControlador {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UsuarioService usuarioService;

   @Test
   void testUsuariosById() {

	   
   }

}
