package application;

import model.entities.Department;
import model.entities.Seller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Program {
    public static void main(String[] args) {

        Department dp=new Department(1,"Vendas");

        System.out.println(dp);
        Seller sl=new Seller(21,"Maria","Maria@maria", LocalDate.now() ,200.0,dp);
        System.out.println(sl);

    }
}
