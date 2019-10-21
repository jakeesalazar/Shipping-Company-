/** The class Parcel represents a container object which can be of type Box or Flat.
	It can contain an ArrayList of Items. It has a length, width, and height, as well
	as weight and volume. It also has a tracking number.

  @author Martin Cu && Jacob Salazar
  @version 1.0
*/

import java.util.*;
import java.util.ArrayList;
import java.time.*;

public abstract class Parcel {
	
	/**	This constructor recieves ans integer used to designate
	the dimension and type of parcel.

	@param i an integer between 1 to 6 representing the different sizes and types
	of parcel.
	*/
	public Parcel (int i)
	{
		items = new ArrayList <Item> ();
		switch (i) 
		{
			case 0:	length = 9;
					width = 14;
					height = 1;
					parcelType = "FLT";
					break;
			case 1:	length = 12;
					width = 18;
					height = 3;
					parcelType = "FLT";
					break;
			case 2:	length = 12;
					width = 10;
					height = 5;
					parcelType = "BOX";
					break;
			case 3:	length = 14;
					width = 11;
					height = 7;
					parcelType = "BOX";
					break;
			case 4:	length = 18;
					width = 12;
					height = 9;
					parcelType = "BOX";
					break;
			case 5:	length = 20;
					width = 16;
					height = 12;
					parcelType = "BOX";
					break;
			default: length = 0;
					width = 0;
					height = 0;
					parcelType = "UNK";
		}
	}

	/**	This method returns the total volume of the items in the parcrl
	*
	*	@return double total volume of items
	*/
	public abstract double getTotalVolume();

	/**	This method returns the parcel tracking number.
	*
	*	@return String tracking number
	*/
	public abstract String getTrackInfo();

	/**	This method returns the type of the parcel.
	*
	*	@return String type of the parcel
	*/
	public String getType()
	{
		return parcelType;
	}

	/**	This method returns the width of the parcel
	*
	*	@return double width of parcel
	*/
	public abstract double getW();

	/**	This method returns the length of the parcel
	*
	*	@return double length of parcel
	*/
	public abstract double getL();

	/**	This method returns the height of the parcel
	*
	*	@return double height of parcel
	*/
	public abstract double getH ();
	/**	This method returns the number of items in the parcel
	*
	*	@param int number of items in parcel
	*/
	public abstract int getNum();

	/**	This method adds an item object into the parcel.
		@param product item object
	*/
	public abstract void addItem(Item product);
	
	/**	This method returns the parcel size of the parcel.
		@return String parcel name
	*/
	public abstract String parcelSize();
	
	/**	This method sets the parcel tracking number.
		@param info String tracking number
	*/
	public abstract void setTrackInfo(String info);
	
	/**	This method returns an item in the parcel.
		@param i non-negative integer value
	*/
	public abstract Item getItem(int i);
	
	/**	This method returns the total weight of the parcel.
		@return double total weight of all items
	*/
	public abstract double getTotalWeight();

	protected ArrayList<Item> items;
	protected String parcelType;
	protected double weight;
	protected double volume;
	protected int numItems;
	protected int width;
	protected int length;
	protected int height;
	protected String trackNumber = "";
}
