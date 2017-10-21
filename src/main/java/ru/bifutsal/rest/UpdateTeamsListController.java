package ru.bifutsal.rest;

import ru.bifutsal.api.ClientTeamService;
import ru.bifutsal.rest.ro.EmptyResponseRo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by itimofeev on 21.10.2017.
 */

@RestController
public class UpdateTeamsListController {

	@Autowired
	private ClientTeamService clientTeamService;

	@RequestMapping(value = "/updateTeams")
	public EmptyResponseRo updateTeams() throws IOException {
		clientTeamService.getTeams().execute();
		EmptyResponseRo response = new EmptyResponseRo();
		return response;
	}

}
