import java.io.IOException;  
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import java.util.HashMap;

public class FreePeopleShoeParser {
	//link to access FreePeople data
	 private final String LINK = "http://www.freepeople.com/shoes/"
			 + "?browseState=updated&hasLeftNav=YES&startResult=1&showAll=1&sizes=lt";
	 //map the shoe id to the FreePeopleShoe it represents
	 private static HashMap<String, FreePeopleShoe> shoeMap = new HashMap<String, FreePeopleShoe>();
	 private static final String SELECTOR_QUERY = "[data-stylenumber], [data-integration], [src], [itemprop=url], .price";
	 
	 public static void main(String[] args){
		 FreePeopleShoeParser fp = new FreePeopleShoeParser();
	 }
	 
	 public FreePeopleShoeParser(){
		 try{
			 Elements allShoes = getAllShoes();
			 System.out.println("allShoes size: " + allShoes.size());
			 for(Element shoe : allShoes){
				 createShoe(shoe);
			 }
		 }
		 catch(IOException e){
			 e.printStackTrace();
			 //send me an email to say it stopped working eventually
			 
			 return;
		 }
	 }
	 
	 //gets all the items on the page
	 private Elements getAllShoes() throws IOException{
		try{
 			//maxBodySize prevents timeout
             Document doc = Jsoup.connect(LINK).maxBodySize(0).get(); 
             //get all elements with a div attribute data-stylenumber
             Elements items = doc.getElementsByAttribute("data-stylenumber");
             return items;
 			}
		 catch (IOException e)
		 {
			 throw new IOException("The link could not be processed"); 
		 }
	}
	 
	 private void createShoe(Element shoe){
		 //.attr returns an empty string if there isn't one
		 String product_id = shoe.attr("data-stylenumber");
		 //don't want to process again if we already have it in the map
		 if(shoeMap.containsKey(product_id)){
			 System.out.println("duplicate id: " + product_id);
			 return;
		 } 
		 
		 //get all elements matching one of the selector queries
		 Elements shoeFeatures = shoe.select(FreePeopleShoeParser.SELECTOR_QUERY);
		 String URL = "";
		 String img_URL = "";
		 String name = "";
		 double price = -1;
		 for(Element e : shoeFeatures){
			 
			 //if this is the link 
			 if(e.hasAttr("data-integration") && e.attr("data-integration").contains("browse-productThumbLink")){
				 URL = e.attr("href");
			 }
			 //if this is the image
		     if(e.hasAttr("src")){
				 img_URL = e.attr("src");
			 }
			 //if this is the name
			 if(e.hasAttr("itemprop") && e.attr("itemprop").equals("url")){
				 name = e.text();
			 }
			 //if this is the price
			 if(e.hasClass("price")){
				 price = Double.valueOf(e.ownText());
			 }
			 
		 }
		 shoeMap.put(product_id, new FreePeopleShoe(product_id, name, price, URL, img_URL));
		 
	 }
	 
	 public HashMap<String, FreePeopleShoe> getShoeMap(){
		 return shoeMap;
	 }
	 
}
