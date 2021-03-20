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
public class TblHistoryDTO implements Serializable{
    private int historyID;
    private int billID;
    private String action;
    private boolean status;
    private String email;

    public TblHistoryDTO() {
    }

    public TblHistoryDTO(int historyID, int billID, String action, boolean status, String email) {
        this.historyID = historyID;
        this.billID = billID;
        this.action = action;
        this.status = status;
        this.email = email;
    }

    /**
     * @return the historyID
     */
    public int getHistoryID() {
        return historyID;
    }

    /**
     * @param historyID the historyID to set
     */
    public void setHistoryID(int historyID) {
        this.historyID = historyID;
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
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
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
