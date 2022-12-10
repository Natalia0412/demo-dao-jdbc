package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.dao.impl.DepartmentDaoJdbc;
import model.entities.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("teste findByID");
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        System.out.println(departmentDao.findById(1));
        System.out.println("teste findAll");
        List<Department> list = departmentDao.findaAll();
        for (Department dep : list
        ) {
            System.out.println(dep);
        }
        System.out.println("teste Delete ");
       // departmentDao.deleteById(6);
        System.out.println("teste insert ");
        Department dep = new Department(null,"Comercial");
        departmentDao.insert(dep);
        System.out.println("teste update ");
        System.out.println("Digite um para alterar");
        String nome = sc.next();
        System.out.println("digite o id para alterar");
        int id = sc.nextInt();
        dep = departmentDao.findById(id);
        dep.setName(nome);
        departmentDao.update(dep);



    }
}
