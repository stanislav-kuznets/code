package ru.otus.skuznets.hw12;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;

import ru.otus.skuznets.hw12.Cache.CacheEngineImpl;
import ru.otus.skuznets.hw12.Cache.Element;


public class DBServiceImpl implements DBService, AutoCloseable {
	
	private SessionFactory sessionFactory;
	private CacheEngineImpl<Long, DataSet> cache;
	
	public DBServiceImpl(CacheEngineImpl<Long, DataSet> cache) {
		
		this.cache = cache;
		
		Configuration config = new Configuration();
		
		config.addAnnotatedClass(UserDataSet.class);
		config.addAnnotatedClass(AddressDataSet.class);
		config.addAnnotatedClass(PhoneDataSet.class);
		
		config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/web");
		config.setProperty("hibernate.connection.username", "root");
		config.setProperty("hibernate.connection.password", "demo");
		config.setProperty("hibernate.show_sql", "true");
		config.setProperty("hibernate.hbm2ddl.auto", "create");
		config.setProperty("hibernate.connection.useSSL", "false");
		config.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		
		sessionFactory = createSessionFactory(config);
	}
	
	 private static SessionFactory createSessionFactory(Configuration configuration) {
	    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
	    builder.applySettings(configuration.getProperties());
	    ServiceRegistry serviceRegistry = builder.build();
	    return configuration.buildSessionFactory(serviceRegistry);
	 }

	public void save(UserDataSet user) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			UserDAO dao = new UserDAO(session);
			dao.save(user);
			transaction.commit();
		}
		cache.put(new Element(user.getId(), user));
	}

	public UserDataSet load(long id) {
		if(cache.get(id) != null) {
			cache.setCacheHitCount(cache.getHitCount() + 1);
			return (UserDataSet) cache.get(id);
		}
		try (Session session = sessionFactory.openSession()) {
		UserDAO dao = new UserDAO(session);
		return dao.load(id);
		}
	}
	
	@Override
	public void close() {
		sessionFactory.close();
	}

	@Override
	public void getCacheData() {
		System.out.println("The missCount = " + cache.getMissCount());
		System.out.println("The hitCount = " + cache.getHitCount());
		
	}

	
}
