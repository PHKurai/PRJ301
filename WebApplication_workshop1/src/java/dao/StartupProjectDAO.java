/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.StartupProjectDTO;
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
public class StartupProjectDAO implements I_DAO<StartupProjectDTO, Integer>{

    @Override
    public boolean create(StartupProjectDTO entity) {
        String sql = "INSERT INTO tblStartupProjects"
                + " (project_id, project_name, Description, Status, estimated_launch) "
                + " VALUES (?,?,?,?,?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getDescription());
            ps.setString(4, entity.getStatus());
            ps.setString(5, entity.getEstimatedLaunch());

            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    @Override
    public boolean update(StartupProjectDTO entity) {
        String sql = "UPDATE tblStartupProjects SET "
                + "Status = ? "
                + "WHERE project_id = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, entity.getStatus());
            ps.setInt(2, entity.getId());
            int n = ps.executeUpdate();
            return n > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public List<StartupProjectDTO> readAll() {
        return null;
    }

    @Override
    public StartupProjectDTO readById(Integer id) {        
        String sql = "SELECT * FROM tblStartupProjects WHERE project_id LIKE ?";
        
        try{
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                StartupProjectDTO sp = new StartupProjectDTO(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getString("estimated_launch"));
                return sp;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return null;
    }

    @Override
    public List<StartupProjectDTO> search(String searchTerm) {
        List<StartupProjectDTO> list = new ArrayList<>();
        
        String sql = "SELECT * FROM tblStartupProjects WHERE project_name LIKE ?";
        
        try{
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+searchTerm+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                StartupProjectDTO sp = new StartupProjectDTO(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getString("estimated_launch"));
                list.add(sp);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return list;
    }
    
    public int getNumberOfProject() {
        String sql = "SELECT COUNT(project_id) AS num FROM tblStartupProjects";
        
        try{
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("num");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0;
    }
}
