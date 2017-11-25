package ru.bifutsal.dao.befutsal.impl.interceptor;

import ru.bifutsal.dao.befutsal.retrofit.CookieHeaderStore;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by itimofeev on 25.11.2017.
 */

@Component
public class BefutsalCookieInterceptor implements Interceptor {

	@Autowired
	private CookieHeaderStore cookieHeaderStore;

	@Override
	public Response intercept(Chain chain) throws IOException {
		return chain.proceed(chain.request()
				.newBuilder()
				.header("Cookie", cookieHeaderStore.getAuthCookie())
				.build());
	}


}
