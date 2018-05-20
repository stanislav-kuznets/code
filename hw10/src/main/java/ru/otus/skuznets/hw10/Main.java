package ru.otus.skuznets.hw10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.otus.skuznets.hw10.DBServiceHibernateImpl;

public class Main {

	public static void main(String[] args) {
		
		DBServiceHibernateImpl service = new DBServiceHibernateImpl();
		service.save(new UserDataSet(1, "Алексей", 30));
		service.save(new UserDataSet(2, "Юрий", 35));
		
		List<PhoneDataSet> phones = new ArrayList();
		phones.add(new PhoneDataSet("123"));
		phones.add(new PhoneDataSet("231"));
		phones.add(new PhoneDataSet("432"));
		
		service.save(new UserDataSet("Ivan", 35, new AddressDataSet("Komsomolskaya"), phones));
		System.out.println(service.load(1));

		
	}

}
