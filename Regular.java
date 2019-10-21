/** The class Regular is subclass of Product.

 	@author Martin Cu && Jacob Salazar
 	@version 1.0
*/

public class Regular extends Product {

	/**	This constructor represents a regular shaped product.
	*
	*	@param length a positive number representing the object's length
	*	@param width a positive number representing the object's width
	*	@param height a positive number representing the object's height
	*	@param weight a positive number representing the object's weight
	*/
	public Regular (double length, double width, double height,double weight)
	{
		super(length,width, height, weight);
	}
	
	/**	This method returns a String representation of the Document's length, width, and height.	*/
	public String toString ()
	{
		return super.getLength() + " x " + super.getWidth() + " x " + super.getHeight() ;
	}
}
