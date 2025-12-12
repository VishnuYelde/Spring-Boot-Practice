package boot.model;

public class ProductModel {
	private int pid;
	private String name;
	private double price;
	private String discription;
	private String color;

	@Override
	public String toString() {
		return "ProductModel [pid=" + pid + ", name=" + name + ", price=" + price + ", discription=" + discription
				+ ", color=" + color + "]";
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
