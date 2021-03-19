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
    private String retalDate;
    private String returnDate;
    private float priceTotal;
    private String email;

    public TblRentalDTO() {
    }

    public TblRentalDTO(int billID, int guestID, String retalDate, String returnDate, float priceTotal, String email) {
        this.billID = billID;
        this.guestID = guestID;
        this.retalDate = retalDate;
        this.returnDate = returnDate;
        this.priceTotal = priceTotal;
        this.email = email;
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
     * @return the retalDate
     */
    public String getRetalDate() {
        return retalDate;
    }

    /**
     * @param retalDate the retalDate to set
     */
    public void setRetalDate(String retalDate) {
        this.retalDate = retalDate;
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

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
