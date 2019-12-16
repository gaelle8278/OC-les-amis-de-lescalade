package dev.gaellerauffet.lesamisdelescalade.model;

import java.util.Collection;

import javax.persistence.*;

@Entity
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
  
    private String role;
    
    private String label;
    
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
    
	
    
}
