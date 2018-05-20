package ru.otus.skuznets.hw10;

import org.hibernate.Session;

public class UserDAO {

	private Session session;
	
	public UserDAO(Session session) {
		this.session = session;
	}
	
	public void save(UserDataSet user) {
		session.save(user);
	}
	
	public UserDataSet load(long id) {
		return (UserDataSet) session.load(UserDataSet.class, id);
	}
}
