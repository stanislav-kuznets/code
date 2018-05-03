package ru.otus.skuznets.hw8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestClass {
	
	private String name = "Test";
	private int number = 10;
	private boolean isTrue = true;
	private double length = 200.00;
	private int[] digits = {1, 2, 3};
	private short sh = 2;
	List<Object> list = new ArrayList<Object>();
	private Object[] array;
	{
		list.add("ABC");
		list.add("CDE");
		array = list.toArray();
	}

	@Override
	public boolean equals(Object obj) {
		TestClass test = (TestClass) obj;
		if(this.name.equals(test.name) && this.number == test.number && this.isTrue == test.isTrue
				&& this.length == test.length && Arrays.equals(this.digits, test.digits) && this.sh == test.sh
				&& Arrays.equals(this.array, test.array)) {
			return true;
		}
		return false;
	}

}
