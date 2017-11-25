package ru.bifutsal.dao.befutsal.retrofit;

import ru.bifutsal.BitfutsalBotApplication;
import ru.bifutsal.dao.befutsal.bo.LoginRequestBo;
import ru.bifutsal.dao.befutsal.bo.LoginResponseBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by itimofeev on 25.11.2017.
 */
@Component
public class CookieHeaderStore {

	@Autowired
	private IAuthBefutsalService authBefutsalService;

	private String authCookie;

	@PostConstruct
	private void post() {
		LoginRequestBo requestBo = new LoginRequestBo();
		requestBo.setEmail("telegram");
		requestBo.setPassword("dGVsZWdyYW0=");
		requestBo.setUid("lol");
		retrofit2.Response<LoginResponseBo> loginResponseBoResponse = null;
		try {
			loginResponseBoResponse = authBefutsalService.login(requestBo).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		authCookie = loginResponseBoResponse.headers().get("Set-Cookie");
	}

	public String getAuthCookie() {
		return authCookie;
	}

}
