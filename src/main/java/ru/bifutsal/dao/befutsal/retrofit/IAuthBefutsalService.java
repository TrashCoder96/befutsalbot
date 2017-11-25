package ru.bifutsal.dao.befutsal.retrofit;

import ru.bifutsal.dao.befutsal.bo.LoginRequestBo;
import ru.bifutsal.dao.befutsal.bo.LoginResponseBo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by itimofeev on 25.11.2017.
 */
public interface IAuthBefutsalService {

	@POST("login")
	Call<LoginResponseBo> login(@Body LoginRequestBo loginRequest);

}
