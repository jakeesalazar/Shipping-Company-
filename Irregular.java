/** The class Regular is subclass of Product.

 	@author Martin Cu && Jacob Salazar
 	@version 1.0
*/

public class Irregular extends Product {

	/**	This constructor represents an irregular shaped product.
	*
	*	@param length a positive number representing the object's length
	*	@param width a positive number representing the object's width
	*	@param height a positive number representing the object's height
	*/
	public Irregular (double length, double width, double height,double weight2)
	{
		super(length,width, height, weight2);
		double weight1 = length*width*height/305;

		if (weight2 >= weight1)
			super.weight = weight2;
		else if (weight1 > weight2)
			super.weight = weight1;
	}

}
