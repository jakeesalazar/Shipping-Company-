/** The class Document is subclass of Item. Besides length and width,
	it also has a number of pages.

 	@author Martin Cu && Jacob Salazar
 	@version 1.0
*/

public class Document extends Item
{
	/**	This constructor takes a parameter length, width, and numPages
		as the measurement of the Document object.
		@param length positive value length measurement
		@param width positive value value width measurement
		@param numPages positive integer value number of pages
	*/
	public Document(double length,double width,int numPages)
	{
		super(length,width);
		this.numPages = numPages;
		super.height = Math.ceil((double)numPages/25);
		double weight = (((double)numPages/25)*200)/1000;

		if (weight >= 1)
			super.weight = weight;
		else if (weight < 1 && weight >= 0)
			super.weight = 1;
		super.volume = length*width*height;
	}
	/**	This method returns the number of pages of the document.
		@return numPages int number of pages of the object
	*/
	public int getnumPages()
	{
		return numPages;
	}

	private int numPages;
}
