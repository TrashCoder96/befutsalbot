package ru.bifutsal.aggregator.telegram.view;

import ru.bifutsal.BitfutsalBotApplication;
import ru.bifutsal.aggregator.telegram.TelegramCustomerInfo;
import ru.bifutsal.dao.CustomerDto;
import ru.bifutsal.dao.befutsal.bo.TeamBo;
import ru.bifutsal.dao.befutsal.impl.BefutsalDao;
import ru.bifutsal.dao.repository.CustomerRepository;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by itimofeev on 26.11.2017.
 */
public class TeamsTelegramView extends TelegramView {

	@Override
	public String getText() {
		return "А это список команд";
	}

	@Override
	@Transactional
	public Keyboard getKeyboard() {
		BefutsalDao dao = (BefutsalDao) BitfutsalBotApplication.getApplicationContext().getBean("befutsalDao");
		CustomerRepository customerRepository = (CustomerRepository) BitfutsalBotApplication.getApplicationContext().getBean("customerRepository");
		CustomerDto customerDto = customerRepository.findByExternalId(TelegramCustomerInfo.getUserInformation().getCustomerId());
		List<TeamBo> teamBoList = dao.getTeams();
		List<KeyboardButton> buttons = new ArrayList<>();
		if (TelegramCustomerInfo.getUserCommand().equals("Предыдущая страница") && customerDto.getCurrentPage() > 0) {
			customerDto.setCurrentPage(customerDto.getCurrentPage() - 1);
		}
		if (TelegramCustomerInfo.getUserCommand().equals("Следующая страница") && customerDto.getCurrentPage() < teamBoList.size() / 10) {
			customerDto.setCurrentPage(customerDto.getCurrentPage() + 1);
		}
		if (customerDto.getCurrentPage() > 0) {
			buttons.add(new KeyboardButton("Предыдущая страница"));
		}
		int startPosition = customerDto.getCurrentPage() * 10;
		int endPosition = startPosition + 10 < teamBoList.size() ? startPosition + 10 : startPosition + teamBoList.size() % 10;
		for (int i = startPosition ; i < endPosition; i++) {
			buttons.add(new KeyboardButton(teamBoList.get(i).getTitle()));
		}
		if (customerDto.getCurrentPage() < teamBoList.size() / 10) {
			buttons.add(new KeyboardButton("Следующая страница"));
		}
		KeyboardButton[][] keyboardButtons = new KeyboardButton[buttons.size()][1];
		for (int i = 0; i < buttons.size(); i++) {
			keyboardButtons[i][0] = buttons.get(i);
		}
		customerRepository.save(customerDto);
		return new ReplyKeyboardMarkup(keyboardButtons)
				.selective(true);
	}

	@Override
	public List<String> imageUrls() {
		return null;
	}

	@Override
	public List<String> docUrls() {
		return null;
	}
}
