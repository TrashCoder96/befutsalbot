package ru.bifutsal.dao.befutsal.impl;

import ru.bifutsal.dao.befutsal.IBefutsalDao;
import ru.bifutsal.dao.befutsal.bo.TeamBo;
import ru.bifutsal.dao.befutsal.retrofit.IBefutsalRetrofitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by itimofeev on 25.11.2017.
 */

@Component
public class BefutsalDao implements IBefutsalDao {

	@Autowired
	private IBefutsalRetrofitService befutsalRetrofitService;

	@Override
	public List<TeamBo> getTeams() {
		try {
			return befutsalRetrofitService.teams().execute().body().getTeams();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
