package aisino.reportform.db;
import org.hibernate.SessionFactory;

public interface DynamicSessionFactory extends SessionFactory{

	 public SessionFactory getHibernateSessionFactory();
}
