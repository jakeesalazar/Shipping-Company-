/** The class Driver is part of the MVC implementation of the Johnny Moves
	program which connects the GUI with the Machine1 class.

 	@author Martin Cu && Jacob Salazar
 	@version 1.0
*/
import java.util.*;
import java.time.format.DateTimeFormatter;

public class Driver {

    public static void main(String[] args)
    {
		GUI gui = new GUI();
    	Machine1 program = new Machine1();
    	Controller c = new Controller(gui, program);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			boolean first_run = true;
		
			public void run()
			{
				if (c.getExit() )
				{
					timer.cancel();
					timer.purge();
				}
				if (first_run)
					first_run = false;
				else
					program.updateDate();

				String formattedDate = program.getCurrDate().format(formatter);
				gui.updateTaDate("Current Date: " + formattedDate);
			}
		};

		timer.scheduleAtFixedRate(task, 0, 25000);
		
    }
}
