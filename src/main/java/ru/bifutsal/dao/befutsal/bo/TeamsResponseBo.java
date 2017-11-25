package ru.bifutsal.dao.befutsal.bo;

import java.util.List;

/**
 * Created by itimofeev on 25.11.2017.
 */
public class TeamsResponseBo extends AbstractBo {

	private List<TeamBo> teams;

	public List<TeamBo> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamBo> teams) {
		this.teams = teams;
	}
}
