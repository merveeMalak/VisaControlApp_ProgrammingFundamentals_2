package application;

import applicant.Applicant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Application implements IApplication {
	Applicant applicant;
	
	//default constructor
	public Application(){}
	//copy constructor
	public Application(Applicant applicant){
		this.applicant = new Applicant(applicant);
	}
	//getter
	public Applicant getApplicant() {
		return applicant;
	}
	//tests whether a string consists of numbers or not
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
	//Passport
	
	//checks whether the passport number is valid or not
	public int passportNumberValidator() {

		String passportNumber = applicant.getPassport().getPassportNumber();
		String lastThreeDigits = passportNumber.substring(7, 10);
		
		if (passportNumber.length() == 10) {
			if (passportNumber.substring(0, 1).equals("P") && isInteger(lastThreeDigits)) {
				return 0;
			}
			return 2;
		}

		return 2;


	}
	
	//checks whether the expiration date is valid or not
	public int expirationDateValidator() {

		LocalDate expirationDate = LocalDate.parse(applicant.getPassport().getExpirationDate());
		LocalDate presentDate = LocalDate.now();
		
		long monthsTillExpiration = ChronoUnit.MONTHS.between(presentDate,expirationDate);
		
		if (monthsTillExpiration>=6) {
			return 0;
		}
		return 3; // 3. rejection reason, 0 is success

		
	}

	public int checkPassport(){
		// if there exists a passport, calls passportNumberValidator() && expirationDateValidator()
		if (applicant.getPassport().getPassportNumber().equals("")
				&& applicant.getPassport().getApplicantId() == 0
				&& applicant.getPassport().getExpirationDate().equals("")){
			return  1; //1. reject reason
		}
		if  (passportNumberValidator() !=0){
			return passportNumberValidator();
		}
		if (expirationDateValidator() != 0){
			return expirationDateValidator();
		}
		return 0;
	}
	
	//Photo
	
	//checks whether the photo is square
	public int isSquare() {

		String photoInfo = applicant.getPhoto().getResolution().toLowerCase();
		String[] photoResolution = photoInfo.split("x");
		if ( ! (photoResolution[0].equals(photoResolution[1])) ){
			return 5; //5. reject reason
		}
		return 0;

	}
	
	//checks the resolution of the photo is in interval 600,1200
	public int checkResolution(){

		String photoInfo = applicant.getPhoto().getResolution().toLowerCase();
		String[] photoResolution = photoInfo.split("x");
		if ((Integer.parseInt(photoResolution[0]) < 600) && (Integer.parseInt(photoResolution[1])>1200)) {
			return 5;
		}
		return 0;
	}
	
	//checks whether the position is correct
	public int checkPosition() {

		String position = applicant.getPhoto().getPosition();
		if (!(position.toLowerCase().equals("neutral face") || position.toLowerCase().equals("neutral smile"))) {
			return 6;
		}
		return 0;
	}
	
	//checks whether the photo is valid
	public int checkPhoto(){
		// if there exists a photo, calls isSquare() &&  checkResolution() && checkPosition()

		if (applicant.getPhoto().getApplicantId() == 0 &&
				applicant.getPhoto().getPosition().equals("") &&
				applicant.getPhoto().getResolution().equals("")){
			return 4; //4. reject reason
		}if (!(isSquare() == 0)){
			return isSquare();
		}if (!(checkResolution()== 0)){
			return checkResolution();
		}if (!(checkPosition() == 0)){
			return checkPosition();
		}
		return 0;
	}

	//Document
	
	//checks if there exists a letter of acceptance
	public int checkLetterOfAcceptance() {
		//0 if does not exist
    	int letterOfAcceptance = 0;
    	
    	for (int i=0; i < applicant.getDocuments().size(); i++) {
    		if (applicant.getDocuments().get(i).getDocumentType().equals("LA")) {
    			letterOfAcceptance = 1;
    		}
    	}    	
    	return letterOfAcceptance;
	}
	
	//checks if there exists an invitation letter
	public int checkInvitationLetter() {
		//0 if does not exist
    	int invitationLetter = 0;
    	
    	for (int i=0; i < applicant.getDocuments().size(); i++) {
    		if (applicant.getDocuments().get(i).getDocumentType().equals("IL")) {
    			invitationLetter = 1;
    		}
    	}    	
    	return invitationLetter;
	}
	
	//checks whether the applicant has a green card or not
	public int checkGreenCard() {
		//0 if does not exist
    	int greenCard = 0;
    	
    	for (int i=0; i < applicant.getDocuments().size(); i++) {
    		if (applicant.getDocuments().get(i).getDocumentType().equals("GC")) {
    			greenCard = 1;
    		}
    	}    	
    	return greenCard;
	}
	
	//checks whether the visa is approved, returns reason of rejection if not
	public int visaStatus() {
		//0 is approved, others are reason number
		
		int visaStatus = 0;

		if (checkPassport() != 0){
			return checkPassport();
		}
		if (checkPhoto() != 0){
			return checkPhoto();
		}
		if(checkFinancialStatus() != 0){
			return checkFinancialStatus();
		}
		return visaStatus;
	}
	
	//decides the type of the visa
	public String visaType() {
		
		String applicantId_string = Integer.toString(applicant.getApplicantId());
		int visaType_int = Integer.parseInt(applicantId_string.substring(0, 2));
		String visaType = "Immigrant";
		
		if (visaType_int == 11) {
			visaType = "Tourist";
		}
		else if (visaType_int == 23) {
			visaType = "Worker";
		}
		else if (visaType_int == 25) {
			visaType = "Educational";
		}		
		return visaType;
	}
	
	
	//Financial Status -- must be defined in each class
	public abstract int checkFinancialStatus();
	
	//Visa Duration -- if accepted
	//if it didn't get rejected from other checks,visa duration must be defined in each class
	public abstract int visaDuration();
	
	
	
	//verification
	
	//an array of rejection reasons.
	//index 0 is empty, because all functions returns 0 in case of success
	//all other indexes greater than zero refers to the given reason in the hw document
	private final String[] rejectionReasons = {""," Applicant does not have a passport"," Passport is not valid",
    		" Passport expiration date is not valid"," Applicant does not have a photo"," Resolution of photo is not valid",
    		" Position in the photo is not valid"," Applicant does not have a financial status report",
    		" Applicant does not have a stable financial status"," Applicant does not have a letter of acceptance"};
    
	 //prints out the accurate output
    public void showMessage(){

    		if (visaStatus() == 0) {
        		int visa = visaDuration();
        		String status = "Status: Accepted";
        		String description = "Visa Duration: ";
        		String durationOrReason = "";

        		if (visa == 0) {
        			status = "Status: Rejected";
        			description = "Reason: ";
        			durationOrReason = rejectionReasons[3];
        		}
        		else if (visa == 6) {
        			durationOrReason = "6 months";
        		}
        		else if (visa == 12) {
        			durationOrReason = "1 year";
        		}
        		else if (visa == 24) {
        			durationOrReason = "2 years";
        		}
        		else if (visa == 48) {
        			durationOrReason = "4 years";
        		}
        		else if (visa == 60) {
        			durationOrReason = "5 years";
        		}
        		else {
        			durationOrReason = "Permanent";
        		}

        		System.out.println("Applicant ID: "+getApplicant().getApplicantId() +", "+
    					"Name: "+getApplicant().getApplicantInfo().getApplicantName()+", "+
    					"Visa Type: "+visaType()+", "+status+ ", "+
    					description+durationOrReason);
        	}
        	
        	else {
        		System.out.println("Applicant ID: "+getApplicant().getApplicantId() +", "+
    					"Name: "+getApplicant().getApplicantInfo().getApplicantName()+", "+
    					"Visa Type: "+visaType()+", "+"Status: Rejected, "+
    					"Reason: "+rejectionReasons[visaStatus()]);
        	}
        }
}
