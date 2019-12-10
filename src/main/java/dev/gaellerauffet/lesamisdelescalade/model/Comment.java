package dev.gaellerauffet.lesamisdelescalade.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String content;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	//@TODO enforce default value to test saving of comment entity (must be replace when there will be session/authentification)
	@Column(name = "user_id")
	private int userID = 1;
	
	//@TODO enforce default value to test saving of comment entity (must be replace when there will associated to spot page)
	@Column(name = "spot_id")
	private int spotId = 1;
	
	public Comment() {};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getSpotId() {
		return spotId;
	}

	public void setSpotId(int spotId) {
		this.spotId = spotId;
	}
	
	
	

}
