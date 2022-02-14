package org.kanban.controller;

import java.util.List;

import org.kanban.dtos.CreaTareaDTO;
import org.kanban.dtos.Msgs;
import org.kanban.dtos.TareaUsuarioDTO;
import org.kanban.entities.Tarea;
import org.kanban.entities.Usuario;
import org.kanban.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("kanban/api")
@CrossOrigin
public class TareaController {

@Autowired
TareaService tareaService;

@ApiOperation(notes="Listado de todas las TareasDTO, "
		+ "se utiliza un DTO para mostrar los datos de las tareas, "
		+ "evitando traer datos innecesario y componiendo un JSON", value="Todos las Tareas",tags = { "Tareas"})
@GetMapping("/tareas")
public ResponseEntity<?> allTareas() {
	List<TareaUsuarioDTO> result = tareaService.listTareasDTO();
	if (result.isEmpty()) {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay datos registrados");
	} else {
		return ResponseEntity.ok(result);
	}
  }

@ApiOperation(notes="Tarea por ID, con parametro de tipo Numerico", 
value="Buscar Tarea por ID",tags = { "Tareas"})
@GetMapping("/tareaid/{id}")
public ResponseEntity<Usuario>TareaById(@PathVariable int id) {
	 if(!tareaService.existsById(id))
         return new ResponseEntity(new Msgs("La Tarea con el ID "+id+" no existe"), HttpStatus.NOT_FOUND);
    Tarea tarea = tareaService.findById(id).get();
     return new ResponseEntity(tarea, HttpStatus.OK); 
}

@ApiOperation(notes="Tarea por Titulo, con parametro de tipo String", 
value="Buscar Tarea por Titulo",tags = { "Tareas"})
@GetMapping("/tareatitle/{title}")
public ResponseEntity<Usuario>TareaById(@PathVariable String title) {
	 if(!tareaService.existsByTitle(title))
         return new ResponseEntity(new Msgs("La Tarea "+title+" no existe"), HttpStatus.NOT_FOUND);
    Tarea tarea = tareaService.findByTitle(title).get();
     return new ResponseEntity(tarea, HttpStatus.OK); 
}

@ApiOperation(notes = "Crea un nuevo Tarea en el sistema utilizamos un DTO y enviamos los datos, "
		+ " devolviendo un mensaje informativo, para asi evitar datos vacios", value = "Crear Tarea", tags = {
				"Tareas" })
@PostMapping("/tarea")
public ResponseEntity<Tarea> nuevaTarea (CreaTareaDTO tarea) {

return ResponseEntity.status(HttpStatus.CREATED).body(tareaService.SaveTarea(tarea));
}

@ApiOperation(notes = 
"Eliminar tarea, para fines de prueba nos permitimos eliminar! "
, value = "Eliminar Tarea", tags = {
				"Tareas" })
@DeleteMapping("/tarea/{id}")
public ResponseEntity<?> eliminarTarea(@PathVariable("id") int id) {
	if (!tareaService.existsById(id))
		return new ResponseEntity(new Msgs("La Tarea con ID " + id + " no existe"), HttpStatus.NOT_FOUND);
		tareaService.delete(id);
	return new ResponseEntity(new Msgs("Tarea Eliminada"), HttpStatus.OK);
}

}


