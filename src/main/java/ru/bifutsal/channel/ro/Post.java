package ru.bifutsal.channel.ro;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by itimofeev on 24.10.2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {

	private String id;

	private String owner_id;

	private String text;

	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
