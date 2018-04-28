package aisino.reportform.db.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;
import javax.naming.NamingException;
import javax.naming.Reference;
import org.hibernate.Cache;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.StatelessSessionBuilder;
import org.hibernate.TypeHelper;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;

import aisino.reportform.db.CustomerContextHolder;
import aisino.reportform.db.DynamicSessionFactory;

public class DynamicSessionFactoryImpl implements DynamicSessionFactory {
	 private static final long serialVersionUID = 5384069312247414885L;
	    private Map<Object, SessionFactory> targetSessionFactorys;  
	    private SessionFactory defaultTargetSessionFactory; 
	    
	public void setTargetSessionFactorys(
				Map<Object, SessionFactory> targetSessionFactorys) {
			this.targetSessionFactorys = targetSessionFactorys;
		}

		public void setDefaultTargetSessionFactory(
				SessionFactory defaultTargetSessionFactory) {
			this.defaultTargetSessionFactory = defaultTargetSessionFactory;
		}

	@Override
	public SessionFactoryOptions getSessionFactoryOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionBuilder withOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session openSession() throws HibernateException {
		// TODO Auto-generated method stub
		  return this.getHibernateSessionFactory().openSession();
	}

	@Override
	public Session getCurrentSession() throws HibernateException {
		// TODO Auto-generated method stub
		return this.getHibernateSessionFactory().getCurrentSession();
	}

	@Override
	public StatelessSessionBuilder withStatelessOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatelessSession openStatelessSession() {
		// TODO Auto-generated method stub
		  return this.getHibernateSessionFactory().openStatelessSession();
	}

	@Override
	public StatelessSession openStatelessSession(Connection connection) {
		// TODO Auto-generated method stub
		 return this.getHibernateSessionFactory().openStatelessSession(connection);
	}

	@Override
	public ClassMetadata getClassMetadata(Class entityClass) {
		// TODO Auto-generated method stub
		 return this.getHibernateSessionFactory().getClassMetadata(entityClass);
	}

	@Override
	public ClassMetadata getClassMetadata(String entityName) {
		// TODO Auto-generated method stub
		  return this.getHibernateSessionFactory().getClassMetadata(entityName);
	}

	@Override
	public CollectionMetadata getCollectionMetadata(String roleName) {
		// TODO Auto-generated method stub
		 return this.getHibernateSessionFactory().getCollectionMetadata(roleName);
	}

	@Override
	public Map<String, ClassMetadata> getAllClassMetadata() {
		// TODO Auto-generated method stub
		 return this.getHibernateSessionFactory().getAllClassMetadata();
	}

	@Override
	public Map getAllCollectionMetadata() {
		// TODO Auto-generated method stub
		 return this.getHibernateSessionFactory().getAllClassMetadata();
	}

	@Override
	public Statistics getStatistics() {
		// TODO Auto-generated method stub
		   return this.getHibernateSessionFactory().getStatistics();
	}

	@Override
	public void close() throws HibernateException {
		// TODO Auto-generated method stub
		  this.getHibernateSessionFactory().close();	
	}

	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		  return this.getHibernateSessionFactory().isClosed();
	}

	@Override
	public Cache getCache() {
		// TODO Auto-generated method stub
		 return this.getHibernateSessionFactory().getCache();
	}

	@Override
	public void evict(Class persistentClass) throws HibernateException {
		// TODO Auto-generated method stub
		  this.getHibernateSessionFactory().evict(persistentClass);
	}

	@Override
	public void evict(Class persistentClass, Serializable id)
			throws HibernateException {
		 this.getHibernateSessionFactory().evict(persistentClass, id);
	}

	@Override
	public void evictEntity(String entityName) throws HibernateException {
		// TODO Auto-generated method stub
		 this.getHibernateSessionFactory().evictEntity(entityName);
		
	}

	@Override
	public void evictEntity(String entityName, Serializable id)
			throws HibernateException {
		// TODO Auto-generated method stub
		  this.getHibernateSessionFactory().evictEntity(entityName, id);
		
	}

	@Override
	public void evictCollection(String roleName) throws HibernateException {
		// TODO Auto-generated method stub
		  this.getHibernateSessionFactory().evictCollection(roleName);
	}

	@Override
	public void evictCollection(String roleName, Serializable id)
			throws HibernateException {
		// TODO Auto-generated method stub
		this.getHibernateSessionFactory().evictCollection(roleName, id);
		
	}

	@Override
	public void evictQueries(String cacheRegion) throws HibernateException {
		// TODO Auto-generated method stub
		 this.getHibernateSessionFactory().evictQueries(cacheRegion);   
	}

	@Override
	public void evictQueries() throws HibernateException {
		// TODO Auto-generated method stub
	    this.getHibernateSessionFactory().evictQueries();      
	}

	@Override
	public Set getDefinedFilterNames() {
		// TODO Auto-generated method stub
		 return this.getHibernateSessionFactory().getDefinedFilterNames();
	}

	@Override
	public FilterDefinition getFilterDefinition(String filterName)
			throws HibernateException {
		// TODO Auto-generated method stub
		 return this.getHibernateSessionFactory().getFilterDefinition(filterName);
	}

	@Override
	public boolean containsFetchProfileDefinition(String name) {
		// TODO Auto-generated method stub
		  return this.getHibernateSessionFactory().containsFetchProfileDefinition(name);
	}

	@Override
	public TypeHelper getTypeHelper() {
		// TODO Auto-generated method stub
		   return this.getHibernateSessionFactory().getTypeHelper();
	}

	@Override
	public Reference getReference() throws NamingException {
		// TODO Auto-generated method stub
		  return this.getHibernateSessionFactory().getReference();
	}

	@Override
	public SessionFactory getHibernateSessionFactory() {
		// TODO Auto-generated method stub
		 SessionFactory targetSessionFactory = targetSessionFactorys.get(CustomerContextHolder.getCustomerType());  
	        if (targetSessionFactory != null) {  
	            return targetSessionFactory;  
	        } else if (defaultTargetSessionFactory != null) {  
	            return defaultTargetSessionFactory;  
	        }  
	        return null;
	}

}
