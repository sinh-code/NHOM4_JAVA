package Baimau4;

public class Product {
	private String product_id;
	private String product_name;
	private double product_price;
	private int product_total;
	public Product() {
		
	}
	
	public Product(String product_id, String product_name, double product_price, int product_total) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_total = product_total;
	}
	
	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	public int getProduct_total() {
		return product_total;
	}

	public void setProduct_total(int product_total) {
		this.product_total = product_total;
	}
	
	@Override
	public String toString() {
		return "ProductID: " + product_id + ", Product Name: " + product_name + ", Product Price :"
				+ product_price + ", Product Total: " + product_total ;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
