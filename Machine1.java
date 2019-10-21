/**	The clss Machine represents the self-checkout machine which handles Johnny Move's
 *	service. It is capable of creating transactions for users, checking for which parcel
 *	can accomodate the user's list of items, offer the available parcels, generate the
 *	tracking information, generation of reports, tracking of parcels, computing the fee
 *	for delivery, and updating the status of shipment.
 *
 *	@author Martin Cu && Jacob Salazar
 */
import java.util.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.util.ArrayList.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class Machine1 {

    /**	This constructor represents the self-checkout machine.	*/
	public Machine1()
	{
		int i =0;
		standardParcel = new Parcel[6];

		for (i = 0; i < 4; i++)
		{
			standardParcel[i] = new Box(i);
		}

		for (i = 4; i < 6;i++){
			standardParcel[i] = new Flat(i);
		}

		Database = new ArrayList <Transaction>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");
		currDate = LocalDate.now();
		numberOfParcels = 1;
		seq = 1;
		password = "admin123";
	}
	
	/**	This method calculates the total fee of shipment.
	*
	*	@param p Parcel being transacted
	*	@param n int representing the type of parcel
	*	@return double total fee for delivery
	*/
	public Transaction getCurrTransaction()
	{
		return (Database.get((Database.size() ) ) );
	}
	/**	This method returns the base fee of a parcel.
		@param p parcel object
		@return basefee double fee based on parcel type
	*/
	public double getBaseFee(Parcel p)
	{
		if (  (p.getType()).equalsIgnoreCase("FLT") &&  p.getL() == 9 && p.getW() == 14 && p.getH() == 1  )
		return 30;
		if ( (p.getType()).equalsIgnoreCase("FLT") &&  p.getL() == 12 && p.getW() == 18 && p.getH() == 3  )
		return 50;
		if ((p.getType().equalsIgnoreCase("BOX")))
		return 50;

		return 50;
	}
	/**	This method returns the parcel rate.
		@param p parcel object
		@return shortDouble double fee of the parcel based on
		parcel type and weight
	*/
	public double getParcelRate(Parcel p)
	{
		double vw = 0; // volumetric weight;
		double kilo = 0; // kilos
		double baseRate = 0;

		if (  (p.getType()).equalsIgnoreCase("FLT") &&  p.getL() == 9 && p.getW() == 14 && p.getH() == 1  )
			return  30;
		else if ( (p.getType()).equalsIgnoreCase("FLT") &&  p.getL() == 12 && p.getW() == 18 && p.getH() == 3  )
			return  50;
		else
		{
			for (int i = 0; i < p.getNum(); i++)
			{
				if (p.getItem(i) instanceof Regular )
				{
					kilo = ((Regular)p.getItem(i)).getWeight();
					if(kilo <= 0)
					kilo = 1;
					baseRate += kilo * 40;
				}
				else if ( p.getItem(i) instanceof  Irregular )
				{
					vw = ((Irregular)p.getItem(i)).getVolume() / 305;
					kilo = ((Irregular)p.getItem(i)).getWeight();
					if (vw * 30 >= kilo * 40)
					baseRate += vw * 30;
					else
					baseRate += kilo * 40;
				}
				else if ( p.getItem(i) instanceof  Document )
				{
					kilo = ((Document)p.getItem(i)).getWeight();
					baseRate += kilo * 40;
				}
			}
		}
		int temp = (int)(baseRate*100.0);
		double shortDouble = ((double)temp)/100.0;

		return shortDouble;
	}
	/**	This method returns the service fee of the parcel based on destination.
		@param p parcel object
		@param d String destination of the parcel
		@return fee double fee of the parcel based on destination
	*/
	public double getServiceFee(Parcel p,String d)
	{
		double fee =0;
		if(d.equalsIgnoreCase("Metro Manila"))
			return 50;
		else if (d.equalsIgnoreCase("Provincial Luzon"))
			return 100;
		else if (d.equalsIgnoreCase("Visayas"))
		{
			if (1000 >= p.getTotalVolume() * 0.10 && 1000 >= p.getTotalWeight() * 0.10)
				return  1000;
			else if (p.getTotalVolume() * 0.10 >= 1000 && (p.getTotalVolume() * 0.10) > (p.getTotalWeight() * 0.10))
				return  p.getTotalVolume() * 0.10;
			else if (p.getTotalWeight() * 0.10 >= 1000 && (p.getTotalWeight() * 0.10) > (p.getTotalVolume() * 0.10))
				return p.getTotalWeight() * 0.10;
		}
		else if (d.equalsIgnoreCase("Mindanao"))
		{
			if (3000 >= p.getTotalVolume() * 0.25 && 3000 >= p.getTotalWeight() * 0.25)
				return  3000;
			else if (p.getTotalVolume() * 0.25 >= 3000 && (p.getTotalVolume() * 0.25) > (p.getTotalWeight() * 0.25))
				return  p.getTotalVolume() * 0.25;
			else if (p.getTotalWeight() * 0.10 >= 3000 && (p.getTotalWeight() * 0.25) > (p.getTotalVolume() * 0.25))
				return  p.getTotalWeight() * 0.25;
		}
		return fee;
	}
	/**	This method returns the fee inccurred from parcel insurance.
		@param p parcel object
		@param insured true or false value representing its insurance status
		@return fee double fee of the parcel based on insurance
	*/
    public double getInsuranceFee(Parcel p, boolean insured)
	{
		double fee= 0;

		if (insured)
			fee = p.getNum() * 5;

		return fee;
    }
	/**	This method returns the total fee for parcel delivery.
		@param p parcel object
		@param dest String destination of the parcel
		@param opt true or false value representing insurance status
		@return Total double total fee for delivery
	*/
	public double computeFee(Parcel p, String dest, boolean opt)
	{
		double Total;

		double  ParcelRate =  getParcelRate (p);
		double  serviceRate =  getServiceFee (p, dest);
		double insuranceFee = getInsuranceFee(p, opt);
		Total = ParcelRate + serviceRate + insuranceFee;

		return Total;
	}

    /**	This method checks which parcel can contain all the items in the arraylist.
	*
	*	@param itemList arraylist of items
	*	@return integer array of integer representing the types of parcel available
	*/
	public int[] checkFit (ArrayList<Item> itemList)
	{
		int available = 6;
		double temp1=0, temp2=0, temp3=0, Len = 0, Wid = 0, Hei = 0,tempVol = 0;
		boolean possible = false;
		double L = 0, W = 0, H = 0;
		boolean case1 = true;

		int[] arr = new int[7];
		for (int i = 0; i < 7;i++)
		arr[i] = 1;

		// case 1 if all of the items are documents:
		for (int i = 0;i < itemList.size();i++)
		{
			if (!(itemList.get(i) instanceof Document))
				case1 = false;
		}
		if (case1)
		{
			for (int i = 0; i < itemList.size();i++)
			{
				if ((itemList.get(i)).getLength() > L)
					L = (itemList.get(i)).getLength();
				if ((itemList.get(i)).getWidth() > W)
					W = (itemList.get(i)).getWidth();
				// Assume that you will stack them to one another so the h will be added
				H = H + ((Document)itemList.get(i)).getHeight();
			}
			
			for (int i = 0; i < 6;i++)
			{
				// Each iteration represents the dimensions of each standard size Box
				// i = 0  has dimensions 12 x 10 x 5, then i = 1 has dimensions 14 x 11 x 7
				Len = standardParcel[i].getL();
				Wid = standardParcel[i].getW();
				Hei = standardParcel[i].getH();

				if (Len >= L && Wid >= W && Hei >= H )
				{
					temp1 = Len/L;
					temp2 = Wid/W;
					temp3 = Hei/H;

					if (temp1 >= 1 && temp2 >= 1 && temp3 >=1 )
						possible = true;
				}

				if (Len >= W && Wid >= L && Hei >= H )
				{
					/// W X L X H
					temp1 = Len/W;
					temp2 = Wid/L;
					temp3 = Hei/H;

					if (temp1 >= 1 && temp2 >= 1 &&  temp3 >= 1 )
						possible =true;
				}

				if (possible == false)
				{
					arr[i+1] = 0;
					available--;
				}
				else
					possible = false;
			}
			return arr;
		}
		
		else
		{
			// Assume that the first item inside the itemlist ArrayList has the largest l, w, h
			if (itemList.get(0) instanceof Document)
			{
				L = (itemList.get(0)).getLength();
				W = (itemList.get(0)).getWidth();
				H = ((Document)itemList.get(0)).getHeight();
			}
			else if (itemList.get(0) instanceof Regular)
			{
				L = (itemList.get(0)).getLength();
				W = (itemList.get(0)).getWidth();
				H = ((Regular)itemList.get(0)).getHeight();
			}
			else if (itemList.get(0) instanceof Irregular)
			{
				L = (itemList.get(0)).getLength();
				W = (itemList.get(0)).getWidth();
				H = ((Irregular)itemList.get(0)).getHeight();
			}
			
			// Use this for loop to check the highest l and w of the items, add each h.
			for (int i = 1;i < itemList.size();i++)
			{
				if (itemList.get(i) instanceof Regular)
				{
					//Assume that you will stack the items to the greatest l and greatest w
					if ((itemList.get(i)).getLength() > L)
						L = (itemList.get(i)).getLength();
					if ((itemList.get(i)).getWidth() > W)
						W = (itemList.get(i)).getWidth();
					// Assume that you will stack them to one another so the h will be added
					H = H + ((Regular)itemList.get(i)).getHeight();
				}
				else if (itemList.get(i) instanceof Irregular)
				{
					//Assume that you will stack the items to the greatest l and greatest w
					if ((itemList.get(i)).getLength() > L)
						L = (itemList.get(i)).getLength();
					if ((itemList.get(i)).getWidth() > W)
						W = (itemList.get(i)).getWidth();
					// Assume that you will stack them to one another so the h will be added
					H = H + ((Irregular)itemList.get(i)).getHeight();
				}
				else if (itemList.get(i) instanceof Document)
				{
					//Assume that you will stack the items to the greatest l and greatest w
					if ((itemList.get(i)).getLength() > L)
						L = (itemList.get(i)).getLength();
					if ((itemList.get(i)).getWidth() > W)
						W = (itemList.get(i)).getWidth();
					// Assume that you will stack them to one another so the h will be added
					H = H + ((Document)itemList.get(i)).getHeight();
				}
			}
			
			/// Check whatever rotations could be possible to fit the items inside the container
			for (int i = 0; i < 6;i++)
			{
				// Each iteration represents the dimensions of each standard size Box
				// i = 0  has dimensions 12 x 10 x 5, then i = 1 has dimensions 14 x 11 x 7
				Len = standardParcel[i].getL();
				Wid = standardParcel[i].getW();
				Hei = standardParcel[i].getH();
				
				/// Arrange it to L X W X H
				if (Len >= L && Wid >= W && Hei >= H )
				{
					temp1 = Len/L;
					temp2 = Wid/W;
					temp3 = Hei/H;
					
					if (temp1 >= 1 && temp2 >= 1 && temp3 >=1 )
						possible = true;
				}
				// W X H X L
				if (Len >= W && Wid >= H && Hei >= L )
				{
					temp1 = Len/W;
					temp2 = Wid/H;
					temp3 = Hei/L;
					
					if (temp1 >= 1 && temp2 >= 1 && temp3 >=1 )
						possible = true;
				}

				if (Len >= W && Wid >= L && Hei >= H )
				{
					/// W X L X H
					temp1 = Len/W;
					temp2 = Wid/L;
					temp3 = Hei/H;

					if (temp1 >= 1 && temp2 >= 1 &&  temp3 >= 1 )
						possible =true;
				}
				// H X L X W
				if (Len >= H && Wid >= L && Hei >= W )
				{
					temp1 = Len/H;
					temp2 = Wid/L;
					temp3 = Hei/W;
					
					if (temp1 >= 1 && temp2 >= 1  && temp3 >=1 )
						possible = true;
				}
				/// H X W X L
				if (Len >= H && Wid >= W && Hei >= L )
				{
					temp1 = Len/H;
					temp2 = Wid/W;
					temp3 = Hei/L;
					
					if (temp1 >= 1 && temp2 >= 1 && temp3 >= 1 )
						possible = true;
				}
				/// L X H X W
				if (Len >= L && Wid >= H && Hei >= W )
				{
					temp1 = Len/H;
					temp2 = Wid/W;
					temp3 = Hei/L;
					
					if (temp1 >= 1 && temp2 >= 1 && temp3 >= 1 )
						possible = true;
				}
				/// If one possible rotation is found then it the parcel will not be removed from the list
				if (possible == false)
				{
					arr[i+1] = 0;
					available--;
				}
				else
					possible = false;
			}
			return arr;
		}
	}
	/**	This method takes an ArrayList of <Item> objects and creates and returns a parcel object.
		@param type integer between 0 to 5 representing the type of parcel to be created
		@param list ArrayList of <Item> objects to be added into the parcel
	*/
	public Parcel createParcel(int type, ArrayList<Item> list)
	{
		Parcel p;
		
		if (type == 1 || type == 0)
			p = new Flat(type);
		else
			p = new Box(type);

		for (int i = 0; i < list.size(); i++)
			p.addItem(list.get(i));
		
		return p;
	}

    /**	This method creates a parcel delivery transaction and adds its details into the
		ArrayList of <Transaction>
		@param name String parcel recipient
		@param dest String parcel destination
		@param p parcel object
		@param opt parcel insurance status
	*/
    public void createTransaction(String name,String dest, Parcel p,boolean opt)
    {
		Transaction t = new Transaction(p,name,dest,numberOfParcels);
		t.setStartDate(currDate);
		t.setStatus("Preparing");
		t.setInsured(opt);
		t.setNumberofItem(p.getNum() );
		t.setFee(computeFee(p, dest, opt));
		
		p.setTrackInfo(generateTrackInfo(p, dest) );
		
		Database.add(t);
		seq++;
    }
	/**	This method returns the number of successful transactions done by the system.
		@return Database.size() size of the ArrayList of <Transaction>
	*/
    public int getNumberofTransaction()
	{
		return Database.size();
    }
	/**	This method checks and returns if a transaction is insured.
		@param t Transaction object
		@param ans insurance status
	*/
    public String isInsured(Transaction t,boolean ans)
	{
		if (ans == true)
		{
			t.setInsured(true);
			return "Yes";
		}
		else
		{
			t.setInsured(false);
			return "No";
		}
    }
	/**	This method generates 2d array of String for basic report generation.
		@return report 2d array of String
	*/
    public String[][] getBasicReportGeneration()
	{
		String[][] report = new String[Database.size()][5];
		ArrayList <LocalDate> c = new ArrayList <LocalDate> ();

		for (int i = 0; i < Database.size();i++)
		c.add(Database.get(i).getDeliveryDate());

		Collections.sort(c);
		System.out.println(c);
		
		int p = 0; // rows of 2D array string
		for (int i = 0; i < c.size();i++)
		{
			for (int j = 0; j < Database.size();j++)
			{
				if (Database.get(j).getDeliveryDate() == c.get(i) && currDate.compareTo(Database.get(j).getDeliveryDate()) <= 0)
				{
					report[p][0] = Database.get(j).endDate();
					report[p][1] = ((Database.get(j)).getParcel()).getTrackInfo();
					report[p][3] = Database.get(j).getRecipient();
					report[p][2] = Database.get(j).getDestination();
					report[p][4] = Database.get(j).getStatus();
					p++;
				}
			}
		}
		return report;
    }

    /**	This method displays the information of all transacted parcels.	*/
    public void SeeTransactionStatus()
    {
    	int i;

    	for (i = 0; i < Database.size(); i++)
    	{
			System.out.println("Parcel receipient: " + (Database.get(i)).getRecipient());
			System.out.println("Parcel Destination: " + (Database.get(i)).getDestination());
			System.out.println("Parcel size: " + ((Database.get(i)).getParcel()).getL() + " x " + ((Database.get(i)).getParcel()).getW() + " x " + ((Database.get(i)).getParcel()).getH() );
			System.out.println("Parcel price: " + (Database.get(i)).getFee());
			System.out.println("Parcel Track info: " + ((Database.get(i)).getParcel()).getTrackInfo());
			System.out.println("----------------------------------------");
		}
    }
	/**	This method generates the tracking information of the parcel.
	*
	*	@param p current Parcel being transacted
	*	@param dest parcel destination
	*/
	public String generateTrackInfo(Parcel p, String dest)
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMdd");
		String info;
		String num = "";
		String date = currDate.format(dtf);
		String d;

		if (p.getNum() < 10)
			num = "0" + Integer.toString(p.getNum() );
		else if (p.getNum() < 100)
			num = Integer.toString(p.getNum() );

		if (dest.equalsIgnoreCase("Metro Manila"))
			d ="MML";
		else if (dest.equalsIgnoreCase("Provincial Luzon"))
			d = "LUZ";
		else if (dest.equalsIgnoreCase("Visayas"))
			d = "VIS";
		else if (dest.equalsIgnoreCase("Mindanao"))
			d = "MIN";
		else
			d = "UNK";

		info = p.getType() + date + d + num + getSeq();
		return info;
	}
    /**	This method displays the shipment status of the parcel.
     *
     *	@param info a String formatted tracking number
     */
    public String[] trackParcel(String info)
    {
		String[] ParcelInfo = new String[4];
		int i;
		boolean found = false;

		for (i = 0; i < Database.size(); i++)
		{
			if (((Database.get(i)).getParcel()).getTrackInfo().equalsIgnoreCase(info))
			{
				for (int j = 0; j<4 ;j++)
				{
					if (j==0)
						ParcelInfo[0] = ((Database.get(i)).getParcel()).parcelSize();
					if (j==1)
						ParcelInfo[1] = (Database.get(i)).strDate();
					if (j==2)
						ParcelInfo[2] = (Database.get(i)).endDate();
					if (j==3)
						ParcelInfo[3] = Database.get(i).getStatus();
					
					found = true;
				}
			}
		}
		if(found == false)
			return null;

		return ParcelInfo;
    }
	/**	This method returns the current date of the machine.
     *
     *	@return currDate a LocalDate representing the machine's current date
     */
    public LocalDate getCurrDate()
    {
    	return currDate;
    }
	/**	This method updates the current date of the machine and updates the status
	*	of all parcels.
	*/
	public void updateDate()
	{
		int i;
		currDate = currDate.plusDays(1);
		
		for (i = 0; i < Database.size(); i++)
			updateStatus( (Database.get(i) ) );
	
		seq = 1;
	}
	/**	This method updates the shipment status of the parcel based on the destination
	*	and start date of shipment.
	*
	*	@param t Transaction object
	*/
	public void updateStatus(Transaction t)
	{
		Period period = Period.between(t.getStartDate(), currDate);
		Integer dif = period.getDays();
		System.out.println(t.getStartDate() +" vs "+ currDate + t.getDestination() );
		System.out.print(dif);

		if (dif == 1)
			t.setStatus("Shipping");
		else if (dif == 2 && t.getDestination().equalsIgnoreCase("MML"))
			t.setStatus("Delivered");
		else if (dif == 3 && t.getDestination().equalsIgnoreCase("LUZ"))
			t.setStatus("Delivered");
		else if (dif == 5 && t.getDestination().equalsIgnoreCase("VIS"))
			t.setStatus("Delivered");
		else if (dif == 8 && t.getDestination().equalsIgnoreCase("MIN"))
			t.setStatus("Delivered");
	}
	/**	This method generates 2d array of String for advanced report generation.
		@return report 2d array of String
	*/
    public String[][] getAdvReport()
	{
		int[][] arr = new int[9][6];
		String[][] report = new String[9][6];

		for (int i = 0;i < arr.length;i++)
			for (int j = 0; j < arr[i].length;j++)
				arr[i][j] =0;

		for (int i = 0; i < Database.size();i++)
		{
			if (Database.get(i).getDestination().equalsIgnoreCase("MML"))
			{
				if (Database.get(i).getParcel() instanceof Flat && Database.get(i).getParcel().getH() ==1 )
					arr[2][1] = arr[2][1]+1;
				else
					arr[3][1] = arr[3][1]+1;

				if (Database.get(i).getParcel() instanceof Box)
				{
					if (Database.get(i).getParcel().getH() == 5)
						arr[4][1] = arr[4][1]+1;
					if (Database.get(i).getParcel().getH() == 7)
						arr[5][1] = arr[5][1]+1;
					if (Database.get(i).getParcel().getH() == 9)
						arr[6][1] = arr[6][1]+1;
					if (Database.get(i).getParcel().getH() == 12)
						arr[7][1] = arr[7][1]+1;
				}
			}
			
			if (Database.get(i).getDestination().equalsIgnoreCase("LUZ"))
			{
				if (Database.get(i).getParcel() instanceof Flat && Database.get(i).getParcel().getH() ==1 )
					arr[2][2] = arr[2][2]+1;
				else
					arr[3][2] = arr[3][2]+1;

				if (Database.get(i).getParcel() instanceof Box)
				{
					if (Database.get(i).getParcel().getH() == 5)
					  arr[4][2] = arr[4][2]+1;
					if (Database.get(i).getParcel().getH() == 7)
					  arr[5][2] = arr[5][2]+1;
					if (Database.get(i).getParcel().getH() == 9)
					  arr[6][2] = arr[6][2]+1;
					if (Database.get(i).getParcel().getH() == 12)
					  arr[7][2] = arr[7][2]+1;
				}
			}

			if (Database.get(i).getDestination().equalsIgnoreCase("VIS"))
			{
				if (Database.get(i).getParcel() instanceof Flat && Database.get(i).getParcel().getH() ==1 )
					arr[2][3] = arr[2][3]+1;
				else
					arr[3][3] = arr[3][3]+1;

				if (Database.get(i).getParcel() instanceof Box)
				{
					if (Database.get(i).getParcel().getH() == 5)
						arr[4][3] = arr[4][3]+1;
					if (Database.get(i).getParcel().getH() == 7)
						arr[5][3] = arr[5][3]+1;
					if (Database.get(i).getParcel().getH() == 9)
						arr[6][3] = arr[6][3]+1;
					if (Database.get(i).getParcel().getH() == 12)
						arr[7][3] = arr[7][3]+1;
				}
			}

			if (Database.get(i).getDestination().equalsIgnoreCase("MIN"))
			{
				if (Database.get(i).getParcel() instanceof Flat && Database.get(i).getParcel().getH() ==1 )
					arr[2][4] = arr[2][4]+1;
				else
					arr[3][4] = arr[3][4]+1;
				if (Database.get(i).getParcel() instanceof Box)
				{
					if (Database.get(i).getParcel().getH() == 5)
						arr[4][4] = arr[4][4]+1;
					if (Database.get(i).getParcel().getH() == 7)
						arr[5][4] = arr[5][4]+1;
					if (Database.get(i).getParcel().getH() == 9)
						arr[6][4] = arr[6][4]+1;
					if (Database.get(i).getParcel().getH() == 12)
						arr[7][4] = arr[7][4]+1;
				}
			}
		}

		for (int i = 0; i < 9;i++)
		{
			if (i == 2)
				for (int j=0;j<5;j++)
					arr[2][5] += arr[2][j];
				
			else if (i == 3)
				for (int j=0;j<5;j++)
					arr[3][5] += arr[3][j];
				
			else if (i == 4)
				for (int j=0;j<5;j++)
					arr[4][5] += arr[4][j];
				
			else if (i == 5)
				for (int j=0;j<5;j++)
					arr[5][5] += arr[5][j];
			
			else if (i == 6 )
				for (int j=0;j<5;j++)
					arr[6][5] += arr[6][j];

			else if (i == 7)
				for (int j=0;j<5;j++)
					arr[7][5] += arr[7][j];
		}

		for (int j=1;j<6;j++)
		{
			if (j ==1)
				for (int i =2;i<9;i++)
					arr[1][1] += arr[i][j];
			
			else if (j ==2)
				for (int i =2;i<9;i++)
					arr[1][2] += arr[i][j];
				
			else if (j ==3)
				for (int i =2;i<9;i++)
					arr[1][3] += arr[i][j];
				
			else if (j ==4)
				for (int i =2;i<9;i++)
					arr[1][4] += arr[i][j];
				
			else if (j ==5)
				for (int i =2;i<9;i++)
					arr[1][1] = arr[i][j];
				
			else if (j ==6)
				for (int i =2;i<9;i++)
					arr[1][6] = arr[i][j];
				
			else if (j ==7)
				for (int i =2;i<9;i++)
					arr[1][7] = arr[i][j];
		}

		for (int i = 0;i<9;i++)
			for (int j=0;j<6;j++)
				report[i][j] = String.valueOf(arr[i][j]);

		report = initialize(report);

		return report;
	}
	/**	This method increments the number of parcels.	*/
	public void incTransactionNum()
	{
		numberOfParcels++;
	}
	/**	This method initializes the 2d array of String for advanced report generation.
		@return report initialized array with header
	*/
	public String[][] initialize (String [][] report)
	{
		report[0][0] = "";report[1][0] ="num";report[2][0] = "Small Flat Parcel"; report[3][0] = "Medium Flat Parcel";
		report[4][0] = "Small Box Parcel";report[5][0] = "Medium Box Parcel";report[6][0] = "Large Box Parcel";
		report[7][0] = "Large Box Parcel";report[8][0] = "XL Box Parcel";
		report[0][1] = "MML";report[0][2] = "LUZ"; report[0][3] = "VIS"; report[0][4] = "MIN";
		report[0][5] = "TOTAL";

		return report;
	}
	/**	This method returns the String formatted sequence of the parcel sequence.
		@return n String formatted parcel sequence of the day
	*/
	public String getSeq()
	{
		String n;
		
		if (seq < 0)
			n = "000";
		else if (seq < 10)
			n = "00" + Integer.toString(seq);
		else if (seq < 100)
			n = "0" + Integer.toString(seq);
		else
			n = Integer.toString(seq);
			
		return n;
    }
	/**	This method returns the admin password of the system.
		@return password String admin password
	*/
	public String getPassword()
	{
		return password;
	}

    private Parcel[] standardParcel;
    private ArrayList <Transaction> Database;
    private int numberOfParcels;
	private int seq;
	private String password;
    private LocalDate currDate;
}
