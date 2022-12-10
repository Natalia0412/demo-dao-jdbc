package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJdbc implements DepartmentDao {
    private Connection conn;


    public DepartmentDaoJdbc(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement st = null;
        try{
            st= conn.prepareStatement(
                    "INSERT INTO department"+
                            "(Name)"+
                            "Values"+
                            "(?)",
                    Statement.RETURN_GENERATED_KEYS
                    );
            st.setString(1, department.getName());
           int rowsAffected= st.executeUpdate();
           if (rowsAffected>0){
               ResultSet rs=st.getGeneratedKeys();
               if(rs.next()){
                   int id= rs.getInt(1);
                   department.setId(id);
               }
               DB.closeResultSet(rs);
           }
        }catch (SQLException e){
            throw  new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }


    }

    @Override
    public void update(Department department) {
        PreparedStatement st=null;
        try {
            st= conn.prepareStatement("UPDATE department \n" +
                    "set Name =? \n" +
                    "WHERE Id= ?");
            st.setString(1,department.getName());
            st.setInt(2,department.getId());
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st=null;
        try{
            st= conn.prepareStatement("DELETE FROM department WHERE Id= ?");
            st.setInt(1,id);
            int rows=st.executeUpdate();
            if(rows==0){
                throw new DbException("Id não existe");
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department WHERE Id= ? "
            );
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Department dep = instantiateDepartment(rs);
                return dep;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }

    @Override
    public List<Department> findaAll() {
        PreparedStatement st=null;
        ResultSet rt=null;
        try{
            st=conn.prepareStatement(
                    "SELECT * FROM department "
            );
            rt= st.executeQuery();
            List<Department> list =new ArrayList<>();
            Map<Integer,Department>map=new HashMap<>();
            while (rt.next()){
                Department dep= map.get(rt.getInt("Id"));
                if (dep==null){
                    dep=instantiateDepartment(rt);
                    map.put(rt.getInt("Id"),dep);
                }
               list.add(dep);
            }
            return list;
        }catch (SQLException e){
            throw  new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rt);
        }
    }
}
