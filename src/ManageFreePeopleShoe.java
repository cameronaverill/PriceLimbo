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
import java.util.List;
import java.util.LinkedList;

public class ManageFreePeopleShoe {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	
	public ManageFreePeopleShoe(){
		try{
	         sessionFactory = createSessionFactory();
	    }
		catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
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
	
	public Integer addFreePeopleShoe(FreePeopleShoe shoe){
		Session session = sessionFactory.withOptions().interceptor(new MyInterceptor()).openSession();
		Transaction tx = null;
		Integer shoeId = null;
		try{
			tx = session.beginTransaction();
			shoeId = (Integer) session.save(shoe);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return shoeId;
	}
	
	//returns a linkedList of all the FreePeopleShoes in the database
	@SuppressWarnings("unchecked")
	public List<FreePeopleShoe> listShoes(){
		Session session = sessionFactory.withOptions().interceptor(new MyInterceptor()).openSession();
		Transaction tx = null;
		LinkedList<FreePeopleShoe> shoes = new LinkedList<FreePeopleShoe>();
		try{
			tx = session.beginTransaction();
			shoes.addAll(session.createQuery("FROM FreePeopleShoe").list());
			tx.commit();
		}
		catch(HibernateException e){
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return shoes;
	}
	
	//returns a shoe if it exists 
	public FreePeopleShoe getFreePeopleShoeByProductID(String product_id) throws NoSuchFieldException{
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		FreePeopleShoe shoe = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(FreePeopleShoe.class);
			//get the shoe based on the free people product id
			shoe = (FreePeopleShoe) criteria.add(Restrictions.eq("product_id", product_id))
			                             .uniqueResult();
			tx.commit();
		}
		catch(HibernateException e){
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return shoe;
	}
	
	public FreePeopleShoe getFreePeopleShoe(Integer shoeId) throws NoSuchFieldException{
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		FreePeopleShoe shoe = null;
		try{
			tx = session.beginTransaction();
			shoe = (FreePeopleShoe) session.get(FreePeopleShoe.class, shoeId);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return shoe;
	}
	
	//returns whether we should update the shoe entry in the database 
	public void updateFreePeopleShoe(String product_id, double price) throws NoSuchFieldException{
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
			shoe.setPrice(price);
			session.update(shoe);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally{
			session.close();
		}
	}
	
    public void deleteFreePeopleShoe(Integer shoeId){
       Session session = sessionFactory.withOptions().interceptor(new MyInterceptor()).openSession();
	   Transaction tx = null;
       try{
          tx = session.beginTransaction();
          FreePeopleShoe shoe = 
                   (FreePeopleShoe)session.get(FreePeopleShoe.class, shoeId); 
          session.delete(shoe); 
          tx.commit();
       }catch (HibernateException e) {
          if (tx!=null) tx.rollback();
          e.printStackTrace(); 
       }finally {
          session.close(); 
       }
    } 
	
	
	
}
