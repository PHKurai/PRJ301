/**
 * DAO là một design pattern dùng để truy cập và thao tác với database
 * Chứa tất cả các phương thức CRUD (Create, Read, Update, Delete)
 * Đóng gói toàn bộ logic truy cập database
 * Tách biệt logic truy cập dữ liệu khỏi business logic
 * Giúp code dễ maintain và test hơn
 */
package dao;

import dto.UserDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

/**
 *
 * @author phucl
 */
public class UserDAO implements IDAO<UserDTO, String>{

    @Override
    public boolean create(UserDTO entity) {
        boolean result = false;
        
        String sql = "INSERT [dbo].[tblUsers] ([userID], [fullName], [roleID], [password])"
                + "VALUES ("
                + "N'" + entity.getUserId() + "', "
                + "N'" + entity.getFullName()+ "', "
                + "N'" + entity.getRoleId() + "', "
                + "N'" + entity.getPassword() + "')";
        
        result = executeUpdate(sql);
        
        return result;
    }

    @Override
    public List<UserDTO> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDTO readById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(UserDTO entity) {
        boolean result = false;
        
        String sql = "UPDATE [tblUsers] SET "
                + "[fullName] = N'" + entity.getFullName() + "', "
                + "[roleID] = N'" + entity.getRoleId() + "', "
                + "[password] = N'" + entity.getPassword() + "' "
                + "WHERE [userID] = N'" + entity.getUserId() + "'";
        
        result = executeUpdate(sql);
        
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        
        String sql = "DELETE FROM [tblUsers] "
                + "WHERE [userID] = N'" + id + "'";
        
        result = executeUpdate(sql);
        
        return result;
    }

    @Override
    public List<UserDTO> search(String searchTerm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean executeUpdate(String sql) {
        boolean result = false;
        
        try {
            Connection conn = DBUtils.getConnection();
            Statement st = conn.createStatement();
            int n = st.executeUpdate(sql);
            
            result = n > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
}
