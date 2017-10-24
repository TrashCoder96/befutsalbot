package ru.bifutsal.channel.ro;

/**
 * Created by itimofeev on 24.10.2017.
 */
public enum EventTypeEnum {

	WALL_POST_NEW("wall_post_new"),
	CONFIRMATION("confirmation");

	private final String value;

	EventTypeEnum(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	public static EventTypeEnum value(String value) {
		for (EventTypeEnum s : values()) {
			if (s.value().equals(value)) return s;
		}
		return null;
	}

}
