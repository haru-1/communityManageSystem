package dao.impl;

import bean.Student;
import dao.LoginDao;
import util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginImpl extends BaseDao implements LoginDao {
    //登录
    public Student login(int id, String password) {
        Student student = new Student();
        try {
            getCon();
            String adminsql = "select * from admin where ad_id = ? and ad_password = ?";
            exeQuery(adminsql,id,password);
            ResultSet adminResultSet = (ResultSet) ps.executeQuery();
            if(adminResultSet.next()){
                student.setStu_name("isAdmin");
                return student;
            }
            String sql = "select * from student where stu_id = ? and stu_password =?";
            exeQuery(sql,id,password);
            ResultSet resultSet = (ResultSet) ps.executeQuery();
            if(resultSet.next()){
                student.setStu_name("notAdmin");
                return student;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return null;
    }

    //修改密码
    public int updatePassword(int id, String password, String newpassword) {
        int res = 0;
        try {
            getCon();
            String sql = "update student set stu_password = ? where stu_id= ? and stu_password = ?;";
            res = exeUpdate(sql,newpassword,id,password);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeAll();
        }
        return res;
    }
}
