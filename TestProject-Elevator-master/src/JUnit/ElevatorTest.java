package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import SuppClasses.Elevator;

class ElevatorTest {

	@Test
	public void ChangeDirection() 
	{
		boolean expected[]= new boolean[1];
		boolean actual[]=new boolean[1];
		Elevator elevator = new Elevator();				
		elevator.SetDirection(false);
		actual[0]=elevator.GetDirection();
		elevator.SetDirection(true);
		elevator.ChangeDirection();
		expected[0]=elevator.GetDirection();
		Assert.assertArrayEquals(expected, actual);
		
	}

}
