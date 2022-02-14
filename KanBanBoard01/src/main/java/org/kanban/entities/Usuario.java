package org.kanban.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="usuarios")                                

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Usuario{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fullname;
	private String username;
    private String password;
    private String roles;
	public Usuario(String fullname, String username, String password, String roles) {
		super();
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

    
}
