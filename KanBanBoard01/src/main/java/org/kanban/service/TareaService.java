package org.kanban.service;

import java.util.List;
import java.util.Optional;

import org.kanban.dtos.CreaTareaDTO;
import org.kanban.dtos.CreaUsuarioDTO;
import org.kanban.dtos.TareaUsuarioDTO;
import org.kanban.entities.Tarea;
import org.kanban.entities.Usuario;
import org.kanban.repository.TareaRepository;
import org.kanban.repository.UsuarioRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareaService {
	@Autowired
	TareaRepository tareaRepository;
	@Autowired
	UsuarioRepositiry usuarioRepository;
	public List<TareaUsuarioDTO> listTareaUsuario(int user_id) {
		// TODO Auto-generated method stub
		return tareaRepository.listaTareas(user_id);
	}
	
	public List<TareaUsuarioDTO> listTareasDTO(){
		return tareaRepository.listaTareas();
	}

	public boolean existsById(int id) {

		return tareaRepository.existsById(id);
	}
	public boolean existsByTitle(String title) {
		return tareaRepository.existsByTitle(title);
	}

	public Optional<Tarea> findById(int id) {
		// TODO Auto-generated method stub
		return tareaRepository.findById(id);
	}
	
	public Optional<Tarea> findByTitle(String title) {
		// TODO Auto-generated method stub
		return tareaRepository.findByTitle(title);
	}
	
	public Tarea save(Tarea tarea) {
		return tareaRepository.save(tarea);
	}
	
	public Tarea SaveTarea (CreaTareaDTO t) {
		Tarea tarea= Tarea.builder()
				.title(t.getTitle())
				.description(t.getDescription())
				.usuario(usuarioRepository.findById(t.getUsuario_id()).orElse(null))
				.build();
			return tareaRepository.save(tarea);
	}
	
	
	public void delete(int id) {
		tareaRepository.deleteById(id);
	}
	
}
