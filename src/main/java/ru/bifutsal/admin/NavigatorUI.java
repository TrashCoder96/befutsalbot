package ru.bifutsal.admin;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by itimofeev on 16.09.2017.
 */
@Theme("valo")
@SpringUI(path = "/admin")
@SpringViewDisplay
public class NavigatorUI extends UI implements ViewDisplay {

	private Panel display;

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout root = new VerticalLayout();
		root.setSizeFull();
		setContent(root);

		final CssLayout navigationBar = new CssLayout();
		navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		navigationBar.addComponent(createNavigationButton("Ключи локализации", LocalizationKeyView.LOCALIZATIONKEYVIEW));
		root.addComponent(navigationBar);

		display = new Panel();
		display.setSizeFull();
		root.addComponent(display);
		root.setExpandRatio(display, 1.0f);
	}

	private Button createNavigationButton(String caption, final String viewName) {
		Button button = new Button(caption);
		button.addStyleName(ValoTheme.BUTTON_SMALL);
		button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
		return button;
	}


	@Override
	public void showView(View view) {
		display.setContent((Component) view);
	}
}
