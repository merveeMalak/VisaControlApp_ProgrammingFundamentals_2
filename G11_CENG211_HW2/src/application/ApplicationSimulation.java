package application;

import java.util.ArrayList;

import applicant.Applicant;
import file_io.FileIO;

public class ApplicationSimulation {
	
	public ApplicationSimulation() {}
	
	
	//iterates over the applicant ArrayList, calls showMessage() for every item
	public void applicationSimulation() {
		
		FileIO file = new FileIO();
		ArrayList<Applicant> applicantArrayList = file.getApplicantArrayList();
		
		for (int i = 0; i<applicantArrayList.size();i++) {
			
			Applicant applicant = applicantArrayList.get(i);
			String type = Integer.toString(applicantArrayList.get(i).getApplicantId()).substring(0,2);
			int type_int = Integer.parseInt(type);
			Application application = new Tourist(applicant);

			switch(type_int){
				case 11:
					application = new Tourist(applicant);
					break;
				case 23:
					application = new Worker(applicant);
					break;
				case 25:
					application = new Educational(applicant);
					break;
				case 30:
					application = new Immigrant(applicant);
					break;
			}
			application.showMessage();

		}
	}
	}

