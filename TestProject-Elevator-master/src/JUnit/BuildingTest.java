package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import SuppClasses.Building;
import SuppClasses.Elevator;
import SuppClasses.User;

class BuildingTest {

	@Test
	public void PassengeresTransaction() {
		
		Building building = new Building();
		building.elevator.currentFloor=2;
		building.elevator.passengers.add(new User(2,building.GetStoreys()));//добавим в лифт пару пассажиров для чистоты эксперимента
		building.elevator.passengers.add(new User(2,building.GetStoreys()));
		building.elevator.currentCapacity-=2;
		ArrayList<User> unitTPassengers= new ArrayList<User>();
		unitTPassengers.addAll(0,building.elevator.passengers);
		ArrayList<User> tPassengers= new ArrayList<User>();
		ArrayList<User> unitTAwaiting= new ArrayList<User>();
		unitTAwaiting.addAll(0,building.floors.get(building.elevator.currentFloor).awaiting);
		ArrayList<User> tAwaiting= new ArrayList<User>();
		int tempCapacity=building.elevator.currentCapacity;;
		if(unitTPassengers.size()!=0)
		{
			for(int j=0;j<unitTPassengers.size();j++)//выходят из лифта
			{
			
				if(unitTPassengers.get(j).GetWantTo()==building.elevator.currentFloor)
				{
					unitTPassengers.get(j).SetWantToRand(building.elevator.currentFloor, building.GetStoreys());//рандомим выходящему пассажиру новый требуемый этаж
					unitTPassengers.get(j).SetPlacedAt(building.elevator.currentFloor);
					unitTAwaiting.add(unitTPassengers.get(j));
					tempCapacity++;
					System.out.println("Человек вышел из лифта. В лифте " + tempCapacity+" места");				
				}
				else
					tPassengers.add(unitTPassengers.get(j));
			}
			unitTPassengers=new ArrayList<User>();
			unitTPassengers.addAll(0,tPassengers);
			
		}
		//
		
		if(unitTAwaiting!=null)
		{
		for(int j=0;j<unitTAwaiting.size() ;j++)//заходят в лифт
			{	
			
			if(unitTAwaiting.get(j).WantToDir()==building.elevator.GetDirection() && tempCapacity>0) 
				{
					if(building.elevator.GetDirection()==true && unitTAwaiting.get(j).GetWantTo()>building.elevator.maxFloor)//меняем максимальный этаж в соответствии с новыми пасажирами
					{
						building.elevator.maxFloor=unitTAwaiting.get(j).GetWantTo();
					}
					
					if(building.elevator.GetDirection()==false && unitTAwaiting.get(j).GetWantTo()<building.elevator.maxFloor)//ситуация та же, но едем вниз.
					{
						building.elevator.maxFloor=unitTAwaiting.get(j).GetWantTo();
					}
					
					unitTPassengers.add(unitTAwaiting.get(j));
					
					tempCapacity--;
					System.out.println("Человек зашел в лифт. Ему нужно на "+(unitTPassengers.get(unitTPassengers.size()-1).GetWantTo()+1)+" этаж. Осталось " + tempCapacity+" места");							
				}		
			else
				tAwaiting.add(unitTAwaiting.get(j));
			}	
		unitTAwaiting= new ArrayList<User>();
		unitTAwaiting.addAll(0, tAwaiting);	
		}
		System.out.println("------------");
		building.PassengeresTransaction();
		
		Assert.assertEquals(unitTPassengers,building.elevator.passengers);//равниваем только пассажиров, поскольку изза рандомного переписывания человека при выходе из лифта списки будет трудно сравнивать
		
		
	}

}
