package comp3350.srsys.tests.business;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.srsys.application.Main;
import comp3350.srsys.business.PingChat;
import comp3350.srsys.objects.User;

public class ProductLogicTest extends TestCase {

	@Before
	public void setUp() {
		Main.startUp();
	}

	@After
	public void tearDown() {
		Main.shutDown();
	}

	@Test
	public void testFilters(){
	}
}
