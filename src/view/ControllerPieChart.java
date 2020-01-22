package view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import components.Component;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControllerPieChart {
	static ObservableList<Component> allComponents;
	static double allCountComponents;
	static double allCost;

	public static void statisticsByCategorie() {
		allCountComponents = 0;
		try {
			HashMap<String, Double> statForCategory = new HashMap<>();
			for (Component component : allComponents) {
				if (statForCategory.containsKey(component.getCategorie())) {
					statForCategory.put(component.getCategorie(), statForCategory.get(component.getCategorie()) + 1.0);
				} else {
					statForCategory.put(component.getCategorie(), 1.0);
				}
				allCountComponents++;
			}
			ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList();
			Iterator<?> iterator = statForCategory.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry mentry = (Map.Entry) iterator.next();
				valueList.add(new PieChart.Data(mentry.getKey().toString(), (double) (mentry.getValue())));
			}
			PieChart statisticPieChart = new PieChart();
			statisticPieChart.setData(valueList);
			statisticPieChart.getData().forEach(data -> {
				String percentage = String.format("%.2f%%", ((data.getPieValue() / allCountComponents) * 100));
				Tooltip toolTip = new Tooltip(percentage);
				Tooltip.install(data.getNode(), toolTip);
			});
			VBox vbox = new VBox();

			vbox.getChildren().add(statisticPieChart);
			Scene scene = new Scene(vbox);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Диаграмма");
			stage.show();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "не найдено файла с данными для вывода", null,
					JOptionPane.ERROR_MESSAGE, null);
			e.printStackTrace();
		}
	}

	public static void statisticsByCost() {
		try {
			HashMap<String, Double> statForCost = new HashMap<>();
			for (Component component : allComponents) {
				if (statForCost.containsKey(component.getCategorie())) {
					statForCost.put(component.getCategorie(),
							statForCost.get(component.getCategorie()) + component.getCost());
				} else {
					statForCost.put(component.getCategorie(), (double) component.getCost());
				}
				allCost += component.getCost();
			}

			ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList();
			Iterator<?> iterator = statForCost.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry mentry = (Map.Entry) iterator.next();
				valueList.add(new PieChart.Data(mentry.getKey().toString(), (double) (mentry.getValue())));
			}
			PieChart statisticPieChart = new PieChart();
			statisticPieChart.setData(valueList);
			statisticPieChart.getData().forEach(data -> {
				String percentage = String.format("%.2f $", data.getPieValue());
				Tooltip toolTip = new Tooltip(percentage);
				Tooltip.install(data.getNode(), toolTip);
			});
			VBox vbox = new VBox();

			vbox.getChildren().add(statisticPieChart);
			Scene scene = new Scene(vbox);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Диаграмма");
			stage.show();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "не найдено файла с данными для вывода", null,
					JOptionPane.ERROR_MESSAGE, null);
			e.printStackTrace();
		}
	}
}