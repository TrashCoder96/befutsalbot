package ru.bifutsal.channel.ro;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by itimofeev on 24.10.2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachmentRo {

	private String type;

	private PhotoRo photo;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PhotoRo getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoRo photo) {
		this.photo = photo;
	}
}
