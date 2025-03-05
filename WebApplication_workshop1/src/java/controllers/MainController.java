/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.StartupProjectDAO;
import dto.StartupProjectDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.AuthUtils;
import utils.ValidUtils;

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
    private String processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url;
        String strUserID = request.getParameter("username");
        String strPassword = request.getParameter("password");
        if (AuthUtils.isValidLogin(strUserID, strPassword)) {
            url = PROJECT_DASHBOARD_PAGE;
            UserDTO user = AuthUtils.getUser(strUserID);
            request.getSession().setAttribute("user", user);
            HttpSession session = request.getSession();
            if (!AuthUtils.isAdmin(session)) {
                List<StartupProjectDTO> spList = spDAO.readAll();
                request.setAttribute("startupProject", spList);
            } else {
                processSearch(request, response);
            }
            
        } else {
            request.setAttribute("messageError", "Incorrect Username or Password");
            url = LOGIN_PAGE;
        }
        return url;
    }

    private String processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isLoggedIn(session)) {
            request.getSession().invalidate();
            url = LOGIN_PAGE;
        }
        return url;

    }

    private String processSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = PROJECT_DASHBOARD_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isAdmin(session)) {
            url = PROJECT_DASHBOARD_PAGE;
            String searchTerm = request.getParameter("searchTerm");
            searchTerm = searchTerm == null ? "" : searchTerm;
            List<StartupProjectDTO> spList = spDAO.search(searchTerm);
            request.setAttribute("startupProject", spList);
            request.setAttribute("searchTerm", searchTerm);
        }
        return url;
    }

    private String processUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = PROJECT_DASHBOARD_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isAdmin(session)) {
            String id = request.getParameter("id");
            url = UPDATE_PROJECT_PAGE;
            StartupProjectDTO sp = spDAO.readById(Integer.parseInt(id));
            request.setAttribute("project", sp);
            request.setAttribute("isSuccessful", false);
        }
        return url;
    }

    private String processGetUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = PROJECT_DASHBOARD_PAGE;

        HttpSession session = request.getSession();
        if (AuthUtils.isAdmin(session)) {
            boolean isSuccessful = false;
            url = UPDATE_PROJECT_PAGE;
            String id = request.getParameter("project");
            StartupProjectDTO sp = spDAO.readById(Integer.parseInt(id));
            request.setAttribute("project", sp);
            String newStatus = request.getParameter("newStatus");
            if (ValidUtils.isValidStatus(newStatus)) {
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
        }

        return url;
    }

    private String processAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = PROJECT_DASHBOARD_PAGE;

        HttpSession session = request.getSession();
        if (AuthUtils.isAdmin(session)) {
            url = CREATE_PROJECT_PAGE;
        }

        return url;
    }

    private String processGetAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = PROJECT_DASHBOARD_PAGE;

        HttpSession session = request.getSession();
        if (AuthUtils.isAdmin(session)) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String status = request.getParameter("status");
            String date = request.getParameter("date");

            boolean isError = false;

            if (name == null || name.trim().isEmpty()) {
                isError = true;
                request.setAttribute("name_error", "Name cannot be empty.");
            }

            if (status == null || !ValidUtils.isValidStatus(status)) {
                isError = true;
                request.setAttribute("status_error", "Status cannot be empty and in 'Ideation', 'Development', 'Launch', 'Scaling'.");
            }

            if (date == null || !ValidUtils.isValidDate(date)) {
                isError = true;
                request.setAttribute("date_error", "Date cannot be empty and like format 'YYYY-MM-DD'.");
            }

            System.out.println(spDAO.getNumberOfProject() + 1);
            StartupProjectDTO sp = new StartupProjectDTO(spDAO.getNumberOfProject() + 1, name, description, status, date);

            if (!isError) {
                boolean check = spDAO.create(sp);
                System.out.println(check);
                url = PROJECT_DASHBOARD_PAGE;
                processSearch(request, response);
            } else {
                request.setAttribute("project", sp);
                url = CREATE_PROJECT_PAGE;
            }
        }

        return url;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            if (action != null) {
                if (action.equals("login")) {
                    url = processLogin(request, response);
                } else if (action.equals("logout")) {
                    url = processLogout(request, response);
                } else if (action.equals("search")) {
                    url = processSearch(request, response);
                } else if (action.equals("update")) {
                    url = processUpdate(request, response);
                } else if (action.equals("getUpdate")) {
                    url = processGetUpdate(request, response);
                } else if (action.equals("add")) {
                    url = processAdd(request, response);
                } else if (action.equals("getAdd")) {
                    url = processGetAdd(request, response);
                }
            }
        } catch (Exception e) {
            log("Error in MainController: " + e.toString());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
