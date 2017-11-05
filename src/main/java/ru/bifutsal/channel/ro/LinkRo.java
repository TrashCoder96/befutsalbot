package ru.bifutsal.channel.ro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by vsharanin on 06.11.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkRo {

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
