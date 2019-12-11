package dev.gaellerauffet.lesamisdelescalade.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String status;
	
	@Column(name = "status_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime statusDate;
	
	@Column(name = "created_at",updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
    private User user;
	
	@ManyToOne
	@JoinColumn(name = "guidebook_id", nullable = true)
    private Guidebook guidebook;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(LocalDateTime statusDate) {
		this.statusDate = statusDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Guidebook getGuidebook() {
		return guidebook;
	}

	public void setGuidebook(Guidebook guidebook) {
		this.guidebook = guidebook;
	}

	
	

}