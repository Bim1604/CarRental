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
public class TblGuestDTO implements Serializable{
    private String guestID;
    private String guestName;
    private String phone;
    private String address;

    public TblGuestDTO() {
    }

    public TblGuestDTO(String guestID, String guestName, String phone, String address) {
        this.guestID = guestID;
        this.guestName = guestName;
        this.phone = phone;
        this.address = address;
    }

    /**
     * @return the guestID
     */
    public String getGuestID() {
        return guestID;
    }

    /**
     * @param guestID the guestID to set
     */
    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    /**
     * @return the guestName
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * @param guestName the guestName to set
     */
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
