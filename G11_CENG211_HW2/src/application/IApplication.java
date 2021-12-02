package application;

public interface IApplication {


    /**
     * checks Photo object is acceptable
     * @return if it is acceptable, returns 0, otherwise returns number of rejection reason
     */
    int checkPhoto();

    /**
     * checks Passport object is acceptable
     * @return if it is acceptable, returns 0, otherwise returns number of rejection reason
     */
    int checkPassport();

    /**
     * checks FinancialStatus object is acceptable
     * @return if it is acceptable, returns 0, otherwise returns number of rejection reason
     */
    int checkFinancialStatus();

    /**
     * checks visa duration is acceptable
     * @return if it is acceptable, returns 0, otherwise returns number of rejection reason
     */
    int visaDuration();

}
