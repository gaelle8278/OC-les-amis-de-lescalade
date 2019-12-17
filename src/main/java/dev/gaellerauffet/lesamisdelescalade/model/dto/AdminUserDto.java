package dev.gaellerauffet.lesamisdelescalade.model.dto;

import java.util.Collection;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import dev.gaellerauffet.lesamisdelescalade.model.Role;
import dev.gaellerauffet.lesamisdelescalade.model.User;

@Component
public class AdminUserDto {
	
	// injection doesn't work why ?
	/*@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;*/
	
    
    private Integer id;
	
	@NotBlank(message = "Veuillez saisir un prénom.")
	private String firstName;

	@NotBlank(message = "Veuillez saisir un nom.")
	private String lastName;

	@NotBlank(message = "L''adresse e-mail est obligatoire.")
	@Email(message = "L''adresse e-mail n'est pas valide.")
	private String email;
	
	private String pseudo;
	
	@NotBlank(message = "Veuillez un mot de passe.")
	@Size(min = 6, max = 12, message = "Le mot de passe doit comporter entre 6 et 12 caractères.")
	private String password;
	
	@Size(min = 10, max = 10, message = "Le numéro de téléphone doit comporter 10 chiffres.")
	private String phone;
	
	private String city;
	
	@Size(min = 5, max = 5, message = "Le code postal doit comporter 5 chiffres.")
	private String postalCode;
	
	private  Collection<Role> roles;
	
	private boolean checkedCGU = true;
	
	
	
	public String getUpdatePassword(User oldUser) {
		String updatedPassword = null;
		if( ! StringUtils.isBlank(this.password) ) {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			updatedPassword = bCryptPasswordEncoder.encode(this.password);
		} else {
			updatedPassword = oldUser.getPassword();
		}
		
		return updatedPassword;
	}
	
	public String getNewPassword() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String updatedPassword = bCryptPasswordEncoder.encode(this.password);
		
		
		return updatedPassword;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return "";
	}

	public void setPassword(String password) {
		
		this.password  = password; 
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

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public boolean isCheckedCGU() {
		return checkedCGU;
	}

	public void setCheckedCGU(boolean checkedCGU) {
		this.checkedCGU = checkedCGU;
	}
	
	
	
	

}
