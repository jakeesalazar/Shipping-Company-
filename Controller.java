/** The class Controller is part of the MVC implementation of the Johnny Moves
	program which allows the GUI to interact with the Machine1 class which handles all the
	computations for the system.

 	@author Martin Cu && Jacob Salazar
 	@version 1.0
*/
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.util.ArrayList.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class Controller implements ActionListener, ItemListener
{
	/**	This constructor takes a parameter GUI and Machine1 class info
		order to bridge the user-interface with the system.
		@param g class GUI representing the user-interface
		@param m class Machine1 which handles the computations for the system
	*/
    public Controller(GUI g, Machine1 m)
    {
		ArrayList <Item> list = new ArrayList <Item>();
    	program = m;
    	this.gui = g;
		gui.addListeners(this, true);
		exit = false;
		currPos = 0;
    }
	/**	This method activates whenever an actionevent is triggered from the GUI
		and calls on methods from the GUI and Machine1 class in order to pass information
		@param e ActionEvent event triggered by a listener interface
	*/
    public void actionPerformed(ActionEvent e)
    {
    	if (e.getActionCommand().equals("Proceed") && isValid(currPos) )
		{
			if (currPos == 1)
			{
				if (gui.getBtn(1).isSelected() )
				{
					gui.updateCenterPane(gui.getCardName(currPos + 1));
					currPos++;
				}
				else if (gui.getBtn(2).isSelected() )
				{
					gui.updateCenterPane("Ask Track");
					currPos = 7;
				}
				else if (gui.getBtn(3).isSelected() )
				{
					gui.updateCenterPane("Logout");
					currPos = 9;
				}
			}
			else if (currPos == 2)
			{
				gui.setup(Integer.parseInt(gui.getNumberItem() ) );
				gui.addListeners(this, Integer.parseInt(gui.getNumberItem() ) );
				gui.updateCenterPane(gui.getCardName(currPos + 1));
				currPos++;
			}
			else if (currPos == 3)
			{
				arr = new int[6];
				for (int i = 0;i<6;i++)
				arr[i] = 1;
				list = gui.getItemMeasurements();
				gui.offerParcel(program.checkFit(list));
				gui.addListeners(this, false);
				gui.updateCenterPane(gui.getCardName(currPos + 1));
				currPos++;
			}
			else if (currPos == 4)
			{
				
				p = program.createParcel(gui.getParcelSelected() , list);
				gui.setBreakdown( String.valueOf(program.getParcelRate(p)) ,
				(String.valueOf(program.getInsuranceFee(p, gui.getInsured() )) ),
				(String.valueOf(program.getServiceFee(p, gui.getItemDestinationSelected() ))),
				(String.valueOf(program.computeFee(p, gui.getItemDestinationSelected(), gui.getInsured() ) ) ),
				String.valueOf(list.size() ) );
				gui.updateCenterPane(gui.getCardName(currPos + 1));
				currPos++;
			}
			else if (currPos == 5)
			{
				transact = new Transaction(p, "", gui.getItemDestinationSelected(), 0);
				transact.setStartDate(program.getCurrDate() );
				String date = transact.endDate();

				gui.setReceipt(gui.getRecipient(), gui.getItemDestinationSelected()
				, program.generateTrackInfo(p, gui.getItemDestinationSelected() ), date);
				gui.updateCenterPane(gui.getCardName(currPos + 1));
				currPos++;
			}
			else if (currPos == 7)
			{
				String[] details = new String[4];
				String x = gui.getTrack();
				details = program.trackParcel(x);
				gui.setStatus(details[0],details[1],details[2],details[3]);
				gui.updateCenterPane(gui.getCardName(currPos + 1));
				currPos++;
			}
			else if (currPos == 10)
			{
				gui.resetPane(currPos);
				if (gui.getBtn(4).isSelected() )
				{
					String[][] s = new String[program.getNumberofTransaction()][5];
					s = program.getBasicReportGeneration();
					gui.updateReport(s, true);
					gui.updateCenterPane("Basic Report");
					currPos = 11;
					gui.getBG(3).clearSelection();
				}
				else if (gui.getBtn(5).isSelected() )
				{
					String[][] n = new String[9][6];
					n = program.getAdvReport();
					gui.updateReport(n, false);
					gui.updateCenterPane("Advanced Report");
					currPos = 12;
					gui.getBG(3).clearSelection();
				}
				else if (gui.getBtn(6).isSelected() )
				{
					exit = true;
					gui.dispose();
				}
			}
			else
			{
				gui.updateCenterPane(gui.getCardName(currPos + 1));
				currPos++;
			}
		}
		else if (e.getActionCommand().equals("Back"))
		{
			if (currPos == 3)
				numItemInfo = 0;
			
			gui.resetPane(currPos);
			gui.updateCenterPane(gui.getCardName(currPos - 1));
			currPos--;
		}
		else if (e.getActionCommand().equals("Cancel") || e.getActionCommand().equals("Done"))
		{
			numItemInfo = 0;
			if (currPos > 6)
				gui.resetPane(currPos);
			else if (currPos == 6)
			{
				program.createTransaction(gui.getRecipient(),gui.getItemDestinationSelected(), p, gui.getInsured() );
				for(int i = 0; i < currPos; i++)
					gui.resetPane(i);
			}
			else
			{
				for(int i = 0; i < currPos; i++)
					gui.resetPane(i);
			}
			gui.updateCenterPane("Menu");
			currPos = 1;
		}
    }
	/**	This method checks if all information asked from the GUI is filled depending on
		the param pos.
		@param pos non-negative integer value representing the current position of the shown card
		@return boolean true or false value if all fields are complete
	*/
    public boolean isComplete(int pos)
    {
    	boolean complete = false;
		if (pos == 0 && !gui.getLogin().equals("") )
			complete = true;
		else if (pos == 1 && (gui.getBtn(1).isSelected() || gui.getBtn(2).isSelected() || gui.getBtn(3).isSelected() ) )
			complete = true;
    	else if (pos == 2  && !gui.getNumberItem().equals("") )
		{
    		complete = true;
		}
    	else if (pos == 3 && itemInfoComplete(true) && numItemInfo == Integer.parseInt(gui.getNumberItem() ) )
    		complete = true;

    	else if (pos == 4 && offerInfoComplete() )
    		complete = true;
    	else if (pos == 9 && !gui.getPassword().equals("") )
    		complete = true;
    	else if (pos == 7 && !gui.getTrack().equals("") )
    		complete = true;
		else if (pos == 10 && (gui.getBtn(4).isSelected() || gui.getBtn(5).isSelected() || gui.getBtn(6).isSelected() ) )
				complete = true;
		else if (pos == 5 || pos == 6 || pos == 8 || pos == 11 || pos == 12)
				complete = true;

    	if (complete == false)
    		gui.errorMsg(1);

    	return complete;
    }
	/**	This method checks if all information asked in the transaction details is filled.
		@return boolean true or false value if fields are complete
	*/
    public boolean offerInfoComplete()
    {
    	if (gui.getParcelSelected() == -1)
    		return false;
    	if (gui.getRecipient().equals("") )
    		return false;
    	if (gui.getItemDestinationSelected().equals("<Select Region>") )
    		return false;
    	if (!gui.getBtn(7).isSelected() && !gui.getBtn(8).isSelected())
    		return false;
		
    	return true;
    }
	/**	This method checks if all the measurements asked in the item tab is complete.
		@param opt boolean value representing if the function would check for complete details
		or if the information is an integer
		@return boolean true or false value if all fields are complete
	*/
    public boolean itemInfoComplete(boolean opt)
    {
    	int i;
    	int j;
    	String[] info;

    	for (i = 0; i < gui.getNumItemType(1); i++)
    	{
    		info = new String[3];

    		info = gui.getDocumentInfo(i);
    		for (j = 0; j < 3; j++)
    		{
    			if (opt)
    			{
    				if (info[j].equals("") )
    					return false;
    			}
    			else
    			{
    				if (!isInt(info[j] ) )
    					return false;
    			}
    		}
    	}

    	for (i = 0; i < gui.getNumItemType(2); i++)
    	{
    		info = new String[4];

    		info = gui.getRegularInfo(i);
    		for (j = 0; j < 4; j++)
    		{
    			if (opt)
    			{
    				if (info[j].equals("") )
    					return false;
    			}
    			else
    			{
    				if (!isInt(info[j] ) )
    					return false;
    			}
    		}
    	}

    	for (i = 0; i < gui.getNumItemType(3); i++)
    	{
    		info = new String[3];

    		info = gui.getIrregular(i);
    		for (j = 0; j < 3; j++)
    		{
    			if (opt)
    			{
    				if (info[j].equals("") )
    					return false;
    			}
    			else
    			{
    				if (!isInt(info[j] ) )
    					return false;
    			}
    		}
    	}

    	return true;
    }
	/**	This method checks if a measurement entered is valid or not.
		@return boolean true or false value representing validity
	*/
	public boolean itemInfoValid()
	{
		double L = 0;
		double W = 0; double H = 0;int num = 0;double WT =0;

		list = gui.getItemMeasurements();

		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) instanceof Document)
			{
				L = list.get(i).getLength();
				W = list.get(i).getWidth();
				H = Math.ceil( (double) (((Document)list.get(i)).getnumPages()) / 25);
				WT = list.get(i).getWeight();
			}
			else
			{
				L = list.get(i).getLength();
				W = list.get(i).getWidth();
				H = list.get(i).getHeight();
				WT = list.get(i).getWeight();
			}

			if (L > 20 || W > 20 || H > 20 && L > 0 && W > 0 && H > 0 && WT > 0)
				return false;
		}
		return true;
	}
	/**	This method checks if the information placed in the fields is valid.
		@param pos non-negative integer representing the current card shown
		@return boolean true or false value representing validity
	*/
    public boolean isValid(int pos)
    {
    	boolean valid;

    	if (isComplete(pos) )
    	{
			if (pos == 0 && !gui.getLogin().equals(program.getPassword() ) )
			{
				gui.errorMsg(4);
				gui.resetPane(0);
				return false;
			}
    		else if (pos == 2 && !isInt(gui.getNumberItem() ) || pos == 3 && !itemInfoComplete(false) )
    		{
    			gui.errorMsg(2);
    			return false;
    		}
			else if (pos == 3 && !itemInfoValid() )
			{
				gui.errorMsg(5);
				return false;
			}
			else if (pos == 7 && program.trackParcel(gui.getTrack() ) == null )
			{
				gui.errorMsg(6);
				gui.resetPane(7);
				return false;
			}
			else if (pos == 9 && !gui.getPassword().equals(program.getPassword() ) )
			{
				gui.errorMsg(4);
				gui.resetPane(9);
				return false;
			}
    	}
    	else
			return false;

    	return true;
    }
	/**	This method checks if a String s is an integer or not.
		@param s String to be decoded
		@return boolean true or false value
	*/
    public boolean isInt(String s)
    {
    	try
    	{
    		double i = Double.valueOf(s);
    	}
    	catch (NumberFormatException e)
    	{
    		return false;
    	}

    	return true;
    }
	
	/**	This method checks if a certain combo box i triggered the event.
		@param e ItemEvent triggered by a combo box
		@param i non-negative integer value representing the position of the
		combo box in the array
		@return boolean true or false value
	*/
    public boolean itemStateChanged(ItemEvent e, int i)
    {
    	if (e.getSource() == gui.getCMB(i))
    		return true;
    	else
    		return false;
    }
	/**	This method is triggered whenever an ItemEvent is triggered by a combobox.
		@param e ItemEvent triggered by a combo box
	*/
    public void itemStateChanged(ItemEvent e)
    {
    	int i;

    	if (e.getStateChange() == ItemEvent.SELECTED)
    	{
    		for(i = 0; i < Integer.parseInt(gui.getNumberItem() ); i++)
    		{
    			if(itemStateChanged(e, i) )
    			{
    				gui.resetItemPane(i);
	    			if (e.getItem().toString().equals("Document") )
		    			{
		    				gui.updateItemPane(i, 1);
		    				numItemInfo++;
		    			}
		    		else if (e.getItem().toString().equals("Product (Regular)") )
			    		{
		    				gui.updateItemPane(i, 2);
		    				numItemInfo++;
		    			}
		    		else if (e.getItem().toString().equals("Product (Irregular)") )
		    			{
		    				gui.updateItemPane(i, 3);
		    				numItemInfo++;
		    			}
		    		gui.updateItemPane();
					gui.updateCenterPane(gui.getCardName(currPos) );
    			}
    		}
    	}

    	else if (e.getStateChange() == ItemEvent.DESELECTED)
    	{
    		if (e.getItem().toString().equals("Document") )
    		{
    			gui.setNumItem((gui.getNumItemType(1)) - 1, 1);
	    		numItemInfo--;
    		}
	    	else if (e.getItem().toString().equals("Product (Regular)") )
	    	{
	    		gui.setNumItem((gui.getNumItemType(2)) - 1, 2);
	    		numItemInfo--;
	    	}
	    	else if (e.getItem().toString().equals("Product (Irregular)") )
	    	{
	    		gui.setNumItem((gui.getNumItemType(3)) - 1, 3);
	    		numItemInfo--;
	    	}
    	}

    }
	/**	This method returns the status of exit which represents when the program is closed.
		@return exit boolean true or false
	*/
	public boolean getExit()
	{
		return exit;
	}
	
	private int[] arr;
	private int currPos;
	private boolean exit;
	private int numItemInfo = 0;
    private GUI gui;
    private Machine1 program;
	private Parcel p;
	private ArrayList <Item> list;
	private Transaction transact;
}
