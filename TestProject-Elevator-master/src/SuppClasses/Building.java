package SuppClasses;
import java.util.ArrayList;
import java.util.Scanner;

//Управляющий класс. Так как в нем создается лифт и этажи, а также определяется этажность - назвал его "Здание"
public class Building {
private int storeys;
public Elevator elevator;
public ArrayList<Floor> floors;
boolean flag=true;//сравнивает куда направляется лифт
public int GetStoreys()
{
	return storeys;
}

public Building()
{
	floors=new ArrayList<Floor>();
	storeys=(int)(Math.random()*16+5);
	for(int i=0;i<storeys;i++)
	{
		floors.add(i,new Floor(i,storeys));
		
	}
	elevator=new Elevator();
	System.out.println("В здании " +storeys+" этажей");
}

public void PassengeresTransaction()//Метод, перемещающий пасажиров в лифт и из лифта. Также содержит одну из проверок на максимальный этаж.
{
	ArrayList<User> tPassengers= new ArrayList<User>();
	
	if(elevator.passengers.size()!=0)
	{
		for(int j=0;j<elevator.passengers.size();j++)//выходят из лифта
		{
		
			if(elevator.passengers.get(j).GetWantTo()==elevator.currentFloor)
			{
				elevator.passengers.get(j).SetWantToRand(elevator.currentFloor, storeys);//рандомим выходящему пассажиру новый требуемый этаж
				elevator.passengers.get(j).SetPlacedAt(elevator.currentFloor);
				floors.get(elevator.currentFloor).awaiting.add(elevator.passengers.get(j));
				elevator.currentCapacity++;
				System.out.println("Человек вышел из лифта. В лифте " + elevator.currentCapacity+" места");				
			}
			else
				tPassengers.add(elevator.passengers.get(j));
		}
		elevator.passengers=new ArrayList<User>();
		elevator.passengers.addAll(0,tPassengers);
		
	}
	//
	ArrayList<User> tAwaiting= new ArrayList<User>();
	if(floors.get(elevator.currentFloor).awaiting!=null)
	{
	for(int j=0;j<floors.get(elevator.currentFloor).awaiting.size() ;j++)//заходят в лифт
		{	
		
		if(floors.get(elevator.currentFloor).awaiting.get(j).WantToDir()==elevator.GetDirection() && elevator.currentCapacity>0) 
			{
				if(elevator.GetDirection()==true && floors.get(elevator.currentFloor).awaiting.get(j).GetWantTo()>elevator.maxFloor)//меняем максимальный этаж в соответствии с новыми пасажирами
				{
					elevator.maxFloor=floors.get(elevator.currentFloor).awaiting.get(j).GetWantTo();
				}
				
				if(elevator.GetDirection()==false && floors.get(elevator.currentFloor).awaiting.get(j).GetWantTo()<elevator.maxFloor)//ситуация та же, но едем вниз.
				{
					elevator.maxFloor=floors.get(elevator.currentFloor).awaiting.get(j).GetWantTo();
				}
				
				elevator.passengers.add(floors.get(elevator.currentFloor).awaiting.get(j));
				
				elevator.currentCapacity--;
				System.out.println("Человек зашел в лифт. Ему нужно на "+(elevator.passengers.get(elevator.passengers.size()-1).GetWantTo()+1)+" этаж. Осталось " + elevator.currentCapacity+" места");							
			}		
		else
			tAwaiting.add(floors.get(elevator.currentFloor).awaiting.get(j));
		}	
	floors.get(elevator.currentFloor).awaiting= new ArrayList<User>();
	floors.get(elevator.currentFloor).awaiting.addAll(0, tAwaiting);	
	}
	
	
}

public void GoUp()
{
	
	
	if(elevator.currentFloor==storeys-1 ||(elevator.passengers.size()==0 && floors.get(elevator.currentFloor).WhereToGo()==false))
	{
		elevator.ChangeDirection();
		flag=false;
		System.out.println("Теперь вы направляетесь вниз");
	}
	else {
	if(elevator.currentFloor==elevator.maxFloor && elevator.currentCapacity==elevator.GetCapacity() && floors.get(elevator.currentFloor).WhereToGo()==false && elevator.currentFloor!=storeys)//если в лифте пусто, а те кто снаружи не может решить куда ехать
		elevator.maxFloor++;
	
	elevator.currentFloor++;
	
	}
}

public void GoDown()
{
	
	
	if(elevator.currentFloor==0 || (elevator.passengers.size()==0 && floors.get(elevator.currentFloor).WhereToGo()==true))
	{
		elevator.ChangeDirection();
		flag=true;
		System.out.println("Теперь вы направляетесь вверх");
	}
	else {
	if(elevator.currentFloor==elevator.maxFloor && elevator.currentCapacity==elevator.GetCapacity() && floors.get(elevator.currentFloor).WhereToGo()==false && elevator.currentFloor!=0)
		elevator.maxFloor--;
	
	elevator.currentFloor--;
	
	}
}

public void ElevatorMovement()//Движение лифта
{
	
	Scanner in = new Scanner(System.in);
	
	
		while(elevator.GetDirection()==flag)
		{			
			System.out.println("Вы на "+(elevator.currentFloor+1) + " этаже");
			floors.get(elevator.currentFloor).AwaitingLog();
			PassengeresTransaction();
			floors.get(elevator.currentFloor).AwaitingLog();//Люди вышли из лифта и этаж, который им требуется поменялся. Следовательно они могут опять туда зайти и поехать дальше. Также дублируем этот лог, чтобы показать изменения в случае миграций пассажиров.
			if(flag==true)
				GoUp();
			else
				GoDown();			
			
			int a=in.nextInt();
			if(a==0)
			{
				return;//Просто точка остановки. Лифт в идеале не прекращает работать в принципе
			}
		}
			
		in.close();
}


}
