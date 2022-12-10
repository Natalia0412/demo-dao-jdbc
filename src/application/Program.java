package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);

        //Department dp=new Department(1,"Vendas");

        //System.out.println(dp);
       // Seller sl=new Seller(21,"Maria","Maria@maria", LocalDate.now() ,200.0,dp);
       // System.out.println(sl);
        SellerDao sellerDao= DaoFactory.createSellerDao();// injeção de dependencia
        System.out.println("Teste numero 1:seller findById");
        Seller seller=sellerDao.findById(8);
        System.out.println(seller);
        System.out.println("Teste numero 1:seller findByDepartment");
        Department dep=new Department(2,null);
        List<Seller> list=sellerDao.findByDepartment(dep);
        for (Seller obj: list
             ) {
            System.out.println(obj);
        }
       System.out.println("Teste numero 2:seller findALL");
        list=sellerDao.findaAll();
        for (Seller obj: list
        ) {
            System.out.println(obj);
        }
        /*System.out.println("Teste numero 3:seller insert");
        Seller newSeller=new Seller(null, "Greg","greg@mail", LocalDate.now(),40.0,dep);
        sellerDao.insert(newSeller);
        System.out.println(newSeller.getId());
        System.out.println("Teste numero 4:seller update");
        seller=sellerDao.findById(8);
        seller.setName("Martha Waine");
        sellerDao.update(seller);*/
        System.out.println("Teste numero 5:seller delete");
        System.out.println("Enter com um id para teste de deleção");
        int id=sc.nextInt();
        sellerDao.deleteById(id);
        sc.close();

    }
}
