/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.StartupProjectDAO;
import dao.UserDAO;
import dto.StartupProjectDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author phucl
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private StartupProjectDAO spDAO = new StartupProjectDAO();
    private String LOGIN_PAGE = "login.jsp";
    private String PROJECT_DASHBOARD_PAGE = "projectDashboard.jsp";
    private String CREATE_PROJECT_PAGE = "addProject.jsp";
    private String UPDATE_PROJECT_PAGE = "updateProject.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            if (action != null) {
                if (action.equals("login")) {
                    String strUserID = request.getParameter("username");
                    String strPassword = request.getParameter("password");
                    if (isValidLogin(strUserID, strPassword)) {
                        url = PROJECT_DASHBOARD_PAGE;
                        UserDTO user = getUser(strUserID);
                        request.getSession().setAttribute("user", user);

                        search(request, response);

                    } else {
                        request.setAttribute("messageError", "Incorrect Username or Password");
                        url = LOGIN_PAGE;
                    }
                } else if (action.equals("logout")) {
                    request.getSession().invalidate();
                    url = LOGIN_PAGE;
                } else if (action.equals("search")) {
                    url = PROJECT_DASHBOARD_PAGE;
                    search(request, response);
                } else if (action.equals("update")) {
                    String id = request.getParameter("id");
                    url = UPDATE_PROJECT_PAGE;
                    StartupProjectDTO sp = spDAO.readById(Integer.parseInt(id));
                    request.setAttribute("project", sp);
                    request.setAttribute("isSuccessful", false);
                } else if (action.equals("getUpdate")) {
                    boolean isSuccessful = false;
                    url = UPDATE_PROJECT_PAGE;
                    String id = request.getParameter("project");
                    StartupProjectDTO sp = spDAO.readById(Integer.parseInt(id));
                    request.setAttribute("project", sp);
                    String newStatus = request.getParameter("newStatus");
                    if (isValidStatus(newStatus)) {
                        newStatus = newStatus.substring(0, 1).toUpperCase() + newStatus.substring(1).toLowerCase();
                        sp.setStatus(newStatus);
                        isSuccessful = spDAO.update(sp);
                        if (!isSuccessful) {
                            request.setAttribute("messageError", "Something wrong! Cannot update satuts");
                        }
                    } else {
                        request.setAttribute("messageError", "Status cannot be empty and in 'Ideation', 'Development', 'Launch', 'Scaling'");
                    }
                    request.setAttribute("isSuccessful", isSuccessful);
                } else if (action.equals("add")) {
                    url = CREATE_PROJECT_PAGE;
                } else if (action.equals("getAdd")) {
                    String name = request.getParameter("name");
                    String description = request.getParameter("description");
                    String status = request.getParameter("status");
                    String date = request.getParameter("date");
                    
                    boolean isError = false;

                    if (name == null || name.trim().isEmpty()) {
                        isError = true;
                        request.setAttribute("name_error", "Name cannot be empty.");
                    }

                    if (status == null || !isValidStatus(status)) {
                        isError = true;
                        request.setAttribute("status_error", "Status cannot be empty and in 'Ideation', 'Development', 'Launch', 'Scaling'.");
                    }

                    if (date == null || !isValidDate(date)) {
                        isError = true;
                        request.setAttribute("date_error", "Date cannot be empty and like format 'YYYY-MM-DD'.");
                    }

                    System.out.println(spDAO.getNumberOfProject()+1);
                    StartupProjectDTO sp = new StartupProjectDTO(spDAO.getNumberOfProject()+1, name, description, status, date);

                    if (!isError) {
                        boolean check = spDAO.create(sp);
                        System.out.println(check);
                        url = PROJECT_DASHBOARD_PAGE;
                        search(request, response);
                    } else {
                        request.setAttribute("project", sp);
                        url = CREATE_PROJECT_PAGE;
                    }
                }
            }
        } catch (Exception e) {
            log("Error in MainController: " + e.toString());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    public UserDTO getUser(String username) {
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readById(username);
        return user;
    }

    public boolean isValidLogin(String username, String password) {
        UserDTO user = getUser(username);
        return user != null && user.getPassword().equals(password);
    }

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        searchTerm = searchTerm == null ? "" : searchTerm;
        List<StartupProjectDTO> spList = spDAO.search(searchTerm);
        request.setAttribute("startupProject", spList);
        request.setAttribute("searchTerm", searchTerm);
    }

    private boolean isValidStatus(String status) {
        boolean result = false;
        String[] validStatus = {"Ideation", "Development", "Launch", "Scaling"};

        for (int i = 0; i < validStatus.length; i++) {
            if (validStatus[i].equalsIgnoreCase(status)) {
                result = true;
                break;
            }
        }

        return result;
    }
    
    private boolean isValidDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
