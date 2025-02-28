/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BookDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author phucl
 */
public class BookDAO implements IDAO<BookDTO, String> {

    @Override
    public boolean create(BookDTO entity) {
        String sql = "INSERT INTO tblBooks"
                + " (BookID,Title,Author,PublishYear,Price,Quantity) "
                + " VALUES (?,?,?,?,?,?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, entity.getId());
            ps.setString(2, entity.getTitle());
            ps.setString(3, entity.getAuthor());
            ps.setInt(4, entity.getPublishYear());
            ps.setDouble(5, entity.getPrice());
            ps.setInt(6, entity.getQuantity());

            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    @Override
    public List<BookDTO> readAll() {
        List<BookDTO> list = new ArrayList<>();
        
        String sql = "SELECT * FROM tblBooks WHERE Quantity > 0";
        
        try{
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                BookDTO book = new BookDTO(
                        rs.getString("BookID"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getInt("PublishYear"),
                        rs.getInt("Price"),
                        rs.getInt("Quantity"));
                list.add(book);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return list;
    }

    @Override
    public BookDTO readById(String id) {
        return null;
    }

    @Override
    public boolean update(BookDTO entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List search(String searchTerm) {
        return null;
    }
 
    public List<BookDTO> searchByTitle(String searchTerm) {
        List<BookDTO> list = new ArrayList<>();
        
        String sql = "SELECT * FROM tblBooks WHERE Title LIKE ? AND Quantity > 0";
        
        try{
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+searchTerm+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                BookDTO book = new BookDTO(
                        rs.getString("BookID"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getInt("PublishYear"),
                        rs.getInt("Price"),
                        rs.getInt("Quantity"));
                list.add(book);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return list;
    }

    public boolean updateQuantityToZero(String id) {
        String sql = "UPDATE tblBooks SET Quantity=0 WHERE BookID LIKE ?";
        
        try{
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            int i = ps.executeUpdate();
            
            return i>0;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return false;
    }
}
