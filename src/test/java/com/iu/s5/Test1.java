package com.iu.s5;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



public class Test1 extends AbstractTestCase{

	@Autowired
	private DataSource dataSource;
	
	@Test
	public void test() throws Exception {
		// assert : 단정문
		assertNotNull(dataSource.getConnection());
		//assertEquals(1, );
		
	}
	
	public void go() {
		
	}

}
