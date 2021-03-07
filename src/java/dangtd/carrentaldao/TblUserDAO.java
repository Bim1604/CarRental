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
public class TblUserDAO implements Serializable{
    public String checkLogin(String username, String password) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            System.out.println("hi");
            String sql = "Select name "
                    + "From tblUser "
                    + "Where email = ? AND password =?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()){
                String name = rs.getString(1);
                return name;
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
        return null;
    }
}
