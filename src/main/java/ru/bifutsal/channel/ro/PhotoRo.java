package ru.bifutsal.channel.ro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by itimofeev on 24.10.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoRo {

	private String photo_807;

	public String getPhoto_807() {
		return photo_807;
	}

	public void setPhoto_807(String photo_807) {
		this.photo_807 = photo_807;
	}
}
