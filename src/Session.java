import javax.mail.MessagingException;

public class Session {
	private static String recip = "cameronaverill@gmail.com";
	private static String from = "cra2126@columbia.edu";
	private static String title = "TEST";
	public static void main(String[] args){
		FreePeopleShoeParser shoeParser = new FreePeopleShoeParser();
		ManageFreePeopleShoe manager = new ManageFreePeopleShoe();
		Email em = new Email(recip, title, from, shoeParser.getShoeMap().values());
		EmailSender es = new EmailSender(em);
		try {
			es.sendEmail();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//add them to the database
		for(FreePeopleShoe shoe : shoeParser.getShoeMap().values()){
			try {
				//if it already exists
				if(manager.getFreePeopleShoeByProductID(shoe.getProduct_id()) != null){
					manager.updateFreePeopleShoe(shoe.getProduct_id(), shoe.getPrice());
				}
				//make a new one
				else{
					manager.addFreePeopleShoe(shoe);
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
	}
}
