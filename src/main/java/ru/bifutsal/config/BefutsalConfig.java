package ru.bifutsal.config;

import ru.bifutsal.dao.befutsal.impl.interceptor.BefutsalCookieInterceptor;
import ru.bifutsal.dao.befutsal.retrofit.IAuthBefutsalService;
import ru.bifutsal.dao.befutsal.retrofit.IBefutsalRetrofitService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by itimofeev on 25.11.2017.
 */
@Configuration
public class BefutsalConfig {

	@Autowired
	private BefutsalCookieInterceptor befutsalCookieInterceptor;

	@Bean("auth")
	public OkHttpClient authOkHttpClient() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder()
				.addInterceptor(befutsalCookieInterceptor);
		return builder.build();
	}

	@Bean("normal")
	public OkHttpClient normalOkHttpClient() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		return builder.build();
	}

	@Bean
	public IBefutsalRetrofitService befutsalRetrofitService() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://befutsal.ru/m/")
				.client(authOkHttpClient())
				.addConverterFactory(JacksonConverterFactory.create())
				.build();
		return retrofit.create(IBefutsalRetrofitService.class);
	}

	@Bean
	public IAuthBefutsalService authBefutsalService() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://befutsal.ru/m/")
				.client(normalOkHttpClient())
				.addConverterFactory(JacksonConverterFactory.create())
				.build();
		return retrofit.create(IAuthBefutsalService.class);
	}
}
