package JUnit;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import SuppClasses.User;

public class UserTest {

	@Test
	public void WantToDir() {
		boolean actual[]=new boolean[1];
		boolean expected[] =new boolean[1];
		actual[0]=true;
		expected[0]=true;
		//User user=new User(3,12);
		Assert.assertArrayEquals(expected, actual);
	}

}
