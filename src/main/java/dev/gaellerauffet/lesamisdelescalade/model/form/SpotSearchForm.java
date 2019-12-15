package dev.gaellerauffet.lesamisdelescalade.model.form;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;


@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SpotSearchForm {

	private String name;
	private String region;
	
	private int nbAreas;
	private int nbRoutes;
	private int nbPitches;
	private String grade;
	
	private int minHeight;
	private int maxHeight;
	
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
	public int getNbAreas() {
		return nbAreas;
	}
	public void setNbAreas(int nbAreas) {
		this.nbAreas = nbAreas;
	}
	public int getNbRoutes() {
		return nbRoutes;
	}
	public void setNbRoutes(int nbRoutes) {
		this.nbRoutes = nbRoutes;
	}
	public int getNbPitches() {
		return nbPitches;
	}
	public void setNbPitches(int nbPitches) {
		this.nbPitches = nbPitches;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getMinHeight() {
		return minHeight;
	}
	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}
	public int getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}
	
	
	
	
}
