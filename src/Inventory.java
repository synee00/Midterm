
public class Inventory {

	private String name;
	private String category;
	private String description;
	private double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Inventory(String name, String category, String description, double price) {
		super();
		this.name = name;
		this.category = category;
		this.description = description;
		this.price = price;
	}
	

	public Inventory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		
		String firstLine = String.format("%-20s %s ", name, price);
		String secondLine = String.format("%-5s %s", "\n ", description);
		
		return firstLine + secondLine;
	}
}
