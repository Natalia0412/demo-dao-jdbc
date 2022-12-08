package model.dao;

import model.entities.Seller;

import java.util.List;

public interface DepartmentDao {
    void insert(Seller seller);
    void update(Seller seller);
    void deleteById(Integer id);
    Seller findById(Integer id);
    List<Seller>findaAll();
}
