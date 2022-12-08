package model.dao;

import model.entities.Department;

import java.util.List;

public interface SellerDao {
    void insert(Department department);
    void update(Department department);
    void deleteById(Integer id);
    Department findById(Integer id);
    List<Department> findaAll();
}
