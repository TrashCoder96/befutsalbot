package ru.bifutsal.aggregator.telegram.command;

import ru.bifutsal.aggregator.telegram.TelegramAggregator;
import ru.bifutsal.aggregator.telegram.TelegramDialogStatusEnum;

import com.pengrad.telegrambot.model.request.Keyboard;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.transaction.annotation.Transactional;
/**
 * Created by itimofeev on 06.10.2017.
 */
@Transactional
public abstract class TelegramView {

	private static final Logger logger = LoggerFactory.getLogger(TelegramView.class);

	@Autowired
	protected TelegramAggregator telegramAggregator;

	public abstract TelegramDialogStatusEnum getStatus();

	public abstract String getText();

	public abstract Keyboard buildKeyboard();

	public abstract boolean check(String commandText, TelegramDialogStatusEnum lastCustomerDialogStatus);

	public abstract void execute(String customerId, String command);

}
