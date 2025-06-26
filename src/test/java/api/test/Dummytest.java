package api.test;

import org.testng.annotations.Test;

public class Dummytest {
	@Test(priority=1)
	void test1()
	{
		System.out.println("test caes1");
	}
	
	@Test(priority=2)
	void test2()
	{
		System.out.println("test caes2");
	}

}
