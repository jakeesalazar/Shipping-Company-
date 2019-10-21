/** The class Box is a subclass of Parcel.

  @author Martin Cu && Jacob Salazar
  @version 1.0
*/
import java.util.*;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class Box extends Parcel {

	/**	This constructor recieves an integer used to designate
		the dimension and type of parcel.

		@param i an integer between 1 to 6 representing the different sizes and types
		of parcel.
	*/
	public Box(int i)
	{
		super(i);
		super.items = new ArrayList <Item> ();
	}


	/**	This method initializes the number of items in the parcel.
	*
	*	@param n a non-negative whole number
	*/
	public void setNum(int n)
	{
		if (n > 0)
			super.numItems = n;
		else
			super.numItems = 0;
	}

	/**	This method returns an item from the item arraylist.
	*	@param i an integer index
	*	@return Item object
	*/
	public Item getItem(int i)
	{
		if (i >= 0)
			return super.items.get(i);
		else
			return null;
	}
	
	/**	This method returns the total weight of the parcel.
		@return double total weight of all items
	*/
	public double getTotalWeight()
	{
		double totalWeight = 0.00;

		for (int i = 0;i < super.items.size(); i++)
		{
			if (super.items.get(i) instanceof Document)
				totalWeight += ((Document)super.items.get(i)).getWeight();
			else if (super.items.get(i) instanceof Regular)
				totalWeight += ((Regular)super.items.get(i)).getWeight();
			else if (super.items.get(i) instanceof Irregular)
				totalWeight += ((Irregular)super.items.get(i)).getWeight();
		}
		totalWeight = Math.ceil(totalWeight);
		
		return totalWeight;
	}

	/**	This method returns the total volume of the items in the parcrl
	*
	*	@return double total volume of items
	*/
	public double getTotalVolume()
	{
	double totalVol = 0.00;

		for (int i = 0; i < super.items.size(); i++)
		{
			if (super.items.get(i) instanceof Document)
				totalVol += ((Document)super.items.get(i)).getVolume();
			else if (super.items.get(i) instanceof Regular)
				totalVol += ((Regular)super.items.get(i)).getVolume();
			else if (super.items.get(i) instanceof Irregular)
				totalVol += ((Irregular)super.items.get(i)).getVolume();
		}

		totalVol = Math.ceil(totalVol);
		return totalVol;
	}

	/**	This method returns the parcel tracking number.
	*
	*	@return String tracking number
	*/
	public String getTrackInfo()
	{
		return super.trackNumber;
	}
	
	/**	This method returns the width of the parcel
	*
	*	@return double width of parcel
	*/
	public double getW()
	{
		return super.width;
	}
	/**	This method returns the length of the parcel
	*
	*	@return double length of parcel
	*/
	public double getL()
	{
		return super.length;
	}
	
	/**	This method returns the height of the parcel
	*
	*	@return double height of parcel
	*/
	public double getH ()
	{
		return super.height;
	}
	
	/**	This method returns the number of items in the parcel
	*
	*	@param int number of items in parcel
	*/
	public int getNum()
	{
		return super.items.size();
	}
	
	/**	This method adds an item object into the parcel.
		@param product item object
	*/
	public void addItem(Item product)
	{
		super.items.add(product);
	}

	/**	This method takes an object item and remove it from the
		arraylist of items in the container.
		@param product item object
	*/
	public void removeItem(Item product)
	{
		super.items.remove(product);
	}
	
	/**	This method returns the parcel size of the parcel.
		@return String parcel name
	*/
	public String parcelSize()
	{
		String size ="";

		if (super.length == 12 && super.width == 10 && super.height == 5)
			size = "Small Box Parcel";
		else if (super.length == 14 && super.width == 11 && super.height == 7)
			size = "Medium Box Parcel";
		else if (super.length == 18 && super.width == 12 && super.height == 9)
			size = "Large Box Parcel";
		else if (super.length == 20 && super.width == 16 && super.height == 12)
			size = "Extra Large Box Parcel";
		else
			size = "UNK";
		return size;
	}
	
	/**	This method sets the parcel tracking number.
		@param info String tracking number
	*/
	public void setTrackInfo(String info)
	{
		super.trackNumber = info;
	}


}
