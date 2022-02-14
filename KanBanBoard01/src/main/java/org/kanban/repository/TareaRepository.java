package org.kanban.repository;

import java.util.List;
import java.util.Optional;

import org.kanban.dtos.TareaUsuarioDTO;
import org.kanban.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TareaRepository extends JpaRepository<Tarea, Integer> {
	@Query("SELECT new org.kanban.dtos.TareaUsuarioDTO (t.id,t.title, t.description,u.fullname,u.id )"
			+ " FROM Tarea t JOIN t.usuario u where u.id=?1")
	public List<TareaUsuarioDTO> listaTareas(int id);
	
	@Query("SELECT new org.kanban.dtos.TareaUsuarioDTO (t.id,t.title, t.description,u.fullname,u.id )"
	+ " FROM Tarea t JOIN t.usuario u")
	public List<TareaUsuarioDTO> listaTareas();
	
	boolean existsByTitle(String title);
	Optional<Tarea> findByTitle(String title);
}
