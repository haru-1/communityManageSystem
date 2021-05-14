package dao;

import bean.Activity;
import bean.Student;
import bean.Association;

import java.util.List;

public interface PersonalDao {
    //获取学生个人信息
    Student getPersonalMessage(int stu_id);
    //获取所在社团信息（学生/社长）
    List<Association> getMyAssociation(int stu_id);
    //获取已参加的活动列表
    List<Activity> getMyActivity(int stu_id);
    //申请创建社团
    int createAssociation(Association association,int stu_id);
}
