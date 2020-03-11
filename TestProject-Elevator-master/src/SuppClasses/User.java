package SuppClasses;
import java.util.ArrayList;
import java.lang.Math;
//Собственно, наши пассажиры
public class User {
	private int wantTo;//куда хочет попасть
	private int placedAt;//на каком этаже находится
	
	public void SetWantTo(int wantTo)
	{
		this.wantTo=wantTo;
	}
	public void SetPlacedAt(int placedAt)
	{
		this.placedAt=placedAt;
	}
	public int GetWantTo()
	{
		return wantTo;
	}
	public int GetPlacedAt()
	{
		return placedAt;
	}
	public void SetWantToRand(int placedAt, int storeys)
	{
		int WantTo=placedAt;
		while(WantTo==placedAt)//Рандомим желаемый этаж так, чтобы он не совпадал с текущим
		{
			WantTo=(int)(Math.random()*(storeys));
		}
		SetWantTo(WantTo);
	}
	public boolean WantToDir()
	{
		return wantTo>placedAt;//Если нужно наверх - вернет true, вниз - false
	}
	public User(int placedAt, int storeys)
	{
		SetPlacedAt(placedAt);		
		SetWantToRand(placedAt, storeys);
	}
	
}
