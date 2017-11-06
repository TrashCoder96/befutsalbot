package ru.bifutsal.aggregator.telegram;

/**
 * Created by itimofeev on 07.10.2017.
 */
public enum TelegramDialogStatusEnum {

	UNKNOWN,
	ADDED_TO_CONTACTS,
	HELLO,
	BLOCKED,
	/*
		На всякий введем такие понятия:
		CUSTOM_KEYBOARD - пусть это будет обычная пользовательская клавиатура
		KEYBOARD_BUTTON - а это наша сгенеренная кнопошная клавиатура для пользователя
	*/
	LISTENING_CUSTOM_KEYBOARD_ON_TEAM,
	LISTENING_KEYBOARD_BUTTON_ON_TEAM,
	LISTENING_KEYBOARD_BUTTON_AFTER_SAVING_TEAM;
	

}
