/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangtd.carrentaldao;

import dangtd.carrentaldto.TblCategoryDTO;
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
public class TblCategoryDAO implements Serializable{
    public void getCategory() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            String sql = "Select categoryID, categoryName "
                    + "From tblCategory ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                String categoryID = rs.getString(1);
                String categoryName = rs.getString(2);
                TblCategoryDTO dto = new TblCategoryDTO(categoryID, categoryName);
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
    
    private List<TblCategoryDTO> list;

    public List<TblCategoryDTO> getList() {
        return list;
    }
    
    public String getCategoryName(String categoryID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "Select categoryName "
                    + "From tblCategory "
                    + "Where categoryID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, categoryID);
            rs = ps.executeQuery();
            if (rs.next()){
                String categoryName = rs.getString(1);
                return categoryName;
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
