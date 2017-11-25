package ru.bifutsal.dao.befutsal.retrofit;

import ru.bifutsal.dao.befutsal.bo.TeamsResponseBo;
import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by itimofeev on 25.11.2017.
 */
public interface IBefutsalRetrofitService {

	@GET("teams")
	Call<TeamsResponseBo> teams();

}
