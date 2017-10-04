package ru.bifutsal.admin;

import ru.bifutsal.aggregator.TelegramAggregator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;


/**
 * Created by itimofeev on 04.10.2017.
 */

@UIScope
@SpringView(name = TelegramSettingsView.NAME)
@Transactional
public class TelegramSettingsView extends VerticalLayout implements View {

	@Autowired
	private TelegramAggregator telegramAggregator;

	public static final String NAME = "TelegramSettingsView";

	private Button saveBotKeyButton = new Button("Принять значение");
	private TextField keyField = new TextField("Поле лучше не оставлять пустым =(");

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
	}

	private void update() {
		keyField.setValue(telegramAggregator.getCurrentKeyValue());
	}

	@PostConstruct
	private void init() {
		saveBotKeyButton.setWidth(300, Unit.PIXELS);
		keyField.setWidth(700, Unit.PIXELS);
		update();
		addComponent(new Label("Редактирование настроек телеграмм бота"));
		addComponents(keyField, saveBotKeyButton);
		saveBotKeyButton.addClickListener(e -> {
			telegramAggregator.reloadBot(keyField.getValue());
			update();
		});
	}
}
