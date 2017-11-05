package ru.bifutsal.channel.ro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by vsharanin on 06.11.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoRo {

	private String player;

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}
}
