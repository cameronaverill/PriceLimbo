import org.hibernate.Criteria;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import java.util.HashMap;

public class ManageFreePeopleShoe {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public static void main(String[] args){
		try{
	         sessionFactory = createSessionFactory();
	    }
		catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	    }
		FreePeopleShoeParser fp = new FreePeopleShoeParser();
		HashMap<String, FreePeopleShoe> allShoes = fp.getShoeMap();
		for(FreePeopleShoe shoe: allShoes.values()){
			
		}
		
	}
	
	public static SessionFactory createSessionFactory() {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    serviceRegistry = new ServiceRegistryBuilder().applySettings(
	            configuration.getProperties()). buildServiceRegistry();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}
	
	private Integer addFreePeopleShoe(FreePeopleShoe shoe){
		Session session = sessionFactory.openSession(new MyInterceptor());
		
	}
	
	//returns whether we should update the shoe entry in the database 
	private void updateFreePeopleShoe(String product_id, double price){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(FreePeopleShoe.class);
			//get the shoe based on the free people product id
			FreePeopleShoe shoe = (FreePeopleShoe) criteria.add(Restrictions.eq("product_id", product_id))
			                             .uniqueResult();
			//shouldn't update if it doesn't exist!
			if(shoe == null){
				throw new NoSuchFieldException("you cannot update an item that doesn't exist already");
			}
			
		}
	}
	
	
	
}
