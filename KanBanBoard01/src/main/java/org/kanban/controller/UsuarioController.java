package org.kanban.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kanban.dtos.CreaUsuarioDTO;
import org.kanban.dtos.Msgs;
import org.kanban.dtos.TareaUsuarioDTO;
import org.kanban.entities.Usuario;
import org.kanban.service.TareaService;
import org.kanban.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("kanban/api")
@CrossOrigin
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	TareaService tareaService;

	@ApiOperation(notes = "Listado de Usuarios", value = "Todos los Usuarios", tags = { "Usuarios" })
	@GetMapping("/usuarios")
	public ResponseEntity<?> allUser() {
			List<Usuario> result = usuarioService.listUsuarios();
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay datos registrados");
		} else {
			return ResponseEntity.ok(result);
		}
	}

	@ApiOperation(notes = "Usuario por ID, con parametro de tipo Numerico",
			value = "Buscar Usuario por ID", tags = {"Usuarios" })
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> userById(@PathVariable int id) {
		if (!usuarioService.existsById(id))
			return new ResponseEntity(new Msgs("El usuario con ID " + id + " no existe"), HttpStatus.NOT_FOUND);
		Usuario user = usuarioService.findOne(id).get();
		return new ResponseEntity(user, HttpStatus.OK);
	}


	@ApiOperation(notes = "Crea un nuevo usuario del sistema utilizamos un DTO y enviamos los datos, el passowrd estara encriptado"
			+ " se valida los atributos @NotBlank, devolviendo un mensaje informativo, para asi evitar datos vacios", value = "Crear Usuario", tags = {
					"Usuarios" })
	@PostMapping("/usuario")
	public ResponseEntity<?> createUsuario(@RequestBody CreaUsuarioDTO user) {
		if (StringUtils.isBlank(user.getUsername()))
			return new ResponseEntity(new Msgs("Debe escribir el Nombre de Usuario"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(user.getPassword()))
	    	return new ResponseEntity(new Msgs("Debe escribir el password"), HttpStatus.BAD_REQUEST);
	     if(usuarioService.existsByUsername(user.getUsername()))
	    	 return new ResponseEntity(new Msgs("El usuario ya existe en el sistema"), HttpStatus.BAD_REQUEST);
	    // 	UsuarioDTO usuario = converterUsuarioDto.convertir(usuarioService.SaveUsuario(user));
		return new ResponseEntity(usuarioService.SaveUsuario(user), HttpStatus.OK);
	}

	@ApiOperation(notes = "Eliminar un Usuario, para fines de prueba nos permitimos eliminar, "
			+ "no es buena practica eliminar información de una base de datos", value = "Eliminar Usuario", tags = {
					"Usuarios" })
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable("id") int id) {
		if (!usuarioService.existsById(id))
			return new ResponseEntity(new Msgs("El usuario con ID " + id + " no existe"), HttpStatus.NOT_FOUND);
		if(tareaService.listTareaUsuario(id).isEmpty())
		    return new ResponseEntity(new Msgs("No se puede eliminar usuarios que contengan Tareas"), HttpStatus.NOT_FOUND);	
		usuarioService.delete(id);
		return new ResponseEntity(new Msgs("Usuario eliminado"), HttpStatus.OK);
	}

	@ApiOperation(notes = "Obtenermos Las Tareas por ID de Usuario,"
			+ " si tiene tareas mostramos datos Usuario y datos de sus Tareas, si no tiene tareas informamos ", value = "Buscar Tareas del usuario por ID", tags = {
					"Usuarios" })
	@GetMapping("/usuariotareas/{user_id}")
	public ResponseEntity<?> TareaByUserId(@PathVariable("user_id")  int user_id) {
		if (!usuarioService.existsById(user_id) ){
			return new ResponseEntity(new Msgs("El usuario con ID " +user_id + " no existe"), HttpStatus.NOT_FOUND);
		}
		List<TareaUsuarioDTO> lista = tareaService.listTareaUsuario(user_id);
		if (lista.isEmpty())
			return new ResponseEntity(new Msgs("El usuario con ID " + user_id + " no tiene tareas asignada"),
					HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(lista);
	}
	
	
	@ApiOperation(notes = "Actualizar un usuario del sistema utilizamos un DTO a los atributos @NotBlank, devolviendo un mensaje informativo, para asi evitar datos vacios", value = "Crear Usuario", tags = {
					"Usuarios" })
	@PutMapping("/usuario/{id}")
	public ResponseEntity<?> updateUsuario(@PathVariable("id")int id ,@RequestBody CreaUsuarioDTO user) {
		if(!usuarioService.existsById(id))
            return new ResponseEntity(new Msgs("Usuario no existe"), HttpStatus.NOT_FOUND);
	   if(usuarioService.existsByUsername(user.getUsername()))
	    	 return new ResponseEntity(new Msgs("El usuario"+user.getUsername()+" ya existe en el sistema"), HttpStatus.BAD_REQUEST);
	   if (StringUtils.isBlank(user.getUsername()))
			return new ResponseEntity(new Msgs("Debe escribir el nuevo nombre de Usuario"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(user.getPassword()))
	    	return new ResponseEntity(new Msgs("Debe escribir el nuevo password"), HttpStatus.BAD_REQUEST);

		Usuario usuario = usuarioService.findOne(id).get();
        usuario.setFullname(user.getFullname());
        usuario.setPassword(user.getPassword());
        usuario.setUsername(user.getUsername());
        usuario.setRoles(user.getRol());
		return new ResponseEntity(usuarioService.save(usuario), HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
