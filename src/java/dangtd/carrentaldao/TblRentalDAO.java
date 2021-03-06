/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangtd.carrentaldao;

import dangtd.carrentaldto.TblRentalDTO;
import dangtd.unities.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class TblRentalDAO implements Serializable {

    public boolean addRentalBill(int billID, int guestID, String rentalDate, String returnDate, float priceTotal) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Insert into tblRental "
                    + "(billID, guestID, rentalDate, returnDate, priceTotal) "
                    + "Values (?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, billID);
            ps.setInt(2, guestID);
            ps.setString(3, rentalDate);
            ps.setString(4, returnDate);
            ps.setFloat(5, priceTotal);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

//    Lấy billID lớn nhât
    public int getBillID() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "Select MAX(billID) "
                    + "From tblRental ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int billID = rs.getInt(1);
                return ++billID;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 1;
    }
    
//    Lấy bill
    public void getRentalBill(int billID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "Select billID, guestID, rentalDate, returnDate, priceTotal "
                    + "From tblRental "
                    + "Where billID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, billID);
            rs = ps.executeQuery();
            if (rs.next()){
                int guestID = rs.getInt(2);
                String rentalDate = rs.getString(3);
                String returnDate = rs.getString(4);
                float priceTotal = rs.getFloat(5);
                TblRentalDTO dto = new TblRentalDTO(billID, guestID, rentalDate, returnDate, priceTotal);
                if (this.list == null){
                    this.list = new ArrayList<>();
                }
                this.list.add(dto);
            }
        } finally {
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
    }
    private List<TblRentalDTO> list;

    public List<TblRentalDTO> getList() {
        return list;
    }
    
    
}
