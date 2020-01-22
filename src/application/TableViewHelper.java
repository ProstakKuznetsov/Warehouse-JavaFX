package application;

import components.Component;
import components.Components;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewHelper {
	// Returns an observable list of persons
	public static ObservableList<Component> getComponentList() {
		Component component1 = new Component("computer", "Lenovo", 300);
		Component component2 = new Component("computer", "HP", 400);
		Component component3 = new Component("monitor", "Dell", 250);
		Component component4 = new Component("monitor", "Samsung", 200);

		return FXCollections.<Component>observableArrayList(component1, component2, component3, component4);
	}

	// Returns Component Categorie TableColumn
	public static TableColumn<Component, String> getCategorie() {
		TableColumn<Component, String> categorieCol = new TableColumn<>("categorie");
		PropertyValueFactory<Component, String> categorieCellValueFactory = new PropertyValueFactory<>("categorie");
		categorieCol.setCellValueFactory(categorieCellValueFactory);
		return categorieCol;
	}

	// Returns Component model TableColumn
	public static TableColumn<Component, String> getModel() {
		TableColumn<Component, String> modelCol = new TableColumn<>("model");
		PropertyValueFactory<Component, String> modelCellValueFactory = new PropertyValueFactory<>("model");
		modelCol.setCellValueFactory(modelCellValueFactory);
		return modelCol;
	}

	// Returns Component cost TableColumn
	public static TableColumn<Component, Integer> getCostColumn() {
		TableColumn<Component, Integer> costCol = new TableColumn<>("cost");
		PropertyValueFactory<Component, Integer> costCellValueFactory = new PropertyValueFactory<>("cost");
		costCol.setCellValueFactory(costCellValueFactory);
		return costCol;
	}

	// Return components of a specific type
	public static ObservableList<Component> getOneTypeComponent(ObservableList<Component> allComponents,
			Components componentType) {
		ObservableList<Component> onlyComponents = FXCollections.observableArrayList();
		for (Component component : allComponents) {
			if (componentType.toString().toLowerCase().equals(component.getCategorie().toLowerCase())) {
				onlyComponents.add(component);
			}
		}
		return onlyComponents;
	}
}
