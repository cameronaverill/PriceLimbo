import java.util.Collection;
import java.util.List;
import java.util.LinkedList;

public class Email {
	private String recipient;
	private String body;
	private String title;
	private String sender;
	private List<FreePeopleShoe> items;
	
	public Email(String recipient, String title, String sender, Collection<FreePeopleShoe> collection){
		this.recipient = recipient;
		this.items = new LinkedList<FreePeopleShoe>(collection);
		this.body = generateEmailBody();
		this.title = title;
		this.sender = sender;
	}
	
	public String getBody(){
		return body;
	}
	
	public void setBody(String b){
		this.body = b;
	}
	

	//generate the HTML email out of the items given
	private String generateEmailBody(){
		StringBuffer sb = new StringBuffer("Items that are cheap: /n");
		for(Approves item : items){
			if(item.approve()){
				sb.append("<a href=" + ((Trackable) item).getURL() + ">");
				sb.append(((Trackable) item).getName() + ": </a> ");
				sb.append(((Trackable) item).getPrice());
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	public String getRecipient(){
		return recipient;
	}
	
	public void setRecipient(String rec) throws IllegalArgumentException{
		//check that it's valid email
		if(!rec.contains("@") || !rec.contains(".")){
			throw new IllegalArgumentException("Invalid Email format");
		}
		recipient = rec;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String t){
		title = t;
	}
	
	public String getSender(){
		return sender;
	}
	
	public void setSender(String s){
		sender = s;
	}
}
