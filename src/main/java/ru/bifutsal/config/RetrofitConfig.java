package ru.bifutsal.config;

import ru.bifutsal.api.ClientTeamService;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by itimofeev on 21.10.2017.
 */
@Configuration
public class RetrofitConfig {

	@Bean
	public OkHttpClient client() {
		return new OkHttpClient.Builder()
				.cookieJar(new CookieJar() {

					@Override
					public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
						cookieStore.put(url, cookies);
					}

					@Override
					public List<Cookie> loadForRequest(HttpUrl url) {
						List<Cookie> cookies = cookieStore.get(url);
						return cookies != null ? cookies : new ArrayList<Cookie>();
					}
				})
				.build();
	}

	@Bean
	public ClientTeamService clientTeamService(OkHttpClient client) {
		Retrofit retrofit = new Retrofit.Builder()
				.client(client)
				.addConverterFactory(JacksonConverterFactory.create())
				.baseUrl("http://befutsal.ru/m/")
				.build();
		return retrofit.create(ClientTeamService.class);
	}

}
