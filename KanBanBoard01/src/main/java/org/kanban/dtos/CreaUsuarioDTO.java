package org.kanban.dtos;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class CreaUsuarioDTO {
	
	@NotBlank
	private String fullname;
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	private String rol;
	
}
