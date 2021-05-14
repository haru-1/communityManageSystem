package dao;

import bean.Student;

public interface LoginDao {
    //登录
    Student login(int id, String password);
    //修改密码
    int updatePassword(int id, String passwrod, String newpassword);
}
