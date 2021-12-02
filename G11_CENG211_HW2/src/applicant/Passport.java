package applicant;

public class Passport {
	
	private String informationType; //holds information type as a String
	private int applicantId;  //holds applicant id as a int
	private String passportNumber;  //holds passport number as a String
	private String expirationDate;  //holds expiration date as a String

	//default constructor
	public Passport(){
		this("S", 0, "", "");
	}

	//copy constructor
	public Passport(Passport passport){
		this(passport.informationType, passport.applicantId, passport.passportNumber, passport.expirationDate);
	}

	//full argument constructor
	public Passport(String informationType, int applicantId, String passportNumber, String expirationDate) {
		this.informationType = informationType;
		this.applicantId = applicantId;
		this.passportNumber = passportNumber;
		this.expirationDate = expirationDate;
	}

	//takes all arguments as a string array
	public Passport(String[] passport){
		this(passport[0], Integer.parseInt(passport[1]), passport[2], passport[3]);
	}

	//starts getter methods
	public int getApplicantId(){
		return  this.applicantId;
	}
	
	public String getInformationType() {
		return informationType;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}
	//ends getter methods


	public boolean equals(Object otherObject){
		if (otherObject == null){
			return false;
		}else if(getClass() != otherObject.getClass()){
			return false;
		}else{
			Passport otherPassport = (Passport) otherObject;
			return (this.informationType.equals(otherPassport.informationType)) &&
					(this.applicantId == otherPassport.applicantId) &&
					(this.passportNumber.equals(otherPassport.passportNumber)) &&
					(this.expirationDate.equals(otherPassport.expirationDate));

		}
	}
	public String toString(){
		return "Applicant Id: " + this.applicantId + " Passport Number: " + this.passportNumber + " Expiration Date: " + this.expirationDate;
	}

	public Passport clone(){
		return new Passport(this);
	}

}
