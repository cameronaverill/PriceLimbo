import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Example1{  
    public static void main( String[] args ) throws IOException{  
    	try{
    			//maxBodySize prevents timeout
                Document doc = Jsoup.connect("http://www.freepeople.com/shoes/"
                		+ "?browseState=updated&hasLeftNav=YES&startResult=1&showAll=1&sizes=lt").maxBodySize(0).get(); 
                //get all elements with a div attribute data-stylenumber
                Elements items = doc.select("div[data-stylenumber]");
                for (Element el : items){
                	System.out.println("li: " + el.text());
                }
                System.out.println(items.size());
    		}
    	catch (IOException e)
    	{
    	    e.printStackTrace();
    	}
}
}
