package view;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import components.Component;
import components.Components;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Controller {

	private ObservableList<Component> components = FXCollections.observableArrayList();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane allScene;

	@FXML
	private TableView<Component> table;

	@FXML
	private TableColumn<Component, String> categorie;

	@FXML
	private TableColumn<Component, String> c1;

	@FXML
	private TableColumn<Component, Integer> c2;

	@FXML
	private MenuButton menuControl;

	@FXML
	private MenuItem menuButton1;

	@FXML
	private MenuItem menuButton2;

	@FXML
	private MenuItem allComponentsButton;

	@FXML
	private TextField modelNewComponentField;

	@FXML
	private TextField costNewComponentField;

	@FXML
	private TextField categorieField;

	@FXML
	private Button addComponentButton;

	@FXML
	private Button deleteComponentButton;

	@FXML
	private MenuButton statisticButton;

	@FXML
	private MenuItem statForComponentTypeButton;

	@FXML
	private MenuItem statForCostComponentType;

	public ObservableList<Component> allComponents;

	@FXML
	void initialize() {

		allComponents = application.TableViewHelper.getComponentList();
		components.addAll(allComponents);
		table.setItems(components);
		ControllerPieChart.allComponents = allComponents;

		statForComponentTypeButton.setOnAction(event -> {
			ControllerPieChart.statisticsByCategorie();
		});

		statForCostComponentType.setOnAction(event -> {
			ControllerPieChart.statisticsByCost();
		});

		menuButton1.setOnAction(event -> {
			table.getItems().clear();
			initDataComponents(allComponents, Components.COMPUTER);
		});

		menuButton2.setOnAction(event -> {
			table.getItems().clear();
			initDataComponents(allComponents, Components.MONITOR);

		});

		allComponentsButton.setOnAction(event -> {
			table.getItems().clear();
			components.addAll(allComponents);
		});

		deleteComponentButton.setOnAction(event -> {
			deleteComponent(allComponents);
		});

		addComponentButton.setOnAction(event -> {
			addComponent(allComponents);
		});

		categorie.setCellValueFactory(new PropertyValueFactory<components.Component, String>("categorie"));
		c1.setCellValueFactory(new PropertyValueFactory<components.Component, String>("model"));
		c2.setCellValueFactory(new PropertyValueFactory<components.Component, Integer>("cost"));

		// Set the column resize policy to constrained resize policy
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setEditable(true);

	}

	public ObservableList<Component> deleteComponent(ObservableList<Component> allComponents) {
		TableViewSelectionModel<Component> tsm = table.getSelectionModel();

		// Check, if any rows are selected
		if (tsm.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ни один элемент не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		// Get all selected row indices in an array
		ObservableList<Integer> list = tsm.getSelectedIndices();

		Integer[] selectedIndices = new Integer[list.size()];
		selectedIndices = list.toArray(selectedIndices);

		// Sort the array
		Arrays.sort(selectedIndices);

		// Delete rows (last to first)
		for (int i = selectedIndices.length - 1; i >= 0; i--) {
			tsm.clearSelection(selectedIndices[i].intValue());
			table.getItems().remove(selectedIndices[i].intValue());
			allComponents.remove(selectedIndices[i].intValue());
		}
		ControllerPieChart.allComponents = allComponents;
		return allComponents;
	}

	public ObservableList<Component> addComponent(ObservableList<Component> allComponents) {
		if (categorieField.getText() != null && modelNewComponentField.getText() != null
				&& costNewComponentField.getText() != null) {
			if (!categorieField.getText().isEmpty() && !modelNewComponentField.getText().isEmpty()
					&& !costNewComponentField.getText().isEmpty()) {
				String regular = "[a-zA-Zа-яА-Я]+.*";
				if (categorieField.getText().matches(regular) && modelNewComponentField.getText().matches(regular)) {
					try {
						Component component = new Component(categorieField.getText(), modelNewComponentField.getText(),
								Integer.parseInt(costNewComponentField.getText()));

						// Add the new Component to the table
						table.getItems().add(component);
						allComponents.add(component);

						// Clear the Input Fields
						modelNewComponentField.setText(null);
						costNewComponentField.setText(null);
						categorieField.setText(null);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Некорректно введены данные", "Ошибка",
								JOptionPane.ERROR_MESSAGE);
						return null;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Некорректно введены данные", "Ошибка",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Необходимо заполнить все 3 поля (пустые поля)", "Ошибка",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Необходимо заполнить все 3 поля (null)", "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}
		return allComponents;
	}

	private void initDataComponents(ObservableList<Component> allComponents, Components component) {
		ObservableList<Component> oneComponent = application.TableViewHelper.getOneTypeComponent(allComponents,
				component);
		components.addAll(oneComponent);
	}
}