package dao.impl;

import bean.Activity;
import bean.Association;
import bean.Page;
import bean.Student;
import dao.MemberDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberImpl extends BaseDao implements MemberDao {
    //获取社团成员列表
    public List<Student> getMembersForPage(Page page, String as_name) {
        List<Student> students = new ArrayList<Student>();
        int as_id = 0;
        String mem_type = "普通成员";
        try {
            // 获取连接
            getCon();
            //获取as_id
            String idsql = "SELECT as_id FROM association WHERE as_name = ?";
            // 执行sql
            exeQuery(idsql, as_name);
            ResultSet idResultSet = (ResultSet) ps.executeQuery();
            // 如果查询结果存在，将其赋值给itemCont
            if (idResultSet.next()) {
                as_id = idResultSet.getInt(1);
            }

            // 为page分页，查询总记录数
            String countsql = "SELECT count(*) FROM member WHERE as_id = ?";
            // 执行sql
            exeQuery(countsql, as_id);
            ResultSet countResultSet = (ResultSet) ps.executeQuery();
            // 如果查询结果存在，将其赋值给itemCont
            if (countResultSet.next()) {
                int count = countResultSet.getInt(1);
                page.setItemCont(count);
            }
            // 分页查询活动的详细信息
            String sql = "SELECT student.stu_id,stu_name,stu_sex,stu_major,stu_grade,stu_college,stu_tel FROM student,member WHERE student.stu_id = member.stu_id and as_id = ? and mem_type = ? ORDER BY student.stu_id limit ?,?";
            // 执行sql ，传入分页的起始页，每页大小
            exeQuery(sql, as_id, mem_type, page.getItemStart(), page.getPageSize());
            // 获取查询结果
            ResultSet rs = (ResultSet) ps.executeQuery();
            // 如果存在结果将其封装为一个新的activity对象，添加到list集合
            while (rs.next()) {
                Student student = new Student();
                student.setStu_id(rs.getInt("stu_id"));
                student.setStu_name(rs.getString("stu_name"));
                student.setStu_sex(rs.getString("stu_sex"));
                student.setStu_major(rs.getString("stu_major"));
                student.setStu_grade(rs.getString("stu_grade"));
                student.setStu_college(rs.getString("stu_college"));
                student.setStu_tel(rs.getString("stu_tel"));
                // 将新的student对象添加到students集合中
                students.add(student);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 关闭所有连接，管道
            closeAll();
        }
        return students;
    }

    //删除社团成员
    public int deleteMember(int stu_id, String as_name) {
        int as_id = 0;
        int result = 0;
        int result2 = 0;
        try {
            //获取连接
            getCon();
            //获取as_id
            String idsql = "SELECT as_id FROM association WHERE as_name = ?";
            // 执行sql
            exeQuery(idsql, as_name);
            ResultSet idResultSet = (ResultSet) ps.executeQuery();
            // 如果查询结果存在，将其赋值给itemCont
            if (idResultSet.next()) {
                as_id = idResultSet.getInt(1);
                //要执行的删除SQL
                String sql = "delete from member where stu_id=? and as_id = ?";
                //执行SQL传入参数，并获取受影响行数
                result = exeUpdate(sql, stu_id, as_id);
                if (result != 0) {
                    String sql2 = "delete from applyassociation where stu_id=? and as_id = ?";
                    result2 = exeUpdate(sql2, stu_id, as_id);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭所有连接
            closeAll();
        }
        return result2;
    }

    //查询社团成员
    public List<Student> getStudentForSearch(String as_name, Page page) {
        return null;
    }

    //修改社团信息
    public int updateAssociationMessage(Association association, String oldName) {
        int result = 0;
        try {
            //获取连接
            getCon();
            //要执行的更新SQL
            String sql = "update association set as_name = ?, as_introduce = ?, as_number = ?, as_level = ? where as_name = ?";
            //执行SQL传入参数，并获取手影响行数
            result = exeUpdate(sql, association.getAs_name(), association.getAs_introduce(), association.getAs_number(), association.getAs_level(), oldName);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭所有连接
            closeAll();
        }
        return result;
    }

    //获取社团发布的活动列表
    public List<Activity> getAssociationActivityList(String as_name) {
        List<Activity> activities = new ArrayList<Activity>();
        int as_id = 0;
        try {
            // 获取连接
            getCon();
            //获取as_id
            String idsql = "SELECT as_id FROM association WHERE as_name = ?";
            // 执行sql
            exeQuery(idsql, as_name);
            ResultSet idResultSet = (ResultSet) ps.executeQuery();
            // 如果查询结果存在，将其赋值给itemCont
            if (idResultSet.next()) {
                as_id = idResultSet.getInt(1);
            }
            // 分页查询活动的详细信息
            String sql = "SELECT activity.act_id,act_name,act_content,act_form,act_address,act_regStart,act_regEnd,act_start,act_end,act_number,act_level FROM activity,conduct WHERE activity.act_id = conduct.act_id and as_id = ? and act_status = '通过' ";
            // 执行sql ，传入分页的起始页，每页大小
            exeQuery(sql, as_id);
            // 获取查询结果
            ResultSet rs = (ResultSet) ps.executeQuery();
            // 如果存在结果将其封装为一个新的activity对象，添加到list集合
            while (rs.next()) {
                Activity activity = new Activity();
                activity.setAct_id(rs.getInt("act_id"));
                activity.setAct_name(rs.getString("act_name"));
                activity.setAct_content(rs.getString("act_content"));
                activity.setAct_form(rs.getString("act_form"));
                activity.setAct_address(rs.getString("act_address"));
                activity.setAct_regStart(rs.getString("act_regStart"));
                activity.setAct_regEnd(rs.getString("act_regEnd"));
                activity.setAct_start(rs.getString("act_start"));
                activity.setAct_end(rs.getString("act_end"));
                activity.setAct_number(rs.getString("act_number"));
                activity.setAct_level(rs.getString("act_level"));
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

}
