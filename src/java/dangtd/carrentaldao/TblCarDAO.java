/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangtd.carrentaldao;

import dangtd.carrentaldto.TblCarDTO;
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
public class TblCarDAO implements Serializable {

    public void loadCar(int pageIndex, int pageSize) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "With x as (select ROW_NUMBER() over (order by carID) as r, "
                    + "carID,carName, color, year, categoryID, price, quantity, image "
                    + "From tblCar) "
                    + "Select carID,carName, color, year, categoryID, price, quantity, image "
                    + "From x Where r Between ? AND ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, pageIndex * pageSize - (pageSize - 1));
            ps.setInt(2, pageIndex * pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                String carID = rs.getString(1);
                String carName = rs.getString(2);
                String color = rs.getString(3);
                String year = rs.getString(4);
                String categoryID = rs.getString(5);               
                TblCategoryDAO cateDAO = new TblCategoryDAO();
                String categoryName = cateDAO.getCategoryName(categoryID);
                float price = rs.getFloat(6);
                int quantity = rs.getInt(7);
                String img = rs.getString(8);
                TblCarDTO dto = new TblCarDTO(carID, carName, color, year, categoryName, price, quantity, img);
                if (this.listCar == null) {
                    this.listCar = new ArrayList<>();
                }
                this.listCar.add(dto);
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
    }
    private List<TblCarDTO> listCar;

    public List<TblCarDTO> getListCar() {
        return listCar;
    }

    public int countTotalCar() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select count(carID) "
                    + "From tblCar";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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
        return 0;
    }
//    ????m s??? l?????ng xe ???????c t??m ki???m
    
    public int countSearchCar(String name, String cateID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select count(carID) "
                    + "From tblCar "
                    + "Where carName Like ? AND categoryID LIKE ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            if (cateID.isEmpty()){
                ps.setString(2, "%" + cateID + "%");
            } else {
                ps.setString(2, cateID);
            }      
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
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
        return 0;
    }

//    T??m ki???m xe theo t??n
    public void seachCar(int pageIndex, int pageSize, String name, String cateID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "With x as (select ROW_NUMBER() over (order by carID) as r, "
                    + "carID,carName, color, year, categoryID, price, quantity, image "
                    + "From tblCar "
                    + "Where carName Like ? AND categoryID LIKE ?) "
                    + "Select carID,carName, color, year, categoryID, price, quantity, image "
                    + "From x Where r Between ? AND ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            if (cateID.isEmpty()){
                ps.setString(2, "%" + cateID + "%");
            } else {
                ps.setString(2, cateID);
            }            
            ps.setInt(3, pageIndex * pageSize - (pageSize - 1));
            ps.setInt(4, pageIndex * pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                String carID = rs.getString(1);
                String carName = rs.getString(2);
                String color = rs.getString(3);
                String year = rs.getString(4);
                String categoryID = rs.getString(5);               
                TblCategoryDAO cateDAO = new TblCategoryDAO();
                String categoryName = cateDAO.getCategoryName(categoryID);
                float price = rs.getFloat(6);
                int quantity = rs.getInt(7);
                String img = rs.getString(8);
                TblCarDTO dto = new TblCarDTO(carID, carName, color, year, categoryName, price, quantity, img);
                if (this.listCar == null) {
                    this.listCar = new ArrayList<>();
                }
                this.listCar.add(dto);
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
    }
    
//    chi ti???t xe
    public void loadDetailsCar(String carID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            String sql = "Select carID,carName, color, year, categoryID, price, quantity, image "
                    + "From tblCar "
                    + "Where carID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, carID);
            rs = ps.executeQuery();
            if (rs.next()){
                String carName = rs.getString(2);
                String color = rs.getString(3);
                String year = rs.getString(4);
                String categoryID = rs.getString(5);               
                TblCategoryDAO cateDAO = new TblCategoryDAO();
                String categoryName = cateDAO.getCategoryName(categoryID);
                float price = rs.getFloat(6);
                int quantity = rs.getInt(7);
                String img = rs.getString(8);
                TblCarDTO dto = new TblCarDTO(carID, carName, color, year, categoryName, price, quantity, img);
                System.out.println("dto: " + dto);
                if (this.listCar == null) {
                    this.listCar = new ArrayList<>();
                }
                this.listCar.add(dto);
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
//    L???y gi?? c???a ?? t??
    public float getPriceCar(String carID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            String sql = "Select price "
                    + "From tblCar "
                    + "Where carID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, carID);
            rs = ps.executeQuery();
            if (rs.next()){
                return rs.getFloat(1);
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
        return 0;
    }
//    L???y s??? l?????ng xe c??n ????? thu??
    public int getQuantity(String carID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            String sql = "Select quantity "
                    + "From tblCar "
                    + "Where carID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, carID);
            rs = ps.executeQuery();
            if (rs.next()){
                return rs.getInt(1);
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
        return 0;
    }
//    L???y t??n xe
    public String getCarName(String carID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            String sql = "Select carName "
                    + "From tblCar "
                    + "Where carID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, carID);
            rs = ps.executeQuery();
            if (rs.next()){
                return rs.getString(1);
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
