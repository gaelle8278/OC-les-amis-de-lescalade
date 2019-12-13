package dev.gaellerauffet.lesamisdelescalade.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@DynamicUpdate
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "first_name")
	@NotBlank(message = "Veuillez saisir un prénom")
	private String firstName;

	@NotBlank(message = "Veuillez saisir un nom")
	@Column(name = "last_name")
	private String lastName;

	@NotBlank(message = "L''adresse e-mail est obligatoire")
	private String email;
	
	private String pseudo;
	private String password;
	private String phone;
	private String city;
	
	@Column(name = "postal_code")
	private String postalCode;

	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean checkedCGU = false;
	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean active = true;
	
	@Column(name = "created_at",updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@OneToMany(mappedBy="user")
    private List<Comment> listComment;
	
	@OneToMany(mappedBy="user")
    private List<Spot> listSpots;
	
	@OneToMany(mappedBy="user")
    private List<Booking> listBookings;
	
	@OneToMany(mappedBy="user")
    private List<Guidebook> listGuidebooks;
	
	@ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private  Collection<Role> roles;

	public User() {}

	
	@Override
	public String toString() {
		return String.format("Utilisateur[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String region) {
		this.city = region;
	}

	public boolean isCheckedCGU() {
		return checkedCGU;
	}

	public void setCheckedCGU(boolean isCheckedCGU) {
		this.checkedCGU = isCheckedCGU;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean isActive) {
		this.active = isActive;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public List<Comment> getListComment() {
		return listComment;
	}


	public void setListComment(List<Comment> listComment) {
		this.listComment = listComment;
	}


	public List<Spot> getListSpots() {
		return listSpots;
	}


	public void setListSpots(List<Spot> listSpots) {
		this.listSpots = listSpots;
	}


	public List<Booking> getListBookings() {
		return listBookings;
	}


	public void setListBookings(List<Booking> listBookings) {
		this.listBookings = listBookings;
	}


	public List<Guidebook> getListGuidebooks() {
		return listGuidebooks;
	}


	public void setListGuidebooks(List<Guidebook> listGuidebooks) {
		this.listGuidebooks = listGuidebooks;
	}


	public Collection<Role> getRoles() {
		return roles;
	}


	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	
	

}
