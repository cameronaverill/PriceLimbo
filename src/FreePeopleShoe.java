import java.sql.Timestamp; 

public class FreePeopleShoe implements Trackable, Approves{
	private int id;
	private String product_id;
	private String name;
	private double price;
	private String URL;
	private String img_URL;
	private Timestamp time;
	//the max price at which we send it in the email
	private static double TRIGGER_PRICE = 70;
	private static double TRIGGER_DIFFERENCE = 0.2;
	
	//default constructor needed for Hibernate
	public FreePeopleShoe(){};
	
	//eventually search db w/ hibernate to see if it exists
	public FreePeopleShoe(String product_id, String name, double price, String URL, String img_URL){
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.URL = URL;
		this.img_URL = img_URL;
	}
	
	public int getId() {
	    return id;
	}
	
	public void setId(int id) {
	    this.id = id;
	}
	   
	public double getPrice(){
		return price;
	}
	
	public void setPrice(double newPrice) {
		price = newPrice;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getProduct_id(){
		return product_id;
	}
	
	public void setProduct_id(String product_id){
		this.product_id = product_id;
	}
	
	public String getURL(){
		return URL;
	}
	
	public void setURL(String URL){
		this.URL = URL;
	}
	
	public String getImg_URL(){
		return img_URL;
	}
	
	public void setImg_URL(String img_URL){
		this.img_URL = img_URL;
	}
	
	//approve if less than TRIGGER_PRICE or reduced by TRIGGER_DIFFERENCE
	public boolean approve(){
		return (this.price - FreePeopleShoe.TRIGGER_PRICE <= Math.pow(10, -5));
	}

}
