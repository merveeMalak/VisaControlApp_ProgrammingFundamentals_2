package application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import applicant.Applicant;

public class Educational extends Application {
    //override financial status document and visa duration methods which are inherited from application class
	
	public Educational(Applicant applicant) {
    	this.applicant = applicant;
    }
    @Override
    public int checkFinancialStatus() {
    	
    	if (!(applicant.getFinancialStatus().getApplicantId() == 0
				&& applicant.getFinancialStatus().getIncome() == 0
				&& applicant.getFinancialStatus().getSavings() == 0)) {
    		
    		int income = applicant.getFinancialStatus().getIncome();
        	int savings = applicant.getFinancialStatus().getSavings();
        	int invitationLetter = super.checkInvitationLetter();
        	int letterOfAcceptance = super.checkLetterOfAcceptance();
        	

        	//returns 0 if conditions are satisfied, otherwise returns rejection reason
        	
        	if (letterOfAcceptance == 1) {
        		
        		switch (invitationLetter) {
        		case 0:
        			if (income>=3000) {
                		return 0;
                	}
                	else if (income>=2000 && income<3000) {
                		if(savings>=3000) {
                			return 0;
                		}
                	}
                	else if (income>=1000 && income<2000) {
                		if (savings>=6000) {
                			return 0;
                		}
                	}
        		case 1:
        			if (income>=1500) {
                		return 0;
                	}
                	else if (income>=1000 && income<1500) {
                		if(savings>=1500) {
                			return 0;
                		}
                	}
                	else if (income>=500 && income<1000) {
                		if (savings>=3000) {
                			return 0;
                		}
                	}
        		}
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
				visaDuration_inMonths = 48;
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

}
