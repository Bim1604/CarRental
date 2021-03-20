/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangtd.carrentaldao;

import dangtd.carrentaldto.TblHistoryDTO;
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
public class TblHistoryDAO implements Serializable {

    public boolean addBillToHistory(int historyID, int billID, String action, boolean status, String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DBHelper.makeConnection();
            String sql = "Insert into tblHistory "
                    + "(historyID, billID, action, status, email) "
                    + "Values (?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, historyID);
            ps.setInt(2, billID);
            ps.setString(3, action);
            ps.setBoolean(4, status);
            ps.setString(5, email);
            int rs = ps.executeUpdate();
            if (rs > 0){
                return true;
            }
        } finally{
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
        return false;
    }
    
//    Lấy historyID lớn nhất
    public int getHistoryID() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "Select MAX(historyID) "
                    + "From tblhistory ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()){
                int historyID = rs.getInt(1);
                return ++historyID;
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
    
    private List<TblHistoryDTO> list;

    public List<TblHistoryDTO> getList() {
        return list;
    }
    
//    Lấy lịch sử thao tác
     public void getHistory(String userID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "Select historyID, billID, action, status, email "
                    + "From tblHistory "
                    + "Where email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            while (rs.next()){
                int historyID = rs.getInt(1);
                int billID = rs.getInt(2);
                String action = rs.getString(3);
                boolean status = rs.getBoolean(4);
                String email = rs.getString(5);
                TblHistoryDTO dto = new TblHistoryDTO(historyID, billID, action, status, email);
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
}
