package ru.otus.skuznets.hw11;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		CacheEngineImpl<Long, DataSet> cache = new CacheEngineImpl<>(10);
		try(DBServiceImpl service = new DBServiceImpl(cache)) {
			List<PhoneDataSet> phones = new ArrayList<PhoneDataSet>();
			phones.add(new PhoneDataSet("654"));
			phones.add(new PhoneDataSet("432"));
			phones.add(new PhoneDataSet("413"));
			service.save(new UserDataSet("Ivan", 22, new AddressDataSet("Komsomolskaya"), phones));
			System.out.println(service.load(1));
			service.getCacheData();
		}

	}

}
