package org.kanban.dtos;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder

@NoArgsConstructor
public class UsuarioDTO {
		private String fullname;
		private String password;
		private String username;
		public UsuarioDTO(String fullname, String password, String username) {
			super();
			this.fullname = fullname;
			this.password = password;
			this.username = username;
		}

}
