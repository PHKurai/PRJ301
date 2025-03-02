/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author phucl
 */
public interface I_DAO<T, K> {
    boolean create(T entity);
    boolean update(T entity);
    boolean delete(K id);
    List<T> readAll();
    T readById(K id);
    List<T> search(String searchTerm);
}
