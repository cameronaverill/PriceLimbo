
public class FreePeopleShoe implements Trackable{
	private String product_id;
	private String name;
	private double price;
	private String URL;
	private String img_URL;
	
	//constructor
	//eventually search db w/ hibernate to see if it exists
	public FreePeopleShoe(String product_id, String name, double price, String URL, String img_URL){
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.URL = URL;
		this.img_URL = img_URL;
	}
	
	public double getCurrPrice(){
		return price;
	}
	
	public String getName(){
		return name;
	}
	
	public String getProductID(){
		return product_id;
	}
	
	public String getUrl(){
		return URL;
	}
	
	public String getImgUrl(){
		return img_URL;
	}
}
