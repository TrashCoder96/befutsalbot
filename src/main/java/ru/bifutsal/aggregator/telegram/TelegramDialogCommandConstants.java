package ru.bifutsal.aggregator.telegram;

/**
 * Created by vsharanin on 15.10.2017.
 */

public class TelegramDialogCommandConstants {

	public static final String START = "/start";

	// а дальше то что приходит с кастомной клавиатуры у нас считается либо cmd либо текстом (различаем по TelegramDialogStatusEnum)
	public static final String TEAM = "Команда";
	public static final String LIGA = "Лига";
	public static final String TIME = "Время уведомления";
	public static final String COMPETITOR = "Турнир";
	public static final String LIGA_AND_ZONE = "Лига и зона";
	public static final String SKIP = "Пропустить";
	public static final String ADD = "Добавить еще";
	public static final String NEXT = "Далее";

}
