package applicant;

public class FinancialStatus {
	
	private String informationType; //holds information type as a String
	private int applicantId;  //holds applicant id as a int
	private int income;   //holds income as a int
	private int savings;  //holds saving as a int

	//default constructor
	public FinancialStatus() {
		this("F", 0, 0, 0);
	}

	//full argument constructor
	public FinancialStatus(String informationType, int applicantId, int income, int savings){
		this.informationType = informationType;
		this.applicantId = applicantId;
		this.income = income;
		this.savings = savings;
	}

	//copy constructor
	public FinancialStatus(FinancialStatus financialStatus){
		this(financialStatus.informationType, financialStatus.applicantId, financialStatus.income, financialStatus.savings);
	}

	//takes all arguments as a string array
	public FinancialStatus(String[] financialStatus){
		this(financialStatus[0], Integer.parseInt(financialStatus[1]),Integer.parseInt(financialStatus[2]),Integer.parseInt(financialStatus[3]));
	}

	//starts getter methods
	public String getInformationType() {
		return informationType;
	}

	public int getApplicantId(){
		return  this.applicantId;
	}

	public int getIncome() {
		return income;
	}

	public int getSavings() {
		return savings;
	}
	//ends getter methods

	public boolean equals(Object otherObject){
		if (otherObject == null){
			return false;
		}else if(getClass() != otherObject.getClass()){
			return false;
		}else{
			FinancialStatus otherFinancialStatus = (FinancialStatus) otherObject;
			return (this.informationType.equals(otherFinancialStatus.informationType)) &&
					(this.applicantId == otherFinancialStatus.applicantId) &&
					(this.income == otherFinancialStatus.income) &&
					(this.savings == otherFinancialStatus.savings);

		}
	}
	public String toString(){
		return "Applicant Id: " + this.applicantId + " Income: " + this.income + " Savings: " + this.savings;
	}

	public FinancialStatus clone(){
		return new FinancialStatus(this);
	}


}
