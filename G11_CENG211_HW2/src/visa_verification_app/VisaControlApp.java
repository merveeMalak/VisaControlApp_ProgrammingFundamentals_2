/** This class has the main method of the Visa Verification Application.
 * It calls ApplicationSimulation class to start the app.
 *
 * @author Merve Malak     260201043
 * @author Berfin Yucak    280201096
 */

package visa_verification_app;

import application.ApplicationSimulation;

public class VisaControlApp {
	
	public static void main(String[] args) {
		
		ApplicationSimulation test = new ApplicationSimulation();
		test.applicationSimulation();
	}

}
