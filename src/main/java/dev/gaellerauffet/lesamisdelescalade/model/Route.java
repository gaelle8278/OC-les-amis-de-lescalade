package dev.gaellerauffet.lesamisdelescalade.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Route {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String number;
	private String notice;
	@Column(name = "nb_spits")
	private Integer nbSpits;
	private String grade;
	
	@Column(name = "created_at",updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	/*@Column(name = "area_id")
	private int areaId;*/
	@ManyToOne
	@JoinColumn(name = "area_id", nullable = true)
    private Area area;
	
	@OneToMany(mappedBy="route")
    private List<Pitch> listPitchs;
	
	

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public Integer getNbSpits() {
		return nbSpits;
	}

	public void setNbSpits(Integer nbSpits) {
		this.nbSpits = nbSpits;
	}

	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<Pitch> getListPitchs() {
		return listPitchs;
	}

	public void setListPitchs(List<Pitch> listPitchs) {
		this.listPitchs = listPitchs;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	
	

}
