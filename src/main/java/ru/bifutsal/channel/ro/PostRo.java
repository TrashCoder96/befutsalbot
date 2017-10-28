package ru.bifutsal.channel.ro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by itimofeev on 24.10.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostRo {

	private String id;

	private String owner_id;

	private String text;

	private List<AttachmentRo> attachments;

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

	public List<AttachmentRo> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<AttachmentRo> attachments) {
		this.attachments = attachments;
	}
}
