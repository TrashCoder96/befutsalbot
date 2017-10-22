package ru.bifutsal.api;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by itimofeev on 21.10.2017.
 */
@Component
public class CookieStore {

	private Map<HttpUrl, List<Cookie>> cookieMap = new HashMap<>();

	public Map<HttpUrl, List<Cookie>> getCookieMap() {
		return cookieMap;
	}

	public void setCookieMap(Map<HttpUrl, List<Cookie>> cookieMap) {
		this.cookieMap = cookieMap;
	}
}
