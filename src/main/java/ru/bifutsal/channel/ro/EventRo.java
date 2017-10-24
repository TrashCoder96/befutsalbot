package ru.bifutsal.channel.ro;

/**
 * Created by itimofeev on 24.10.2017.
 */
public class EventRo {

	protected String type;

	protected Post object;

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

	public Post getObject() {
		return object;
	}

	public void setObject(Post object) {
		this.object = object;
	}
}
