/** The class GUI contains the user interface of Johnny Moves. It contains
	different panels which have buttons and text areas to interact and retrieve
	information from the user. 

 	@author Martin Cu && Jacob Salazar
 	@version 1.0
*/
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.util.ArrayList.*;

public class GUI extends JFrame {
	
	/**	This constructor calls on the extension JFrame and calls init 
		which initalizes the panels and directs the panel to the login panel.	*/
    public GUI()
    {
    	super();
    	init();
		updateCenterPane(LOGIN);
    	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    	setSize(400, 400);
    	setVisible(true);
    }
	
	/**	This method initializes the two subpanels of centerPane mainly mainPane and buttonPane.	*/
	public void init()
	{
		JLabel label;
		centerPane = new JPanel();
		centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.PAGE_AXIS) );
		centerPane.setBackground(Color.pink);

		taCurrDate = new JTextArea(1, 1);
		taCurrDate.setEditable(false);
		taCurrDate.setBackground(Color.pink);

		centerPane.add(taCurrDate);

		JPanel panel;
		JPanel subpanel;
		/* main cards */
		mainPane = new JPanel();
		mainPane.setLayout(new CardLayout() );
		mainPane.setBackground(Color.pink);

		// login
		panel = new JPanel();
		panel.setBackground(Color.pink);
		panel.setLayout(new GridLayout(3,1) );

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout() );
		panel.add(subpanel);

		panel.add(subpanel);

		tfLogin = new JPasswordField(15);
		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0) );
		subpanel.add(new JLabel("Enter Admin Password: ") );
		subpanel.add(tfLogin);

		panel.add(subpanel);

		mainPane.add(panel, LOGIN);

		// menu
		panel = new JPanel();
		panel.setBackground(Color.pink);
		panel.setLayout(new GridLayout(6,1) );

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout() );
		label = new JLabel("Main Menu");
		label.setFont(new Font("Serif", Font.PLAIN, 20) );
		subpanel.add(label);
		panel.add(subpanel);

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout() );
		panel.add(subpanel);

		btnDlvr = new JRadioButton("Delivery");
		btnDlvr.setBackground(Color.pink);
    	btnTrack = new JRadioButton("Track Parcel");
		btnTrack.setBackground(Color.pink);
    	btnExit = new JRadioButton("Admin Menu");
		btnExit.setBackground(Color.pink);

		bg_menu = new ButtonGroup();
    	bg_menu.add(btnDlvr);
    	bg_menu.add(btnTrack);
    	bg_menu.add(btnExit);

		panel.add(btnDlvr);
    	panel.add(btnTrack);
    	panel.add(btnExit);

		mainPane.add(panel, MENU);

		// num item
		panel = new JPanel();
		panel.setBackground(Color.pink);
		panel.setLayout(new GridLayout(3,1) );

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout() );

		panel.add(subpanel);

		tfNumItems = new JTextField(15);
		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0) );
		subpanel.add(new JLabel("Number of Items: ") );
		subpanel.add(tfNumItems);

		panel.add(subpanel);
		mainPane.add(panel, NUM_ITEM);
		// breakdown
		panel = new JPanel();
		panel.setBackground(Color.pink);
		panel.setLayout(new GridLayout(8,1) );

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout() );
		label = new JLabel("Breakdown");
		label.setFont(new Font("Serif", Font.PLAIN, 20) );
		subpanel.add(label);
		panel.add(subpanel);

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout() );
		panel.add(subpanel);

		subpanel = new JPanel();
    	subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0) );
		subpanel.setBackground(Color.pink);
    	subpanel.add(new JLabel("Base Fee: ") );
		panel.add(subpanel);

		subpanel = new JPanel();
    	subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0) );
		subpanel.setBackground(Color.pink);
    	subpanel.add(new JLabel("Parcel Rate: ") );
    	taParcelRate = new JTextArea(" ");
    	taParcelRate.setEditable(false);
		taParcelRate.setBackground(Color.pink);
    	subpanel.add(taParcelRate);
		panel.add(subpanel);

		subpanel = new JPanel();
    	subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0) );
		subpanel.setBackground(Color.pink);
    	subpanel.add(new JLabel("Number of Items: ") );
    	taNumItems = new JTextArea();
    	taNumItems.setEditable(false);
		taNumItems.setBackground(Color.pink);
    	subpanel.add(taNumItems);
    	panel.add(subpanel);

		subpanel = new JPanel();
    	subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0) );
		subpanel.setBackground(Color.pink);
    	subpanel.add(new JLabel("Insurance Fee: ") );
    	taInsurance = new JTextArea();
    	taInsurance.setEditable(false);
		taInsurance.setBackground(Color.pink);
    	subpanel.add(taInsurance);
    	panel.add(subpanel);

		subpanel = new JPanel();
    	subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0) );
		subpanel.setBackground(Color.pink);
    	subpanel.add(new JLabel("Service Fee: ") );
    	taServiceFee = new JTextArea();
    	taServiceFee.setEditable(false);
		taServiceFee.setBackground(Color.pink);
    	subpanel.add(taServiceFee);
    	panel.add(subpanel);

		subpanel = new JPanel();
    	subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0) );
		subpanel.setBackground(Color.pink);
    	subpanel.add(new JLabel("Total: ") );
    	taTotal = new JTextArea();
    	taTotal.setEditable(false);
		taTotal.setBackground(Color.pink);
    	subpanel.add(taTotal);
    	panel.add(subpanel);

		mainPane.add(panel, BREAKDOWN);

		// receipt
		panel = new JPanel();
		panel.setBackground(Color.pink);
		panel.setLayout(new GridLayout(7,1) );

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout() );
		label = new JLabel("Receipt");
		label.setFont(new Font("Serif", Font.PLAIN, 20) );
		subpanel.add(label);
		panel.add(subpanel);

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout() );
		panel.add(subpanel);

		subpanel = new JPanel();
    	subpanel.setLayout(new FlowLayout(FlowLayout.LEFT) );
		subpanel.setBackground(Color.pink);
		subpanel.add(new JLabel("Recipient: ") );
    	taRecipient = new JTextArea();
    	taRecipient.setEditable(false);
		taRecipient.setBackground(Color.pink);
		subpanel.add(taRecipient);
    	panel.add(subpanel);

		subpanel = new JPanel();
    	subpanel.setLayout(new FlowLayout(FlowLayout.LEFT) );
		subpanel.setBackground(Color.pink);
		subpanel.add(new JLabel("Destination: ") );
    	taDestination = new JTextArea();
    	taDestination.setEditable(false);
		taDestination.setBackground(Color.pink);
		subpanel.add(taDestination);
    	panel.add(subpanel);

		subpanel = new JPanel();
    	subpanel.setLayout(new FlowLayout(FlowLayout.LEFT) );
		subpanel.setBackground(Color.pink);
		subpanel.add(new JLabel("Tracking Information: ") );
    	taTrack = new JTextArea();
    	taTrack.setEditable(false);
		taTrack.setBackground(Color.pink);
		subpanel.add(taTrack);
    	panel.add(subpanel);

		subpanel = new JPanel();
    	subpanel.setLayout(new FlowLayout(FlowLayout.LEFT) );
		subpanel.setBackground(Color.pink);
		subpanel.add(new JLabel("Delivery Date: ") );
    	taDeliveryDate = new JTextArea();
    	taDeliveryDate.setEditable(false);
		taDeliveryDate.setBackground(Color.pink);
		subpanel.add(taDeliveryDate);
    	panel.add(subpanel);

		mainPane.add(panel, RECEIPT);

		// ask track
		panel = new JPanel();
		panel.setBackground(Color.pink);
		panel.setLayout(new GridLayout(3,1) );

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout() );

		panel.add(subpanel);

		tfTrack = new JTextField(15);
		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0) );
		subpanel.add(new JLabel("Tracking Number: ") );
		subpanel.add(tfTrack);

		panel.add(subpanel);

		mainPane.add(panel, ASK_TRACK);
		// status
		panel = new JPanel();
		panel.setBackground(Color.pink);
		panel.setLayout(new GridLayout(6,1) );

		subpanel = new JPanel();
    	subpanel.setLayout(new FlowLayout() );
		subpanel.setBackground(Color.pink);
    	label = new JLabel("Status");
    	subpanel.setFont(new Font("Serif", Font.PLAIN, 20) );
    	subpanel.add(label);
    	panel.add(subpanel);

		subpanel = new JPanel();
    	subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0) );
		subpanel.setBackground(Color.pink);
    	subpanel.add(new JLabel("Parcel: "));
    	taTrack1 = new JTextArea();
		taTrack1.setBackground(Color.pink);
    	taTrack1.setEditable(false);
    	subpanel.add(taTrack1);
    	panel.add(subpanel);

		subpanel = new JPanel();
    	subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0) );
		subpanel.setBackground(Color.pink);
    	subpanel.add(new JLabel("Start Date: "));
    	taStartDate = new JTextArea();
    	taStartDate.setEditable(false);
		taStartDate.setBackground(Color.pink);
    	subpanel.add(taStartDate);
    	panel.add(subpanel);

		subpanel = new JPanel();
    	subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0) );
		subpanel.setBackground(Color.pink);
    	subpanel.add(new JLabel("End Date: "));
    	taDueDate = new JTextArea();
    	taDueDate.setEditable(false);
		taDueDate.setBackground(Color.pink);
    	subpanel.add(taDueDate);
    	panel.add(subpanel);

		subpanel = new JPanel();
    	subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0) );
		subpanel.setBackground(Color.pink);
    	subpanel.add(new JLabel("Status: "));
    	taStatus = new JTextArea();
    	taStatus.setEditable(false);
		taStatus.setBackground(Color.pink);
    	subpanel.add(taStatus);
    	panel.add(subpanel);

		mainPane.add(panel, STATUS);
		// logout
		panel = new JPanel();
		panel.setBackground(Color.pink);
		panel.setLayout(new GridLayout(3,1) );

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout() );
		panel.add(subpanel);

		tfPassword = new JPasswordField(15);
		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0) );
		subpanel.add(new JLabel("Enter Admin Password: ") );
		subpanel.add(tfPassword);

		panel.add(subpanel);
		mainPane.add(panel, LOGOUT);
		// admin menu
		panel = new JPanel();
		panel.setBackground(Color.pink);
		panel.setLayout(new GridLayout(6,1) );

		label = new JLabel("Admin Menu");
		label.setFont(new Font("Serif", Font.PLAIN, 20) );
		subpanel = new JPanel();
		subpanel.add(label);
		subpanel.setBackground(Color.pink);
		panel.add(subpanel);

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout() );
		panel.add(subpanel);

		btnBasicReport = new JRadioButton("Basic Report Generation");
		btnBasicReport.setBackground(Color.pink);
    	btnAdvReport = new JRadioButton("Advanced Report Generation");
		btnAdvReport.setBackground(Color.pink);
    	btnClose = new JRadioButton("Close Program");
		btnClose.setBackground(Color.pink);

		bg_report = new ButtonGroup();
    	bg_report.add(btnBasicReport);
    	bg_report.add(btnAdvReport);
    	bg_report.add(btnClose);

		panel.add(btnBasicReport);
    	panel.add(btnAdvReport);
    	panel.add(btnClose);

		mainPane.add(panel, ADMIN_MENU);
		// basic report
		basicReportPane = new JPanel();
		basicReportPane.setBackground(Color.pink);
		basicReportPane.setLayout(new BoxLayout(basicReportPane, BoxLayout.PAGE_AXIS) );

		label = new JLabel("Basic Report");
		label.setFont(new Font("Serif", Font.PLAIN, 20) );
		subpanel.add(label);
		basicReportPane.add(subpanel);
		
		jt1 = new JTable();
		jt1.setEnabled(false);
		scroll1 = new JScrollPane(jt1);
		scroll1.getVerticalScrollBar().setUnitIncrement(12);

		basicReportPane.add(scroll1);

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout() );
		basicReportPane.add(subpanel);
		
		mainPane.add(basicReportPane, BASIC_REPORT);
		// adv report
		advReportPane = new JPanel();
		advReportPane.setBackground(Color.pink);
		advReportPane.setLayout(new BoxLayout(advReportPane, BoxLayout.PAGE_AXIS) );

		label = new JLabel("Advanced Report");
		label.setFont(new Font("Serif", Font.PLAIN, 20) );
		subpanel.add(label);
		advReportPane.add(subpanel);

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout() );
		advReportPane.add(subpanel);
		
		jt2 = new JTable();
		jt2.setEnabled(false);
		scroll2 = new JScrollPane(jt2);
		scroll2.getVerticalScrollBar().setUnitIncrement(12);
		
		advReportPane.add(scroll2);
		
		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout() );
		advReportPane.add(subpanel);

		mainPane.add(advReportPane, ADV_REPORT);
		/* button cards */
		buttonPane = new JPanel();
		buttonPane.setLayout(new CardLayout() );
		buttonPane.setBackground(Color.pink);

		// normal buttons
		panel = new JPanel();
		panel.setLayout(new FlowLayout() );
		panel.setBackground(Color.pink);
		btnBack = new JButton("Back");
		btnProc = new JButton("Proceed");
		btnCancel = new JButton("Cancel");
		panel.add(btnProc);
		panel.add(btnBack);
		panel.add(btnCancel);

		buttonPane.add(panel, BUTTON);

		// done button
		panel = new JPanel();
		panel.setLayout(new FlowLayout() );
		panel.setBackground(Color.pink);
		btnDone = new JButton("Done");
		panel.add(btnDone);

		buttonPane.add(panel, BUTTON_DONE);

		// proc only
		panel = new JPanel();
		panel.setLayout(new FlowLayout() );
		panel.setBackground(Color.pink);
		btnProcOnly = new JButton("Proceed");
		panel.add(btnProcOnly);

		buttonPane.add(panel, BUTTON_PROC);
		
		// proc and cancel
		panel = new JPanel();
		panel.setLayout(new FlowLayout() );
		panel.setBackground(Color.pink);
		btnProc1 = new JButton("Proceed");
		panel.add(btnProc1);
		btnCancel1 = new JButton("Cancel");
		panel.add(btnCancel1);
		
		buttonPane.add(panel, BUTTON_BOOL);

		centerPane.add(mainPane);
		centerPane.add(buttonPane);

		add(centerPane);
	}
	/**	This method removes and re-adds the table included in either the basicReportPane or advReportPane
		with new data.
		@param s a 2d array of String values
		@param opt true or false value indicating which pane is to be modified
	*/
	public void updateReport(String[][] s, boolean opt)
	{
		if (opt)
		{
			basicReportPane.remove(scroll1);
			String[] header = {"Delivery Date", "Tracking Number", "Destination", "Recipient", "Status"};
			
			jt1 = new JTable(s, header);
			scroll1 = new JScrollPane(jt1);
			scroll1.getVerticalScrollBar().setUnitIncrement(12);

			basicReportPane.add(scroll1);
		}
		else
		{
			advReportPane.remove(scroll2);
			String[] header = {"", "Metro Manila", "Provincial Luzon", "Visayas", "Mindanao", "Total"};
			
			jt2 = new JTable(s, header);
			scroll2 = new JScrollPane(jt2);
			scroll2.getVerticalScrollBar().setUnitIncrement(12);
			
			advReportPane.add(scroll2);
		}
	}
	/**	This method returns the String value representing the name of the cards of mainPane
		@param i integer value between 0 and 12 represented by currPos in the controller
		@return String name of the card
	*/
	public String getCardName(int i)
	{
		switch (i)
		{
			case 0: return LOGIN;
			case 1: return MENU;
			case 2: return NUM_ITEM;
			case 3: return ASK_ITEM;
			case 4: return OFFER;
			case 5: return BREAKDOWN;
			case 6: return RECEIPT;
			case 7: return ASK_TRACK;
			case 8: return STATUS;
			case 9: return LOGOUT;
			case 10: return ADMIN_MENU;
			case 11: return BASIC_REPORT;
			case 12: return ADV_REPORT;
			default: return null;
		}
	}
	/**	This method initializes and adds the panel containing the transaction details
		for the parcel.
		@param arr integer array of values representing the available parcels to be offered
	*/
	public void offerParcel(int[] arr)
	{
		JPanel panel;
		JPanel subpanel;
		JLabel label;
		// offer
		panel = new JPanel();
		panel.setBackground(Color.pink);
		panel.setLayout(new GridLayout(7, 1) );

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout() );
		panel.add(subpanel);

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0) );
		tfRecipient = new JTextField(15);
		subpanel.add(new JLabel("Recipient:") );
		subpanel.add(tfRecipient);
		panel.add(subpanel);

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0) );
		subpanel.add(new JLabel("Available Parcel:") );

		String[] strParcels = new String[7];
		int j=1;
		for (int i = 1; i <= 6;i++)
		{
			if (arr[i] != 0){

				if (i == 1)
				{
					strParcels[j] = "Small Flat Parcel";
					j++;
				}
				if (i == 2)
				{
					strParcels[j] = "Medium Flat Parcel";
					j++;
				}
				if (i == 3)
				{
					strParcels[j] = "Small Box Parcel";
					j++;
				}
				if (i == 4)
				{
					strParcels[j] = "Medium Box Parcel";
					j++;
				}
				if (i == 5)
				{
					strParcels[j] = "Large Box Parcel";
					j++;
				}
				if (i == 6)
				{
					strParcels[j] = "XL Box Parcel";
					j++;
				}
			}
		}
		cmbParcels = new JComboBox<> (strParcels);
		subpanel.add(cmbParcels);

		panel.add(subpanel);

		subpanel = new JPanel();
		subpanel.setBackground(Color.pink);
		subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0) );
		String[] strDestination = new String[5];
		strDestination[0] = "<Select Region>";
    	strDestination[1] = "Metro Manila";
    	strDestination[2] = "Provincial Luzon";
    	strDestination[3] = "Visayas";
    	strDestination[4] = "Mindanao";

    	cmbDestination = new JComboBox<> (strDestination);
		subpanel.add(new JLabel("Region:") );
		subpanel.add(cmbDestination);

		panel.add(subpanel);

		subpanel = new JPanel();
    	subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0) );
		subpanel.setBackground(Color.pink);
    	btnYes = new JRadioButton("Yes");
		btnYes.setBackground(Color.pink);
    	btnNo = new JRadioButton("No");
		btnNo.setBackground(Color.pink);

    	insureBG = new ButtonGroup();
    	insureBG.add(btnYes);
    	insureBG.add(btnNo);

    	subpanel.add(new JLabel("Insure: ") );
    	subpanel.add(btnYes);
    	subpanel.add(btnNo);

		panel.add(subpanel);

		mainPane.add(panel, OFFER);
	}
	/**	This method resets the text fields and/or text areas associated with a certain panel.
		@param i integer value between 0 to 12
	*/
	public void resetPane(int i)
	{
		bg_menu.clearSelection();
		if (i == 0)
			tfLogin.setText("");
		else if (i == 2)
			tfNumItems.setText("");
		else if (i == 3)
		{
			numDoc = 0;
			numRegular = 0;
			numIrregular = 0;
		}
		else if (i == 4)
		{
			taRecipient.setText("");
			insureBG.clearSelection();
		}
		else if (i == 6)
		{
			taTrack.setText("");
			taStartDate.setText("");
			taDeliveryDate.setText("");
			taStatus.setText("");
			insureBG.clearSelection();
		}
		else if (i == 7)
			tfTrack.setText("");
		else if (i >= 9)
			tfPassword.setText("");
		
	}
	/**	This method updates the cards shown by mainPane and buttonPane.
		@param name String name of the card to be shown
	*/
	public void updateCenterPane(String name)
	{
		CardLayout cards = (CardLayout)mainPane.getLayout();
		CardLayout button_cards = (CardLayout)buttonPane.getLayout();

		cards.show(mainPane, name);

		if (name.equals(LOGIN) || name.equals(MENU) )
			button_cards.show(buttonPane, BUTTON_PROC);
		else if (name.equals(RECEIPT) || name.equals(STATUS) || name.equals(BASIC_REPORT) || name.equals(ADV_REPORT) )
			button_cards.show(buttonPane, BUTTON_DONE);
		else if (name.equals(NUM_ITEM) || name.equals(ASK_TRACK) || name.equals(LOGOUT) || name.equals(ADMIN_MENU) )
			button_cards.show(buttonPane, BUTTON_BOOL);
		else
			button_cards.show(buttonPane, BUTTON);
	}
	/**	This method adds the listener of the buttons and combo boxes.
		@param listener tagging interface of all event listener interfaces
		@param opt true or false value indicating which buttons/combo box listeners
		are to be added
	*/
	public void addListeners(EventListener listener, boolean opt)
	{
		if (opt)
		{
			btnBack.addActionListener ((ActionListener) listener);
			btnCancel.addActionListener ((ActionListener) listener);
			btnProc.addActionListener ((ActionListener) listener);
			btnExit.addActionListener ((ActionListener) listener);
			btnTrack.addActionListener ((ActionListener) listener);
			btnDone.addActionListener ((ActionListener) listener);
			btnProcOnly.addActionListener ((ActionListener) listener);
			btnProc1.addActionListener ((ActionListener) listener);
			btnCancel1.addActionListener ((ActionListener) listener);
		}
		else
		{
		cmbParcels.addItemListener ((ItemListener) listener);
		cmbDestination.addItemListener ((ItemListener) listener);
		}
	}
	/**	This method adds the listener of the combo box of items.
		@param listener tagging interface of all event listener interfaces
		@param i integer representing the number of item combo boxes
	*/
	public void addListeners(EventListener listener, int i)
	{
		int j;

		for (j = 0; j < i; j++)
			cmbItems[j].addItemListener((ItemListener) listener);
	}
	/**	This method returns the String value of the selected destination combo box.
		@return String destination associated with the combo box
	*/
	public String getItemDestinationSelected()
	{
		return String.valueOf(cmbDestination.getSelectedItem());
	}
	/**	This method returns an integer representing the type of parcel selected
		from the combo box.
		@return integer between -1 to 5
	*/
	public int getParcelSelected ()
	{
		String x = "";

		x =	String.valueOf(cmbParcels.getSelectedItem());

		if (x.equalsIgnoreCase("Small Flat Parcel"))
			return 0;
		else if (x.equalsIgnoreCase("Medium Flat Parcel"))
			return 1;
		else if (x.equalsIgnoreCase("Small Box Parcel"))
			return 2;
		else if (x.equalsIgnoreCase("Medium Box Parcel"))
			return 3;
		else if (x.equalsIgnoreCase("Large Box Parcel"))
			return 4;
		else if (x.equalsIgnoreCase("XL Box Parcel"))
			return 5;
		else
			return -1;
	}
	/** This method returns the String value of the selected item combo box.
		@return String item type associated with the combo box
	*/
    public String getItemTypeSelected(int i)
    {
    	return (String) cmbItems[i].getSelectedItem();
    }
	/**	This method returns a JPanel containing item measurements
		neeeded according to the type of item selected.
		@param index non-negative integer of the panel element to be modified
		@param item integer value between 1 to 3 representing the item type
	*/
    public JPanel itemData(int index, int item)
    {
    	int i;

    	if (item == 1)
    	{
    		itemInfo[index].setLayout(new GridLayout(1, 0) );
			itemInfo[index].setBackground(Color.pink);

    		for(i = 0; i < 3; i++)
    		{
    			JPanel subpanel = new JPanel();
    			subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0) );
				subpanel.setBackground(Color.pink);

    			switch (i)
    			{
    				case 0:
    					subpanel.add(new JLabel("Length: ") );
    					tfDocument[numDoc][i] = new JTextField(15);
    					subpanel.add(tfDocument[numDoc][i]);
    					break;
    				case 1:
    					subpanel.add(new JLabel("Width: ") );
    					tfDocument[numDoc][i] = new JTextField(15);
    					subpanel.add(tfDocument[numDoc][i]);
    					break;
    				case 2:
    					subpanel.add(new JLabel("Number of Pages: ") );
    					tfDocument[numDoc][i] = new JTextField(15);
    					subpanel.add(tfDocument[numDoc][i]);
    					break;
    			}
    			itemInfo[index].add(subpanel);
    		}
    		numDoc++;
    	}

    	else if (item == 2)
    	{
    		itemInfo[index].setLayout(new GridLayout(1, 0) );

    		for(i = 0; i < 4; i++)
    		{
    			JPanel subpanel = new JPanel();
    			subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0) );
				subpanel.setBackground(Color.pink);

    			switch (i)
    			{
    				case 0:
    					subpanel.add(new JLabel("Length: ") );
    					tfRegular[numRegular][i] = new JTextField(15);
    					subpanel.add(tfRegular[numRegular][i]);
    					break;
    				case 1:
    					subpanel.add(new JLabel("Width: ") );
    					tfRegular[numRegular][i] = new JTextField(15);
    					subpanel.add(tfRegular[numRegular][i]);
    					break;
    				case 2:
    					subpanel.add(new JLabel("Height: ") );
    					tfRegular[numRegular][i] = new JTextField(15);
    					subpanel.add(tfRegular[numRegular][i]);
    					break;
    				case 3:
    					subpanel.add(new JLabel("Weight: ") );
    					tfRegular[numRegular][i] = new JTextField(15);
    					subpanel.add(tfRegular[numRegular][i]);
    					break;
    			}
    			itemInfo[index].add(subpanel);
    		}
    		numRegular++;
    	}

    	else if (item == 3)
    	{
    		itemInfo[index].setLayout(new GridLayout(1, 0) );

    		for(i = 0; i < 4; i++)
    		{
    			JPanel subpanel = new JPanel();
    			subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0) );
				subpanel.setBackground(Color.pink);

    			switch (i)
    			{
    				case 0:
    					subpanel.add(new JLabel("Length: ") );
    					tfIrregular[numIrregular][i] = new JTextField(15);
    					subpanel.add(tfIrregular[numIrregular][i]);
    					break;
    				case 1:
    					subpanel.add(new JLabel("Width: ") );
    					tfIrregular[numIrregular][i] = new JTextField(15);
    					subpanel.add(tfIrregular[numIrregular][i]);
    					break;
    				case 2:
    					subpanel.add(new JLabel("Height: ") );
    					tfIrregular[numIrregular][i] = new JTextField(15);
    					subpanel.add(tfIrregular[numIrregular][i]);
    					break;
					case 3:
						subpanel.add(new JLabel("Weight: ") );
    					tfIrregular[numIrregular][i] = new JTextField(15);
    					subpanel.add(tfIrregular[numIrregular][i]);
    					break;
    			}
    			itemInfo[index].add(subpanel);
    		}
    		numIrregular++;
    	}
		return itemInfo[index];
    }
	/**	This method modifies the itemPane to contain n number of item tabs.
		@param num positive integer value number of item tabs
	*/
	public void setup(int num)
    {
		// ask item (skip)
		itemPane = new JPanel();
		itemPane.setBackground(Color.pink);
		itemPane.setLayout(new BoxLayout(itemPane, BoxLayout.Y_AXIS) );

		tempItemPane = new JPanel();
		tempItemPane.setBackground(Color.pink);
		tempItemPane.setLayout(new BoxLayout(tempItemPane, BoxLayout.PAGE_AXIS) );

    	// initialize tf for items
    	tfDocument = new JTextField[num][3];
    	tfRegular = new JTextField[num][4];
    	tfIrregular = new JTextField[num][4];
    	int i;

    	String[] strItems = new String[4];

    	// dropbox
    	strItems[0] = "<Select Item Type>";
    	strItems[1] = "Document";
    	strItems[2] = "Product (Regular)";
    	strItems[3] = "Product (Irregular)";


    	cmbItems = new JComboBox[num];

    	for (i = 0; i < num; i++)
    	{
    		cmbItems[i] = new JComboBox<String> (strItems);
    	}

    	// item info
    	itemPanel = new JPanel[num + 1];
    	itemInfo = new JPanel[num];
    	for (i = 0; i < num; i++)
    	{
    		itemPanel[i] = new JPanel();
			itemPanel[i].setBackground(Color.pink);
    		itemInfo[i] = new JPanel();
	    	itemPanel[i].setBorder(BorderFactory.createLineBorder(Color.black) );
	    	itemPanel[i].setLayout(new GridLayout(3, 1, 0, 0) );
	    	JPanel subpanel = new JPanel();
	    	subpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0) );
	    	subpanel.add(new JLabel("Item " + (i + 1) ) );
			subpanel.setBackground(Color.pink);
	    	itemPanel[i].add(subpanel);
	    	itemPanel[i].add(cmbItems[i]);
	    	itemPane.add(itemPanel[i]);
    	}

		//add scrollbar
		JScrollPane taScroll = new JScrollPane(itemPane,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		taScroll.getVerticalScrollBar().setUnitIncrement(12);
		tempItemPane.add(taScroll);

		mainPane.add(tempItemPane, ASK_ITEM);
    }
	/**	This method retrieves the information placed in the item text fields and creates
		an item object and adds it into an ArrayList which is returned.
		@return list ArrayList of <Item> objects
	*/
	public ArrayList <Item> getItemMeasurements()
	{
		double L = 0;
		double W = 0; double H = 0;int num = 0;double WT =0;
		ArrayList <Item> list = new ArrayList <Item>();

		for (int i = 0;i <numDoc;i++)
		{
			for(int j = 0;j<3;j++)
			{
				if (j==0)
				  L = Double.parseDouble(tfDocument[i][j].getText());
				if (j==1)
						W = Double.parseDouble(tfDocument[i][j].getText());
				if (j==2)
					  num = Integer.parseInt(tfDocument[i][j].getText());
			}
				list.add(new Document(L,W,num));
		}

		for (int i = 0;i <numRegular;i++)
		{
			for(int j = 0;j<4;j++){

				if (j==0)
				  L = Double.parseDouble(tfRegular[i][j].getText());
				if (j==1)
						W = Double.parseDouble(tfRegular[i][j].getText());
				if (j==2)
					  H = Double.parseDouble(tfRegular[i][j].getText());
				if(j==3)
						WT = Double.parseDouble(tfRegular[i][j].getText());
			}
				list.add(new Regular(L,W,H,WT));
		}

		for (int i = 0;i <numIrregular;i++)
		{
			for(int j = 0;j<3;j++){

				if (j==0)
				  L = Double.parseDouble(tfIrregular[i][j].getText());
				if (j==1)
						W = Double.parseDouble(tfIrregular[i][j].getText());
				if (j==2)
					  H = Double.parseDouble(tfIrregular[i][j].getText());
				if (j ==3)
				WT = Double.parseDouble(tfIrregular[i][j].getText());

			}
				list.add(new Irregular(L,W,H,WT));
		}

		return list;

	}
	/**	This method updates the panel containing the item tabs.	*/
    public void updateItemPane()
    {
    	int j;

    	for(j = 0; j < Integer.parseInt(getNumberItem() ); j++)
    	{
    		itemPane.remove(itemPanel[j] );
    	}
    	for(j = 0; j < Integer.parseInt(getNumberItem() ); j++)
    	{
    		itemPane.add(itemPanel[j] );
    	}

		mainPane.add(itemPane, ASK_ITEM);
    }
	/**	This method removes all components inside an itemInfo array.
		@param i non-negative integer representing the position of the
		panel to be reset in the array
	*/
	public void resetItemPane(int i)
    {
    	itemInfo[i].removeAll();
    }
	/**	This method updates the panel of a single item tab.
		@param i non-negative integer representing the position of the
		panel to be updated in the array
		@param type integer value between 1 to 3 representing the item type selected
	*/
    public void updateItemPane(int i, int type)
    {
    	itemPanel[i].add(itemData(i, type) );
    }
	/** This method retrieves the information placed in the recipient text field.
		@return String information of the recipient text field
	*/
    public String getRecipient()
    {
    	return tfRecipient.getText();
    }
	/** This method retrieves the information placed in the login text field.
		@return String information of the login text field
	*/
	public String getLogin()
    {
    	return String.valueOf(tfLogin.getPassword() );
    }
	/** This method retrieves the information of the insurance button group.
		@return boolean value associated with the selected insurance button group
	*/
	public boolean getInsured()
	{
		if (btnYes.isSelected() )
			return true;
		else
			return false;
	}
	/** This method retrieves the information placed in the password text field.
		@return String information of the password text field
	*/
    public String getPassword()
    {
    	return String.valueOf(tfPassword.getPassword() );
    }
	/** This method retrieves the information placed in the numitem text field.
		@return String information of the numitem text field
	*/
    public String getNumberItem()
    {
    	return (tfNumItems.getText() );
    }
	/** This method retrieves the information of the item combo box selected.
		@param i non-negative integer value of the selected item combo box
		@return String information of the selected item combo box
	*/
    public JComboBox<String> getCMB(int i)
    {
    	return cmbItems[i];
    }
	/**	This method returns the number of a specific type of item depending on
		the type parameter.
		@param type integer value between 1 to 3 representing item type
		@return num number of a specific item type
	*/
    public int getNumItemType(int type)
    {
    	int num = 0;
    	if (type == 1)
    		num = numDoc;
    	else if (type == 2)
    		num = numRegular;
    	else if (type == 3)
    		num = numIrregular;

    	return num;
    }
	/**	This method assigns the number of a specific item type.
		@param i non-negative integer value to assigns
		@param type integer value between 1 to 3 representing item type
	*/
    public void setNumItem(int i, int type)
    {
    	if (type == 1)
    		numDoc = i;
    	else if (type == 2)
    		numRegular = i;
    	else if (type == 3)
    		numIrregular = i;
    }
	/** This method opens a JOptionPane containing a warning message.
		@param i non-negative integer value representing the type of error message
		inccurred
	*/
    public void errorMsg(int i)
    {
    	JFrame frame = new JFrame();
    	frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		JFrame frame1 = new JFrame();
		frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    	if (i == 0)
    	{
    		JOptionPane.showMessageDialog(frame, "Invalid Password!", "Warning", JOptionPane.ERROR_MESSAGE);
    	}
    	else if (i == 1)
    		JOptionPane.showMessageDialog(frame, "Incomplete information!", "Error", JOptionPane.INFORMATION_MESSAGE);
    	else if (i == 2)
    		JOptionPane.showMessageDialog(frame, "Invalid information please input a double value!", "Error", JOptionPane.INFORMATION_MESSAGE);
    	else if (i == 3)
    		JOptionPane.showMessageDialog(frame, "No such tracking information!!", "Error", JOptionPane.INFORMATION_MESSAGE);
		else if (i == 4)
			JOptionPane.showMessageDialog(frame, "Invalid Password!", "Warning", JOptionPane.ERROR_MESSAGE);
		else if (i == 5)
			JOptionPane.showMessageDialog(frame1, "An item has exceeded the measurement of the largest box!", "Error", JOptionPane.INFORMATION_MESSAGE);
		else if (i == 6)
			JOptionPane.showMessageDialog(frame1, "No such tracking information!", "Error", JOptionPane.INFORMATION_MESSAGE);
		else if (i == 7)
			JOptionPane.showMessageDialog(frame, "Please choose from the menu!", "Warning", JOptionPane.ERROR_MESSAGE);
		else if (i == 8)
			JOptionPane.showMessageDialog(frame, "Items do not fit in any parcel!", "Error", JOptionPane.INFORMATION_MESSAGE);

    	validate();

    	frame.dispose();
		frame1.dispose();
    }
	/** This method updates the information in the text area representing current date.
		@param s String representing current date
	*/
	public void updateTaDate(String s)
	{
		taCurrDate.setText(s);
	}
	/**	This method returns the information in track text field.
		@return String value of tfTrack
	*/
    public String getTrack()
    {
    	return tfTrack.getText();
    }
	/**	This method assigns the values to be shown in the breakdown panel.
		@param parcelRate base parcel rate
		@param insurance parcel insurance rate
		@param sF parcel service rate
		@param total parcel total rate
		@param n number of items inside the parcel
	*/
    public void setBreakdown(String parcelRate, String insurance, String sF, String total,String n)
    {
    	taParcelRate.setText(parcelRate);
		taNumItems.setText(n);
    	taInsurance.setText(insurance);
    	taServiceFee.setText(sF);
    	taTotal.setText(total);
    }

	/**	This method assigns the values to be shown in the end of transaction.
		@param name parcel recipient
		@param dest parcel destination
		@param trackInfo parcel tracking number
		@param date parcel delivery date
	*/
	public void setReceipt(String name, String dest, String trackinfo, String date)
    {
		taDestination.setText(dest);
		taRecipient.setText(name);
		taTrack.setText(trackinfo);
		taDeliveryDate.setText(date);
    }
	/**	This method assigns the values to be shown when tracking a parcel.
		@param size parcel type
		@param start parcel delivery start date
		@param end parcel delivery date
		@param status parcel shipping status
	*/
	public void setStatus (String size, String start, String end, String status){

		taTrack1.setText(size);
		taStartDate.setText(start);
		taDueDate.setText(end);
		taStatus.setText(status);
	}
	/**	This method assigns the information in the track text field.
		@param s String to assigns
	*/
    public void setTrackInfo(String s)
    {
    	taTrack.setText(s);
    }
	/**	This method returns a specific JRadioButton according to the parameter.
		@param i integer value between 1 to 8 representing different buttons
		@return JRadioButton a jradiobutton according to param i
	*/
	public JRadioButton getBtn(int i)
	{
		switch (i)
		{
			case 1: return btnDlvr;
			case 2: return btnTrack;
			case 3: return btnExit;
			case 4: return btnBasicReport;
			case 5: return btnAdvReport;
			case 6: return btnClose;
			case 7: return btnYes;
			case 8: return btnNo;
		}
		return null;
	}
	/**	This method returns a specific ButtonGroup according to the parameter i.
		@param i integer value between 1 to 3 representing the different button groups
		@return ButtonGroup a button group of JRadioButtons
	*/
	public ButtonGroup getBG(int i)
	{
		switch (i)
		{
			case 1: return bg_menu;
			case 2: return insureBG;
			case 3: return bg_report;
		}
		return null;
	}
	/**	This method returns a String array of document measurements.
		@param i non-negative integer value representing the position of the
		textfield in the array
		@return info 2d array of item measurements
	*/
	public String[] getDocumentInfo(int i)
    {
    	String[] info = new String[3];

    	info[0] = tfDocument[i][0].getText();
    	info[1] = tfDocument[i][1].getText();
    	info[2] = tfDocument[i][2].getText();

    	return info;
    }
	/**	This method returns a String array of regular product measurements.
		@param i non-negative integer value representing the position of the
		textfield in the array
		@return info 2d array of item measurements
	*/
    public String[] getRegularInfo(int i)
    {
    	String[] info = new String[4];

    	info[0] = tfRegular[i][0].getText();
    	info[1] = tfRegular[i][1].getText();
    	info[2] = tfRegular[i][2].getText();
    	info[3] = tfRegular[i][3].getText();

    	return info;
    }
	/**	This method returns a String array of irregular product measurements.
		@param i non-negative integer value representing the position of the
		textfield in the array
		@return info 2d arra
		y of item measurements
	*/
    public String[] getIrregular(int i)
    {
    	String[] info = new String[3];

    	info[0] = tfIrregular[i][0].getText();
    	info[1] = tfIrregular[i][1].getText();
    	info[2] = tfIrregular[i][2].getText();

    	return info;
    }
	
	private int numDoc = 0;
	private int numRegular = 0;
	private int numIrregular = 0;

	private ButtonGroup bg_menu;
	private ButtonGroup insureBG;
	private ButtonGroup bg_report;

	private JTextArea taParcelRate;
	private JTextArea taInsurance;
	private JTextArea taNumItems;
	private JTextArea taRecipient;
	private JTextArea taDestination;
	private JTextArea taDeliveryDate;
	private JTextArea taServiceFee;
	private JTextArea taTotal;
	private JTextArea taTrack;
	private JTextArea taTrack1;
	private JTextArea taStartDate;
	private JTextArea taDueDate;
	private JTextArea taStatus;
	private JTextArea taCurrDate;

	private JTextField tfNumItems;
	private JTextField tfRecipient;
	private JTextField tfTrack;
	private JTextField[][] tfDocument;
	private JTextField[][] tfRegular;
	private JTextField[][] tfIrregular;

	private JPasswordField tfPassword;
	private JPasswordField tfLogin;

	private JComboBox<String>[] cmbItems;
	private JComboBox<String> cmbParcels;
	private JComboBox<String> cmbDestination;
	
	private JTable jt1;
	private JTable jt2;
	
	private JScrollPane scroll1;
	private JScrollPane scroll2;

	private final String LOGIN = "Login";
	private final String MENU = "Menu";
	private final String NUM_ITEM = "Num Item";
	private final String ASK_ITEM = "Ask Item";
	private final String OFFER = "Offer";
	private final String BREAKDOWN = "Breakdown";
	private final String RECEIPT = "Receipt";
	private final String ASK_TRACK = "Ask Track";
	private final String STATUS = "Status";
	private final String LOGOUT = "Logout";
	private final String ADMIN_MENU = "Admin Menu";
	private final String BASIC_REPORT = "Basic Report";
	private final String ADV_REPORT = "Advanced Report";

	private final String BUTTON = "Button";
	private final String BUTTON_DONE = "Button Done";
	private final String BUTTON_PROC = "Button Proc";
	private final String BUTTON_BOOL = "Button Bool";

	private JPanel centerPane;
	private JPanel mainPane;
	private JPanel buttonPane;
	private JPanel itemPane;
	private JPanel tempItemPane;
	private JPanel basicReportPane;
	private JPanel advReportPane;
	private JPanel[] itemPanel;
	private JPanel[] itemInfo;

	private JButton btnDone;
	private JButton btnProc;
	private JButton btnBack;
	private JButton btnCancel;
	private JButton btnProcOnly;
	private JButton btnProc1;
	private JButton btnCancel1;

	private JRadioButton btnDlvr;
	private JRadioButton btnTrack;
	private JRadioButton btnExit;
	private JRadioButton btnYes;
	private JRadioButton btnNo;
	private JRadioButton btnBasicReport;
	private JRadioButton btnAdvReport;
	private JRadioButton btnClose;
}
