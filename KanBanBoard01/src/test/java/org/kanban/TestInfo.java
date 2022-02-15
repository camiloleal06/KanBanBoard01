package org.kanban;

import org.kanban.entities.Usuario;

import com.google.common.base.Optional;

public class TestInfo {

	public static Optional<Usuario> CrearUsuario(){
		return Optional.of(new Usuario("Orfa Lia Patiño","orfa2022","OrfaPass","USER"));
	}
	
	
}
