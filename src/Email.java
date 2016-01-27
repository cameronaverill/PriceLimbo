
public class Email {
	private String recipient;
	private String body;
	private String title;
	
	public Email(String recipient, String body, String title){
		this.recipient = recipient;
		this.body = body;
		this.title = title;
	}
	
	public String getBody(){
		return body;
	}
	
	public void setBody(String b){
		body = b;
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
}
