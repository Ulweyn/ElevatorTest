package JUnit;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import SuppClasses.Floor;

public class FloorTest {

	@Test
	public void WhereToGo(){
		boolean expected[]= new boolean[1];
		boolean actual[]=new boolean[1];
		Floor floor = new Floor(1,10);
		
		int count=0;
		for(int i=0;i<floor.awaiting.size();i++)
		{
			if(floor.awaiting.get(i).WantToDir()==true)
				count++;
			
			else
				count--;
		}
		if(count>0)
			actual[0]= true;
		else
			actual[0]= false;
		
		expected[0]=floor.WhereToGo();
		Assert.assertArrayEquals(expected, actual);
		
	}

}
