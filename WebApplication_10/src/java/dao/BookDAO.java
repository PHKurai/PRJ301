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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

/**
 *
 * @author phucl
 */
public class BookDAO implements IDAO{

    @Override
    public boolean create(Object entity) {
        return false;
    }

    @Override
    public List readAll() {
        return null;
    }

    @Override
    public Object readById(Object id) {
        return null;
    }

    @Override
    public boolean update(Object entity) {
        return false;
    }

    @Override
    public boolean delete(Object id) {
        return false;
    }

    @Override
    public List search(String searchTerm) {
        return null;
    }
 
    public List<BookDTO> searchByTitle(String searchTerm) {
        List<BookDTO> list = new ArrayList<>();
        
        String sql = "SELECT * FROM tblBooks WHERE Title LIKE ?";
        
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
}
