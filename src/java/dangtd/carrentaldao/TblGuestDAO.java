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
public class TblGuestDAO implements Serializable{
    public boolean addGuestInfo(int guestID, String guestName, String phone, String address) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DBHelper.makeConnection();
            String sql = "Insert into tblGuest "
                    + "(guestID, guestName, phone, address) "
                    + "Values (?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, guestID);
            ps.setString(2, guestName);
            ps.setString(3, phone);
            ps.setString(4, address);
            int row = ps.executeUpdate();
            if (row > 0){
                return true;
            }
        } finally {
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
        return false;
    }
    
//    Lấy guestID lớn nhât
    public int getGuestID() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "Select MAX(guestID) "
                    + "From tblGuest ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()){
                int guestID = rs.getInt(1);
                return ++guestID;
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
        return 1;
    }
}
