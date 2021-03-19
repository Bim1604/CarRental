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
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class TblDetailsRentDAO implements Serializable{
    public boolean addDetailsRent(int billID, String carID, int amount, float price, float total) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DBHelper.makeConnection();
            String sql = "Insert into tblDetailsRent "
                    + "(billID, carID, amount, price, total) "
                    + "Values (?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, billID);
            ps.setString(2, carID);
            ps.setInt(3, amount);
            ps.setFloat(4, price);
            ps.setFloat(5, total);
            int row = ps.executeUpdate();
            if (row > 0){
                return true;
            }
        } finally {
            if (ps != null){
                ps.close();
            }
            if (con !=  null){
                con.close();
            }
        }
        return false;
    }
}
