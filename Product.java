/** The class Product is subclass of Item.

 	@author Martin Cu && Jacob Salazar
 	@version 1.0
*/
public abstract class Product extends Item
{
	/**	This constructor takes a length, width, height, and weight1
		in order to create a Product.
		@param length a positive number representing the object's length
	*	@param width a positive number representing the object's width
	*	@param height a positive number representing the object's height
	*	@param weight a positive number representing the object's weight
	*/
	public Product(double length, double width, double height, double weight) 
	{
		super(length,width);
		super.height = height;
		super.volume = length*width*height;
		
		if (weight >= 1)
			super.weight = weight;
		else if (weight < 1 && weight > 0)
			super.weight =  Math.ceil(weight);
		else
			super.weight = 1;
	}
	
	/**	This method returns a String representation of the Document's length, width, and height.	*/
	public String toString()
	{
		return super.getLength() + " x " + super.getWidth() + " x " + super.getHeight() ;
	}
}