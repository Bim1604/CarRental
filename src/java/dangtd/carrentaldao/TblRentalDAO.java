/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangtd.carrentaldao;

import dangtd.unities.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class TblRentalDAO implements Serializable {

    public boolean addRentalBill(int billID, int guestID, String rentalDate, String returnDate, float priceTotal, String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Insert into tblRental "
                    + "(billID, guestID, rentalDate, returnDate, priceTotal, email) "
                    + "Values (?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, billID);
            ps.setInt(2, guestID);
            ps.setString(3, rentalDate);
            ps.setString(4, returnDate);
            ps.setFloat(5, priceTotal);
            ps.setString(6, email);
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
}
