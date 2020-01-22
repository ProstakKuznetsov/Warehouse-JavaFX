package components;

public class Component {
	private String categorie;
	private String model;
	private Integer cost;

	public Component(String categorie, String model, Integer cost) {
		this.categorie = categorie;
		this.model = model;
		this.cost = cost;
	}

	public Component(String model, Integer cost) {
		this.model = model;
		this.cost = cost;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "Component: model =" + model + ", cost =" + cost + ".";
	}
}
