package application;

import applicant.Applicant;

public class Immigrant extends Application {

    //override financial status document and visa duration methods which are inherited from application class

	public Immigrant(Applicant applicant) {
    	this.applicant = applicant;
    }
	
    @Override
    public int checkFinancialStatus() {
    	
    	if (!(applicant.getFinancialStatus().getApplicantId() == 0
				&& applicant.getFinancialStatus().getIncome() == 0
				&& applicant.getFinancialStatus().getSavings() == 0)) {
    		
    		int savings = applicant.getFinancialStatus().getSavings();
        	int greenCard = super.checkGreenCard();
        	int invitationLetter = super.checkInvitationLetter();
        	
        	//returns 0 if conditions are satisfied, otherwise returns rejection reason
        	
        	switch (greenCard) {
        	case 0:
        		if (invitationLetter == 1 && savings >= 25000) {
        			return 0;
        		}
        		else if (savings >= 50000) {
        			return 0;
        		}
        	case 1:
        		if (invitationLetter == 1 && savings >= 2000) {
        			return 0;
        		}
        		else if (savings >= 4000) {
        			return 0;
        		}
        	}
        	return 8;
    	}
    	return 7; //7. rejection reason
    }

    @Override
    public int visaDuration() {
        return 100; //100 represents permanent
    }



}
