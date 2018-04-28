package aisino.reportform.db;

import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.SessionFactoryUtils;


public class DynamicTransactionManager extends HibernateTransactionManager{
	 private static final long serialVersionUID = -4655721479296819154L;
	 @Override
	    public DataSource getDataSource() {
	        return SessionFactoryUtils.getDataSource(getSessionFactory());
	    }
	  @Override
	    public SessionFactory getSessionFactory() {
	        DynamicSessionFactory dynamicSessionFactory = (DynamicSessionFactory) super.getSessionFactory();  
	        SessionFactory hibernateSessionFactory = dynamicSessionFactory.getHibernateSessionFactory();  
	        return hibernateSessionFactory;  
	    }
}
