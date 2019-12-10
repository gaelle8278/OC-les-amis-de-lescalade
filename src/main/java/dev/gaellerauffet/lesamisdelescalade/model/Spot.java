package dev.gaellerauffet.lesamisdelescalade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Spot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Le nom est obligatoire")
	private String name;
	@NotBlank(message = "La description est obligatoire")
	private String description;
	private String notice;
	private String type;
	private String region;
	private String orientation;
	@Column(name = "min_height")
	private String minHeight;
	@Column(name = "max_height")
	private String maxHeight;
	@Column(name = "min_grade")
	private String minGrade;
	@Column(name = "max_grade")
	private String maxGrade;
	@Column(name = "nb_routes")
	private String nbRoutes;
	
	private String country = "France";
	//@TODO enforce default value to test saving of spot entity 
	@Column(name = "user_id")
	private int userId = 1;
	

	public Spot() {}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public String getMinHeight() {
		return minHeight;
	}
	public void setMinHeight(String minHeight) {
		this.minHeight = minHeight;
	}
	public String getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(String maxHeight) {
		this.maxHeight = maxHeight;
	}
	public String getMinGrade() {
		return minGrade;
	}
	public void setMinGrade(String minGrade) {
		this.minGrade = minGrade;
	}
	public String getMaxGrade() {
		return maxGrade;
	}
	public void setMaxGrade(String maxGrade) {
		this.maxGrade = maxGrade;
	}
	public String getNbRoutes() {
		return nbRoutes;
	}
	public void setNbRoutes(String nbRoutes) {
		this.nbRoutes = nbRoutes;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
}
