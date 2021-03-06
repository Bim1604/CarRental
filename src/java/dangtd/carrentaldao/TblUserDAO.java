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
public class TblUserDAO implements Serializable {

    public String checkLogin(String username, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select name "
                    + "From tblUser "
                    + "Where email = ? AND password = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString(1);
                return name;
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
        return null;
    }
    public String checkLogin(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select name "
                    + "From tblUser "
                    + "Where email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString(1);
                return name;
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
        return null;
    }
//    L???y role
    public boolean getRole(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT roleID "
                        + "FROM tblUser "
                        + "WHERE email = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    boolean role = rs.getBoolean(1);
                    return role;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }
    
//    Ki???m tra tr???ng th??i t??i kho???n
     public boolean checkStatusAccount(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select statusID "
                    + "From tblUser "
                    + "Where email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                boolean status = rs.getBoolean(1);
                return status;
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
        return false;
    }
//    T???o t??i kho???n m???i
    public boolean createNewAccount(String email, String password, String phone, String name, String address) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Insert into tblUser "
                    + "(email, password, phone, name, address, createDate, statusID, roleID) "
                    + "Values (?,?,?,?,?,CURRENT_TIMESTAMP,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, phone);
            ps.setString(4, name);
            ps.setString(5, address);
            ps.setBoolean(6, false);
            ps.setBoolean(7, false);
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
//    Ki???m tra t??i kho???n tr??ng

    public boolean checkExistAccount(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "Select name "
                    + "From tblUser "
                    + "Where email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString(1);
                if (fullName.length() > 0) {
                    return true;
                }   
            }
        } finally {
            if (rs != null){
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
//    C???p nh???t tr???ng th??i ng?????i d??ng
    public boolean updateStatus(String email) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DBHelper.makeConnection();
            String sql = "Update tblUser "
                    + "Set statusID = ? "
                    + "Where email = ?";
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setString(2, email);
            int row = ps.executeUpdate();
            if (row > 0 ){
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
}
