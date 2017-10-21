package ru.bifutsal.rest.ro.service.impl;

import ru.bifutsal.dao.repository.TeamRepository;
import ru.bifutsal.rest.ro.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by itimofeev on 21.10.2017.
 */

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Override
	public void updateTeams() {

	}
}
