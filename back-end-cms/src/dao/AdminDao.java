package dao;

import bean.Activity;
import bean.Association;
import bean.Page;
import bean.Student;

import java.util.List;

public interface AdminDao {
    //获取全部学生列表
    List<Student> getAllStudentList(Page page);
    //删除学生
    int deleteStudent(int stu_id);
    //获取全部未审批活动
    List<Activity> getApplyActivityList(Page page);
    //获取全部未审批社团
    List<Association> getApplyAssociationList(Page page);
    //审批社团
    int updateApplyAssociation(int as_id,int stu_id,String state);
    //删除社团
    int deleteAssociation(int as_id);
}
