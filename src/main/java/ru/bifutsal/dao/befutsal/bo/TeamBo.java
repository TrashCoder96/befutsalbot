package ru.bifutsal.dao.befutsal.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by itimofeev on 25.11.2017.
 */
public class TeamBo extends AbstractBo {

	private Integer id_team;

	private String title;

	private Integer founded;

	private String emblem_path;

	public Integer getId_team() {
		return id_team;
	}

	public void setId_team(Integer id_team) {
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

	public String getEmblem_path() {
		return emblem_path;
	}

	public void setEmblem_path(String emblem_path) {
		this.emblem_path = emblem_path;
	}
}
