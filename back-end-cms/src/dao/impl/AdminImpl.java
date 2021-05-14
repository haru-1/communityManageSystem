package dao.impl;

import bean.Activity;
import bean.Association;
import bean.Page;
import bean.Student;
import dao.AdminDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminImpl extends BaseDao implements AdminDao {
    //获取全部学生列表
    public List<Student> getAllStudentList(Page page) {
        List<Student> students = new ArrayList<Student>();
        try {
            // 获取连接
            getCon();
            // 为page分页，查询总记录数
            String countsql = "SELECT count(*) FROM student";
            // 执行sql
            exeQuery(countsql);
            ResultSet countResultSet = (ResultSet) ps.executeQuery();
            // 如果查询结果存在，将其赋值给itemCont
            if (countResultSet.next()) {
                int count = countResultSet.getInt(1);
                page.setItemCont(count);
            }
            // 分页查询活动的详细信息
            String sql = "SELECT stu_id,stu_name,stu_sex,stu_major,stu_grade,stu_college,stu_tel FROM student ORDER BY stu_id limit ?,?";
            // 执行sql ，传入分页的起始页，每页大小
            exeQuery(sql, page.getItemStart(), page.getPageSize());
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

    //删除学生
    public int deleteStudent(int stu_id) {
        int result = 0;
        try {
            //获取连接
            getCon();
            //要执行的删除SQL
            String sql = "delete from student where stu_id=? ";
            //执行SQL传入参数，并获取受影响行数
            result = exeUpdate(sql, stu_id);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭所有连接
            closeAll();
        }
        return result;
    }

    //获取全部未审批活动
    public List<Activity> getApplyActivityList(Page page) {
        List<Activity> activities = new ArrayList<Activity>();
        try {
            // 获取连接
            getCon();
            // 为page分页，查询总记录数
            String countsql = "SELECT count(*) FROM activity WHERE act_status = '未审批'";
            // 执行sql
            exeQuery(countsql);
            ResultSet countResultSet = (ResultSet) ps.executeQuery();
            // 如果查询结果存在，将其赋值给itemCont
            if (countResultSet.next()) {
                int count = countResultSet.getInt(1);
                page.setItemCont(count);
            }
            // 分页查询活动的详细信息
            String sql = "SELECT activity.act_id,act_name,act_regStart,act_regEnd,act_start,act_end,as_name FROM activity,conduct,association WHERE activity.act_id = conduct.act_id and conduct.as_id = association.as_id and act_status = '未审批' ORDER BY activity.act_id limit ?,?";
            // 执行sql ，传入分页的起始页，每页大小
            exeQuery(sql, page.getItemStart(), page.getPageSize());
            // 获取查询结果
            ResultSet rs = (ResultSet) ps.executeQuery();
            // 如果存在结果将其封装为一个新的activity对象，添加到list集合
            while (rs.next()) {
                Activity activity = new Activity();
                activity.setAct_id(rs.getInt("act_id"));
                activity.setAct_name(rs.getString("act_name"));
                activity.setAct_regStart(rs.getString("act_regStart"));
                activity.setAct_regEnd(rs.getString("act_regEnd"));
                activity.setAct_start(rs.getString("act_start"));
                activity.setAct_end(rs.getString("act_end"));
                activity.setAs_name(rs.getString("as_name"));
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

    //获取全部未审批社团
    public List<Association> getApplyAssociationList(Page page) {
        List<Association> associations = new ArrayList<Association>();
        try {
            // 获取连接
            getCon();
            // 为page分页，查询总记录数
            String countsql = "SELECT count(*) FROM association where as_status='未审批'";
            // 执行sql
            exeQuery(countsql);
            ResultSet countResultSet = (ResultSet) ps.executeQuery();
            // 如果存在结果，将其赋值给itemCont
            if (countResultSet.next()) {
                int count = countResultSet.getInt(1);
                System.out.println("association count:" + count);
                page.setItemCont(count);
            }
            // 查询社团的详细信息
            String sql = "SELECT stu_id,as_id,as_name,as_introduce,as_number FROM association where as_status='未审批' ORDER BY as_id limit ?,?";
            // 执行sql ，传入分页的起始页，每页大小
            exeQuery(sql, page.getItemStart(), page.getPageSize());
            // 获取查询结果
            ResultSet rs = (ResultSet) ps.executeQuery();
            // 如果存在结果将其封装为一个新的association对象，添加到list集合
            while (rs.next()) {
                Association association = new Association();
                association.setAs_name(rs.getString("as_name"));
                association.setStu_id(rs.getInt("stu_id"));
                association.setAs_id(rs.getInt("as_id"));
                association.setAs_introduce(rs.getString("as_introduce"));
                association.setAs_number(rs.getString("as_number"));
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

    //审批社团
    public int updateApplyAssociation(int as_id, int stu_id, String state) {
        int result = 0;
        int result2 = 0;
        try {
            //获取连接
            getCon();
            //要执行的更新SQL
            String sql = "update association set as_status = ? where as_id = ?";
            //执行SQL传入参数，并获取手影响行数
            result = exeUpdate(sql, state, as_id);
            if (result != 0) {
                if (state == "通过") {
                    String sql2 = "insert member(as_id,stu_id,mem_type) values(?,?,'社长')";
                    //执行SQL传入参数，并获取手影响行数
                    result2 = exeUpdate(sql2, as_id, stu_id);
                } else {
                    result2 = deleteAssociation(as_id);
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

    //删除社团
    public int deleteAssociation(int as_id) {
        int result = 0;
        try {
            //获取连接
            getCon();
            //要执行的删除SQL
            String sql = "delete from association where as_id=?";
            //执行SQL传入参数，并获取受影响行数
            result = exeUpdate(sql, as_id);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭所有连接
            closeAll();
        }
        return result;
    }
}
