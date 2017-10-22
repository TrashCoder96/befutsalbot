package ru.bifutsal.rest;

import ru.bifutsal.api.ClientTeamService;
import ru.bifutsal.api.ro.LoginRequestRo;
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
		LoginRequestRo loginRequestRo = new LoginRequestRo();
		loginRequestRo.setPassword("035963b147f3b4b278d6dad324c642c6");
		loginRequestRo.setUid("telegramid");
		loginRequestRo.setUsername("telegram");
		//clientTeamService.login(loginRequestRo).execute();
		clientTeamService.getTeams().execute();
		EmptyResponseRo response = new EmptyResponseRo();
		return response;
	}

}
