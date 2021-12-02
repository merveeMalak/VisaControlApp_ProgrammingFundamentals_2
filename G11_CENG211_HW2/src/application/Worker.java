package application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import applicant.Applicant;

public class Worker extends Application {
	
	public Worker(Applicant applicant) {
    	this.applicant = applicant;
    }
    @Override
    public int checkFinancialStatus() {
    	
    	if (!(applicant.getFinancialStatus().getApplicantId() == 0
				&& applicant.getFinancialStatus().getIncome() == 0
				&& applicant.getFinancialStatus().getSavings() == 0)) {
    		
    		int savings = applicant.getFinancialStatus().getIncome();
        	int letterOfAcceptance = super.checkLetterOfAcceptance();
        	
        	//returns 0 if conditions are satisfied, otherwise returns rejection reason
        	   	
        	if (letterOfAcceptance == 1 && savings >= 2000) {
        		return 0;
        	}
        	return 8;
    	}
    	return 7; //7. rejection reason
    }


    @Override
    public int visaDuration() {
    	
    	int durationLA_inMonths = 0;
    	
    	for (int i = 0; i<applicant.getDocuments().size();i++) {
    		if (applicant.getDocuments().get(i).getDocumentType().equals("LA")) {
    			durationLA_inMonths = applicant.getDocuments().get(i).getDurationInMonths();
    		}
    	}
    	
    	LocalDate expirationDate = LocalDate.parse(applicant.getPassport().getExpirationDate());
		LocalDate presentDate = LocalDate.now();
		
		long monthsTillExpiration = ChronoUnit.MONTHS.between(presentDate,expirationDate);
		
		int visaDuration_inMonths = 0;
		
		if (durationLA_inMonths>=0 && durationLA_inMonths<12) {
			if (monthsTillExpiration>=durationLA_inMonths) {
				visaDuration_inMonths = 12;
			}
			else {
				visaDuration_inMonths = 0;
			}
		}
		else if(durationLA_inMonths>=12 && durationLA_inMonths<24) {
			if (monthsTillExpiration>=durationLA_inMonths) {
				visaDuration_inMonths = 24;
			}
			else if (monthsTillExpiration>=12) {
				visaDuration_inMonths = 12;
			}
			else {
				visaDuration_inMonths = 0;
			}
		}
		else {
			if (monthsTillExpiration>=durationLA_inMonths) {
				visaDuration_inMonths = 60;
			}
			else if (monthsTillExpiration>=24) {
				visaDuration_inMonths = 24;
			}
			else if (monthsTillExpiration>=12) {
				visaDuration_inMonths = 12;
			}
			else {
				visaDuration_inMonths = 0;
			}
		}
		return visaDuration_inMonths;
    }

    //override financial status document and visa duration methods which are inherited from application class

}
