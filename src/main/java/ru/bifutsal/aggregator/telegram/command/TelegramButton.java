package ru.bifutsal.aggregator.telegram.command;

import ru.bifutsal.aggregator.telegram.TelegramDialogStatusEnum;

/**
 * Created by itimofeev on 09.10.2017.
 */
public class TelegramButton {

	private String text;

	private String command;

	private TelegramDialogStatusEnum status;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public TelegramDialogStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TelegramDialogStatusEnum status) {
		this.status = status;
	}
}
