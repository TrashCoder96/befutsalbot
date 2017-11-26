package ru.bifutsal.dao.telegram;

import ru.bifutsal.aggregator.telegram.view.TelegramView;

/**
 * Created by itimofeev on 25.11.2017.
 */
public interface ITelegramDao {

	void sendView(TelegramView view);

}
