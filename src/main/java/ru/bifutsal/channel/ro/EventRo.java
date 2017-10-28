package ru.bifutsal.channel.ro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by itimofeev on 24.10.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventRo {

	protected String type;

	protected PostRo object;

	protected String group_id;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public PostRo getObject() {
		return object;
	}

	public void setObject(PostRo object) {
		this.object = object;
	}
}
