/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.UserDAO;
import dto.UserDTO;

/**
 *
 * @author phucl
 */
public class UserTest {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        
        // insert
/*
        for (int i = 0; i < 10; i++) {
            UserDTO u = new UserDTO("USER"+i, "Ten "+i, "US", "__"+i);
            userDAO.create(u);
        }
*/        
        // update
/*
        UserDTO u = new UserDTO("SE003", "Lam Hong Phuc", "US", "Nothing");
        userDAO.update(u);
*/

        // delete
        userDAO.delete("USER9");

    }
}
