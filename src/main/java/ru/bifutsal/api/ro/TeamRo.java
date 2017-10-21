package ru.bifutsal.api.ro;

/**
 * Created by itimofeev on 21.10.2017.
 */
public class TeamRo {

	private Long id_team;

	private String title;

	private Integer founded;

	public Long getId_team() {
		return id_team;
	}

	public void setId_team(Long id_team) {
		this.id_team = id_team;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getFounded() {
		return founded;
	}

	public void setFounded(Integer founded) {
		this.founded = founded;
	}
}
