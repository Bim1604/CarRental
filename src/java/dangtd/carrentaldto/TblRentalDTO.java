/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangtd.carrentaldto;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class TblRentalDTO implements Serializable{
    private int billID; 
    private int guestID; 
    private String rentalDate;
    private String returnDate;
    private float priceTotal;

    public TblRentalDTO() {
    }

    public TblRentalDTO(int billID, int guestID, String rentalDate, String returnDate, float priceTotal) {
        this.billID = billID;
        this.guestID = guestID;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.priceTotal = priceTotal;
    }

    /**
     * @return the billID
     */
    public int getBillID() {
        return billID;
    }

    /**
     * @param billID the billID to set
     */
    public void setBillID(int billID) {
        this.billID = billID;
    }

    /**
     * @return the guestID
     */
    public int getGuestID() {
        return guestID;
    }

    /**
     * @param guestID the guestID to set
     */
    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    /**
     * @return the rentalDate
     */
    public String getRentalDate() {
        return rentalDate;
    }

    /**
     * @param rentalDate the rentalDate to set
     */
    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    /**
     * @return the returnDate
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * @return the priceTotal
     */
    public float getPriceTotal() {
        return priceTotal;
    }

    /**
     * @param priceTotal the priceTotal to set
     */
    public void setPriceTotal(float priceTotal) {
        this.priceTotal = priceTotal;
    }

    
}
