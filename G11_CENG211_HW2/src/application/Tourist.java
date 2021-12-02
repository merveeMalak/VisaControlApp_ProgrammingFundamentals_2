package application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import applicant.Applicant;

public class Tourist extends Application {
    
    public Tourist(Applicant applicant) {
    	this.applicant = applicant;
    }
    
    @Override
    public int checkFinancialStatus() {
    	
    	if (!(applicant.getFinancialStatus().getApplicantId() == 0
				&& applicant.getFinancialStatus().getIncome() == 0
				&& applicant.getFinancialStatus().getSavings() == 0)) {
    		
    		int income = applicant.getFinancialStatus().getIncome();
        	int savings = applicant.getFinancialStatus().getIncome();
        	int invitationLetter = super.checkInvitationLetter();
        	
        	//returns 0 if conditions are satisfied, otherwise returns rejection reason
        	
        	switch (invitationLetter) {
        	case 0:
        		if (income>=4000) {
        			return 0;
        	}
        		else if (income>=3000 && income<4000) {
        			if(savings>=6000) {
        				return 0;
        		}
        	}
        		else if (income>=2000 && income<3000) {
        			if (savings>=12000) {
        				return 0;
        		}
        	}
        	case 1:
        		if (income>=2000) {
        			return 0;
        	}
        		else if (income>=1500 && income<2000) {
        			if(savings>=3000) {
        				return 0;
        		}
        	}
        		else if (income>=1000 && income<1500) {
        			if (savings>=6000) {
        				return 0;
        		}
        	}
        	}
        	return 8;
    	}
    	return 7; //7. rejection reason
    }

    @Override
    public int visaDuration() {
    	
    	int invitationLetter = super.checkInvitationLetter();
    	int income = applicant.getFinancialStatus().getIncome();
    	int savings = applicant.getFinancialStatus().getSavings();
    	double DC = 0.0;
    	int calculatedDuration_inMonths = 0;
    	int visaDuration_inMonths = 0;
    	
    	String tempDate = applicant.getPassport().getExpirationDate();
    	
    	if(tempDate != "") {
    		
    		LocalDate expirationDate = LocalDate.parse(tempDate);
    		LocalDate presentDate = LocalDate.now();
    		
    		long monthsTillExpiration = ChronoUnit.MONTHS.between(presentDate,expirationDate);
        	
        	switch (invitationLetter) {
        	case 0:
        		DC = ((income-2000)*6+savings)/12000;
        	case 1:
        		DC = ((income-2000)*6+savings)/6000;
        	}
        	
        	
        	if (DC>=4) {
        		calculatedDuration_inMonths = 5*12;
        	}
        	else if (DC>=2 && DC<4) {
        		calculatedDuration_inMonths = 1*12;   	
        	}
        	else if (DC>=1 && DC<2) {
        		calculatedDuration_inMonths = 6;
        	}
        	
        	
        	if (calculatedDuration_inMonths >= monthsTillExpiration) {
        		visaDuration_inMonths = calculatedDuration_inMonths;
        	}
        	else {
        		switch (calculatedDuration_inMonths) {
        		case 60:
        			if (monthsTillExpiration>=12) {
        				visaDuration_inMonths = 12;
        			}
        			else {
        				visaDuration_inMonths = 6;
        			}
        		case 12:
        			visaDuration_inMonths = 6;
        		}
        	}
    	}
    	else {
    		visaDuration_inMonths = 0;
    	}  	
    	return visaDuration_inMonths;
    }

    //override financial status document and visa duration methods which are inherited from application class
}
