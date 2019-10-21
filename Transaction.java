/** The class Transaction represents each successful transaction made by Johnny Moves.

 	@author Martin Cu && Jacob Salazar
 	@version 1.0
*/
import java.util.*;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class Transaction
{
	/**	This constructor takes a parcel, name, destination, and parcel number
		in order to make a transaction object.
		@param p parcel object
		@param n String parcel recipient
		@param dest String parcel destination
		@param num int parcel number
	*/
	public Transaction (Parcel p,String n,String dest,int num)
	{
		parcel = p;
		recipient = n;
		destination = dest;
		parcelnum = num;
		if (dest.equalsIgnoreCase("Metro Manila"))
			destination = "MML";
		else if (dest.equalsIgnoreCase("Provincial Luzon"))
			destination = "LUZ";
		else if (dest.equalsIgnoreCase("Visayas"))
			destination = "VIS";
		else if (dest.equalsIgnoreCase("Mindanao"))
			destination = "MIN";
		else
			destination = "UNK";
	}
	/**	This method returns the parcel number of the transaction.
		@return parcelnum int parcel number
	*/
	public int getParcelnum()
	{
		return parcelnum;
	}
	/**	This method returns the parcel of the transaction.
		@return parcel parcel object of the transaction
	*/
	public Parcel getParcel()
	{
		return parcel;
	}
	/**	This method returns the parcel recipient.
		@return recipient String parcel recipient
	*/
	public String getRecipient()
	{
		return recipient;
	}
	/**	This method returns the parcel destination.
		@return destination String parcel destination
	*/
	public String getDestination()
	{
		return destination;
	}
	/**	This method returns the startDate of parcel delivery.
		@return startDate LocalDate start date of delivery
	*/
	public LocalDate getStartDate()
	{
		return startDate;
	}
	/**	This method returns the delivery date of parcel delivery.
		@return deliveryDate LocalDate delivery date of parcel
	*/
	public LocalDate getDeliveryDate()
	{
		return deliveryDate;
	}
	/**	This method returns true or false status of insurance
		@return insured boolean true or false of insurance
	*/
	public boolean getInsured()
	{
		return insured;
	}
	/**	This method returns the shipping status of the transaction.
		@return status String status of the delivery
	*/
	public String getStatus()
	{
		return status;
	}
	/**	This method returns the fee of the transaction.
		@return fee double total fee of the delivery
	*/
	public double getFee()
	{
		return fee;
	}
	/**	This method assigns the start date and delivery date of the transaction.
		@param date LocalDate date of transaction creation
	*/
	public void setStartDate(LocalDate date)
	{
		this.startDate = date;

		String x = getDestination();

		if(x.equalsIgnoreCase("MML"))
		this.deliveryDate = this.startDate.plusDays(2);
		else if (x.equalsIgnoreCase("LUZ"))
		this.deliveryDate = this.startDate.plusDays(3);
		else if(x.equalsIgnoreCase("VIS"))
		this.deliveryDate = this.startDate.plusDays(5);
		else if (x.equalsIgnoreCase("MIN"))
		this.deliveryDate = this.startDate.plusDays(8);
		else
		this.deliveryDate = this.startDate;
	}
	/**	This method sets the parcel's delivery status.
		@param i String status of delivery
	*/
	public void setStatus(String i)
	{
		if (i == null)
			status = "UNK";
		else
			status = i;
	}
	/**	This method returns a String representation of the start date.
		@return formattedString String representation of start date
	*/
	public String strDate()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM - dd - yyyy");

		LocalDate sc = getStartDate();

		String formattedString = sc.format(formatter);

		return formattedString;
	}
	/**	This method initializes the insurance status of the parcel.
	*
	*	@param o boolean value of insurance
	*/
	public void setInsured(boolean o)
	{
		insured = o;
	}

	/**	This method initializes the total fee for shipment.
	*
	*	@param n double value fee
	*/
	public void setFee(double n)
	{
		fee = n;
	}
	/**	This method returns a String representation of the delivery date.
		@return endDate String representation of delivery date
	*/
	public String endDate()
	{
		String endDate = "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM - dd - yyyy");
		LocalDate sc = getStartDate();
		String x = getDestination();

		if(x.equalsIgnoreCase("MML"))
			sc = sc.plusDays(2);
		else if (x.equalsIgnoreCase("LUZ"))
			sc = sc.plusDays(3);
		else if(x.equalsIgnoreCase("VIS"))
			sc = sc.plusDays(5);
		else if (x.equalsIgnoreCase("MIN"))
			sc = sc.plusDays(8);

		endDate = sc.format(formatter);
		return endDate;
	}
	/**	This method sets the number of items of the transaction.
		@param x positive integer number of items
	*/
	public void setNumberofItem(int x)
	{
		numItem = x;
	}
	/**	This method returns the number of items in the transaction.
		@return numItem number of items in the transaction
	*/
	public int getNumberofItem()
	{
		return numItem;
	}
	/**	This method sets the transaction destination.
		@param dest String destination of delivery
	*/
	public void setDestination(String dest)
	{
		destination = dest;
	}

	private int numItem;
	private int parcelnum;
	private Parcel parcel;
	private String recipient;
	private String destination;
	private LocalDate startDate;
	private LocalDate deliveryDate;
	private boolean insured;
	private String status;
	private double fee;
}
