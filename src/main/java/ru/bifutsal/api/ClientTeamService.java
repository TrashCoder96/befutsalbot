package ru.bifutsal.api;

import ru.bifutsal.api.ro.LoginRequestRo;
import ru.bifutsal.api.ro.TeamListResponseRo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by itimofeev on 21.10.2017.
 */
public interface ClientTeamService {

	@GET("teams")
	Call<TeamListResponseRo> getTeams();

	@POST("login")
	Call login(@Body LoginRequestRo request);

}
