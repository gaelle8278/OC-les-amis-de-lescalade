package dev.gaellerauffet.lesamisdelescalade.model.form;

import org.springframework.stereotype.Component;

@Component
public class SpotSearchForm {

	private String name;
	private String region;
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
