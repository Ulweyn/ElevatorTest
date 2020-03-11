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
		building.elevator.passengers.add(new User(2,building.GetStoreys()));//������� � ���� ���� ���������� ��� ������� ������������
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
			for(int j=0;j<unitTPassengers.size();j++)//������� �� �����
			{
			
				if(unitTPassengers.get(j).GetWantTo()==building.elevator.currentFloor)
				{
					unitTPassengers.get(j).SetWantToRand(building.elevator.currentFloor, building.GetStoreys());//�������� ���������� ��������� ����� ��������� ����
					unitTPassengers.get(j).SetPlacedAt(building.elevator.currentFloor);
					unitTAwaiting.add(unitTPassengers.get(j));
					tempCapacity++;
					System.out.println("������� ����� �� �����. � ����� " + tempCapacity+" �����");				
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
		for(int j=0;j<unitTAwaiting.size() ;j++)//������� � ����
			{	
			
			if(unitTAwaiting.get(j).WantToDir()==building.elevator.GetDirection() && tempCapacity>0) 
				{
					if(building.elevator.GetDirection()==true && unitTAwaiting.get(j).GetWantTo()>building.elevator.maxFloor)//������ ������������ ���� � ������������ � ������ ����������
					{
						building.elevator.maxFloor=unitTAwaiting.get(j).GetWantTo();
					}
					
					if(building.elevator.GetDirection()==false && unitTAwaiting.get(j).GetWantTo()<building.elevator.maxFloor)//�������� �� ��, �� ���� ����.
					{
						building.elevator.maxFloor=unitTAwaiting.get(j).GetWantTo();
					}
					
					unitTPassengers.add(unitTAwaiting.get(j));
					
					tempCapacity--;
					System.out.println("������� ����� � ����. ��� ����� �� "+(unitTPassengers.get(unitTPassengers.size()-1).GetWantTo()+1)+" ����. �������� " + tempCapacity+" �����");							
				}		
			else
				tAwaiting.add(unitTAwaiting.get(j));
			}	
		unitTAwaiting= new ArrayList<User>();
		unitTAwaiting.addAll(0, tAwaiting);	
		}
		System.out.println("------------");
		building.PassengeresTransaction();
		
		Assert.assertEquals(unitTPassengers,building.elevator.passengers);//��������� ������ ����������, ��������� ���� ���������� ������������� �������� ��� ������ �� ����� ������ ����� ������ ����������
		
		
	}

}
