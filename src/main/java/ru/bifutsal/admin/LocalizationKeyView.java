package ru.bifutsal.admin;

import ru.bifutsal.dao.KeyDto;
import ru.bifutsal.dao.repository.KeyRepository;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Created by itimofeev on 16.09.2017.
 */
@UIScope
@SpringView(name = LocalizationKeyView.LOCALIZATIONKEYVIEW, ui = NavigatorUI.class)
@Transactional
public class LocalizationKeyView extends VerticalLayout implements View {

	public static final String LOCALIZATIONKEYVIEW = "LocalizationKeyView";

	@Autowired
	private KeyRepository keyRepository;

	protected Grid<KeyDto> KeyDtoGrid = new Grid<>(KeyDto.class);

	protected TextField editedValueField = new TextField("Значение");

	protected TextField newKeyField = new TextField("Ключ");

	protected TextField newValueField = new TextField("Значение");

	protected Button deleteSelectedItemsButton = new Button("Удалить выбранные ключи");

	protected Button createNewLocalizationKeyButton = new Button("Создать ключ локализации");

	public void update() {
		KeyDtoGrid.setItems(keyRepository.findAll());
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
	}

	@PostConstruct
	private void init() {
		addComponent(new Label("Редактирование ключей локализации"));
		addComponents(KeyDtoGrid, deleteSelectedItemsButton, newKeyField, newValueField, createNewLocalizationKeyButton);
		KeyDtoGrid.setItems(keyRepository.findAll());
		KeyDtoGrid.setSizeFull();
		Binder<KeyDto> localizationKeyVoBinder = KeyDtoGrid.getEditor().getBinder();
		Binder.Binding<KeyDto, String> changeValueAccountVoBooleanBinding =
				localizationKeyVoBinder.bind(editedValueField, KeyDto::getValue, (localizationKeyVo, value) -> {
					localizationKeyVo.setValue(value);
					keyRepository.save(localizationKeyVo);
					update();
				});
		createNewLocalizationKeyButton.addClickListener(e -> {
			KeyDto localizationKeyVo = new KeyDto();
			localizationKeyVo.setName(newKeyField.getValue());
			localizationKeyVo.setValue(newValueField.getValue());
			keyRepository.save(localizationKeyVo);
			update();
		});
		deleteSelectedItemsButton.addClickListener(e -> {
			KeyDtoGrid.getSelectedItems().forEach(si -> keyRepository.delete(si.getName()));
			update();
		});
		KeyDtoGrid.getColumn("value").setEditorBinding(changeValueAccountVoBooleanBinding);
		KeyDtoGrid.setSelectionMode(Grid.SelectionMode.MULTI);
		KeyDtoGrid.setColumnOrder("name", "value");
		KeyDtoGrid.getEditor().setEnabled(true);
	}
}
