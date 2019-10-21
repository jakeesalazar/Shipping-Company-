/**	The class Item represents an object which has a length, width, volume, and weight.
 *	It has a length, width, height, weight, and volume.
 *
 *	@author Martin Cu && Jacob Salazar
 */
public class Item {

	/**	This constructor takes a parameter length and width
		which serves as its measurement.
		@param length positive value length measurement
		@param width positive value width measurement
	*/
	public Item (double length, double width)
	{
		this.length = length;
		this.width = width;
	}
	
	/**	This method returns the width of the item.
	*
	*	@return double width measurement
	*/
	public double getWidth ()
	{
		return width;
	}
	
	/**	This method returns the length of the item.
	*
	*	@return double length measurement
	*/
	public double getLength()
	{
		return length;
	}
	
	/**	This method returns the height of the item.
	*
	*	@return double height measurement
	*/
	public double getHeight()
	{
		return height;
	}
	
	/**	This method returns the weight of the item.
	*
	*	@return double weight of item
	*/
	public double getWeight()
	{
		return weight;
	}
	
	/**	This method returns the volume of the item.
	*
	*	@return double volume of item
	*/
	public double getVolume()
	{
		return volume;
	}

	protected  double  volume;
	protected  double  length;
	protected  double  width;
	protected  double  height;
	protected  double  weight;
}
