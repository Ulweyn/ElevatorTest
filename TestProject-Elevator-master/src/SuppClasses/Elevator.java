package SuppClasses;
import java.util.ArrayList;

public class Elevator {

	private int capacity=5;//общая вместимость лифта
	public int currentFloor;
	public int currentCapacity;//сколько человек может еще зайти в лифт
	public int maxFloor=1;//по умолчанию равен 2 этажу(сдвиг на единицу по отношению к отображаемому на экране), далее будет изменятся в зависимости от заходящих пассажиров
	private boolean direction;//Направление движения лифта true - вверх, false - вниз. 
	public ArrayList<User> passengers;
	
	public int GetCapacity()
	{
		return capacity;
	}
	
	public boolean GetDirection()
	{
		return direction;
	}
	public void SetDirection(boolean direction)//используется только для теста
	{
		this.direction=direction;
	}
	
	public void ChangeDirection()//Меняем направление движения лифта
	{
		direction=!direction;
	}
	
	public Elevator()
	{
		currentFloor=0;//При создании лифта начинаем с первого этажа
		currentCapacity=capacity;//При создании лифт пуст
		direction=true;
		passengers=new ArrayList<User>();
	}
}
