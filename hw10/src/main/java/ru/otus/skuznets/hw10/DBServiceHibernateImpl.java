package ru.otus.skuznets.hw10;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;


public class DBServiceHibernateImpl implements DBService, AutoCloseable {
	
	private SessionFactory sessionFactory;
	
	public DBServiceHibernateImpl() {
		
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
	}

	public UserDataSet load(long id) {
		try (Session session = sessionFactory.openSession()) {
		UserDAO dao = new UserDAO(session);
		return dao.load(id);
		}
	}
	
	@Override
	public void close() throws Exception {
		sessionFactory.close();
	}

	
}
