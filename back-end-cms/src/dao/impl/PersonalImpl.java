package dao.impl;

import bean.Activity;
import bean.Student;
import bean.Association;
import dao.PersonalDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonalImpl extends BaseDao implements PersonalDao {
    //获取学生个人信息
    public Student getPersonalMessage(int stu_id) {
        Student student = new Student();
        try {
            // 获取连接
            getCon();
            //查询个人信息
            String sql = "select stu_name,stu_sex,stu_major,stu_grade,stu_college,stu_tel from student where stu_id = ?";
            exeQuery(sql, stu_id);
            ResultSet rs = (ResultSet) ps.executeQuery();
            if (rs.next()) {
                student.setStu_name(rs.getString("stu_name"));
                student.setStu_sex(rs.getString("stu_sex"));
                student.setStu_major(rs.getString("stu_major"));
                student.setStu_grade(rs.getString("stu_grade"));
                student.setStu_college(rs.getString("stu_college"));
                student.setStu_tel(rs.getString("stu_tel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭所有连接，管道
            closeAll();
        }
        return student;
    }

    //获取所在社团信息（学生/社长）
    public List<Association> getMyAssociation(int stu_id) {
        List<Association> associations = new ArrayList<Association>();
        try {
            // 获取连接
            getCon();
            // 查询社团的详细信息
            String sql = "SELECT as_name,as_introduce,as_number,as_level,mem_type FROM association,member WHERE association.as_id = member.as_id and member.stu_id = ? ";
            // 执行sql
            exeQuery(sql, stu_id);
            // 获取查询结果
            ResultSet rs = (ResultSet) ps.executeQuery();
            // 如果存在结果将其封装为一个新的association对象，添加到list集合
            while (rs.next()) {
                Association association = new Association();
                association.setAs_name(rs.getString("as_name"));
                association.setAs_introduce(rs.getString("as_introduce"));
                association.setAs_number(rs.getString("as_number"));
                association.setAs_level(rs.getString("as_level"));
                association.setMem_type(rs.getString("mem_type"));
                // 将新的association对象添加到associations集合中
                System.out.println(association);
                associations.add(association);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 关闭所有连接
            closeAll();
        }
        System.out.println("association" + associations);
        return associations;
    }

    //获取已参加的活动列表
    public List<Activity> getMyActivity(int stu_id) {
        List<Activity> activities = new ArrayList<Activity>();
        try {
            // 获取连接
            getCon();
            // 查询活动的详细信息
            String sql = "SELECT act_name,act_content,act_level,att_date FROM activity,attend WHERE activity.act_id = attend.act_id and attend.stu_id = ?";
            // 执行sql
            exeQuery(sql, stu_id);
            // 获取查询结果
            ResultSet rs = (ResultSet) ps.executeQuery();
            while (rs.next()) {
                // 如果存在结果将其封装为一个新的activity对象，添加到list集合
                Activity activity = new Activity();
                activity.setAct_name(rs.getString("act_name"));
                activity.setAct_content(rs.getString("act_content"));
                activity.setAct_level(rs.getString("act_level"));
                activity.setAtt_date(rs.getString("att_date"));
                // 将新的activity对象添加到activities集合中
                activities.add(activity);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 关闭所有连接，管道
            closeAll();
        }
        return activities;
    }

    //申请创建社团
    public int createAssociation(Association association,int stu_id) {
        int res = 0;
        try {
            //获取连接
            getCon();
            // 查询id最大值
            String countsql = "SELECT MAX(as_id) FROM association";
            // 执行sql
            exeQuery(countsql);
            ResultSet countResultSet = (ResultSet) ps.executeQuery();
            // 如果存在结果，将其赋值给as_id
            if (countResultSet.next()) {
                int as_id = countResultSet.getInt(1) + 1;
                //要执行的插入SQL语句
                String sql = "insert into association(as_id,stu_id,as_name,as_level,as_introduce,as_number) values(?,?,?,?,?,?)";
                //执行SQL，并传参数，并获手影响行数
                res = exeUpdate(sql,as_id,stu_id,association.getAs_name(),association.getAs_level(),association.getAs_introduce(),association.getAs_number());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭所有连接
            closeAll();
        }
        return res;
    }
}
