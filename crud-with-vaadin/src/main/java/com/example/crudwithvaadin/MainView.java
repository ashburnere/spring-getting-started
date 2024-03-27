package com.example.crudwithvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.springframework.util.StringUtils;

import java.time.DateTimeException;
import java.time.LocalDateTime;


/**
 * The main view class (called MainView in this guide) is the entry point for Vaadin’s UI logic.
 * In Spring Boot applications, if you annotate it with @Route, it is automatically picked up and shown at the root of
 * your web application. You can customize the URL where the view is shown by giving a parameter to the @Route annotation.
 * The following listing (from the initial project at src/main/java/com/example/crudwithvaadin/MainView.java) shows a
 * simple “Hello, World” view:
 */
@Route
public class MainView extends VerticalLayout {

	private final CustomerRepository repo;
	private final CustomerEditor editor;

	private final Grid<Customer> grid;
	private final TextField filter;
	private final Button addNewButton;

	public MainView(CustomerRepository repo, CustomerEditor editor) {
		add(new Button("Click me" , e -> Notification.show("Hello, Spring+Vaadin user! " + LocalDateTime.now())));

		this.repo = repo;
		this.editor = editor;
		this.grid = new Grid<>(Customer.class);

		this.filter = new TextField();
		this.filter.setPlaceholder("Filter by last name");
		this.filter.setValueChangeMode(ValueChangeMode.LAZY);
		this.filter.addValueChangeListener(e -> listCustomers(e.getValue()));
		add(this.filter, this.grid);

		this.addNewButton = new Button("New customer", VaadinIcon.PLUS.create());

		// build layout
		final HorizontalLayout actions = new HorizontalLayout(filter, addNewButton);
		add(actions, this.grid, editor);

		this.grid.setHeight("300px");
		this.grid.setColumns("id", "firstName", "lastName");
		this.grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		this.filter.setValueChangeMode(ValueChangeMode.LAZY);
		this.filter.addValueChangeListener(e -> listCustomers(e.getValue()));

		// Connect selected Customer to editor or hide if none is selected
		this.grid.asSingleSelect().addValueChangeListener(e -> {
			editor.editCustomer(e.getValue());
		});


		// Instantiate and edit new Customer the new button is clicked
		this.addNewButton.addClickListener(e -> editor.editCustomer(new Customer("", "")));

		// Listen changes made by the editor, refresh data from backend
		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listCustomers(this.filter.getValue());
		});

		this.listCustomers(null);
	}

	void listCustomers(String filterText) {
		if (StringUtils.hasText(filterText)) {
			grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
		} else {
			grid.setItems(repo.findAll());
			//grid.setItems(repo.findAll());
			grid.setItems(VaadinSpringDataHelpers.fromPagingRepository(repo));
		}
	}

}
