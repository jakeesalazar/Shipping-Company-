
public class Test
{

	public static void main(String[] args)
	{
		Product p;
		Item i;
		Parcel j;
		
		i = new Item(5, 5);
		System.out.println(i.getWeight() );
		
		j = new Box(5.5);
	}
	
}