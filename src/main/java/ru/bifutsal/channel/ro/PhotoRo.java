package ru.bifutsal.channel.ro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by itimofeev on 24.10.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoRo {

	private String photo_130;

	public String getPhoto_130() {
		return photo_130;
	}

	public void setPhoto_130(String photo_130) {
		this.photo_130 = photo_130;
	}
}
