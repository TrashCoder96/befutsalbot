package ru.bifutsal.admin;

import ru.bifutsal.admin.vo.service.KeyVoService;
import ru.bifutsal.aggregator.TelegramAggregator;
import ru.bifutsal.config.KeyNames;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by itimofeev on 16.09.2017.
 */
@Theme("valo")
@SpringUI(path = "/admin")
@DesignRoot
public class NavigatorUI extends UI {

	@Autowired
	private TelegramAggregator telegramAggregator;

	@Autowired
	private KeyVoService keyVoService;

	private Button saveBotKeyButton = new Button("Принять значение");

	private TextField keyField = new TextField("Поле лучше не оставлять пустым =(");

	@Override
	protected void init(VaadinRequest request) {
		setSizeFull();
		VerticalLayout root = new VerticalLayout();
		root.addComponent(new Label("Редактирование настроек бота"));
		root.addComponents(keyField, saveBotKeyButton);
		saveBotKeyButton.addClickListener(e -> {
			keyVoService.saveOrUpdateKey(KeyNames.TELEGRAMBOTKEY, keyField.getValue());
			telegramAggregator.reloadBot();
			update();
		});
		keyField.setWidth(700, Unit.PIXELS);
		saveBotKeyButton.setWidth(200, Unit.PIXELS);
		setContent(root);
		update();
	}

	private void update() {
		keyField.setValue(telegramAggregator.getCurrentKeyValue());
	}


}