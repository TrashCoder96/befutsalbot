package ru.bifutsal.channel.ro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by vsharanin on 06.11.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoRo {

	private String player;

	//пипец, нам приходит только фотка обложки
	private String photo_130;

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getPhoto_130() {
		return photo_130;
	}

	public void setPhoto_130(String photo_130) {
		this.photo_130 = photo_130;
	}
}
