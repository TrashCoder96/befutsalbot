package ru.bifutsal.channel.ro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by itimofeev on 24.10.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentRo {

	private String type;

	private PhotoRo photo;

	private AudioRo audio;

	private VideoRo video;

	private LinkRo link;

	private DocRo doc;

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

	public AudioRo getAudio() {
		return audio;
	}

	public void setAudio(AudioRo audio) {
		this.audio = audio;
	}

	public VideoRo getVideo() {
		return video;
	}

	public void setVideo(VideoRo video) {
		this.video = video;
	}

	public LinkRo getLink() {
		return link;
	}

	public void setLink(LinkRo link) {
		this.link = link;
	}

	public DocRo getDoc() {
		return doc;
	}

	public void setDoc(DocRo doc) {
		this.doc = doc;
	}
}
