import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

public class MyInterceptor extends EmptyInterceptor{
	private int updates;
	private int creates;
	private int loads;
	
	//called when you delete 
	public void onDelete(Object entity, 
						Serializable id,
						Object[] state,
						String[] propertyNames,
						Type[] types) {
		System.out.println("Record deleted");
	}
	
	//called when you update 
	public boolean onFlushDirty(Object entity,
						Serializable id,
						Object[] currentState,
						Object[] previousState,
						String[] propertyNames,
						Type[] types) {
		if(entity instanceof FreePeopleShoe){
			System.out.println("Record updated");
			return true;
		}
		return false;
	}
	
	public boolean onLoad(Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
		System.out.println("Loading");
		return true;
	}
	
	public boolean onSave(Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
		if ( entity instanceof FreePeopleShoe ) {
			System.out.println("Record created");
			return true; 
		}
		return false;
	}
	
	//called before you commit a record
	public void preFlush(@SuppressWarnings("rawtypes") Iterator iterator) {
	    System.out.println("Record preflushed");
	}
	
	//called after committed into database
    public void postFlush(@SuppressWarnings("rawtypes") Iterator iterator) {
        System.out.println("postFlush");
    }
    
}
	
