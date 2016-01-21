import java.io.IOException;  
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class FreePeopleShoeParser {
	//link to access FreePeople data
	 private final String LINK = "http://www.freepeople.com/shoes/"
			 + "?browseState=updated&hasLeftNav=YES&startResult=1&showAll=1&sizes=lt";
	 //map the shoe id to the FreePeopleShoe it represents
	 private static HashMap<String, FreePeopleShoe> shoeMap = new HashMap<String, FreePeopleShoe>();
	 
	 public FreePeopleShoeParser(){
		 try{
			 Elements allShoes = getAllShoes();
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
			 return;
		 } 
		 Attributes atts = shoe.attributes();
		 //iterate through all the attributes
		 for(Attribute a : atts){
			 
		 }
		 
	 }
	 
}
