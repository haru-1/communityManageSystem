package dao;

import bean.Activity;
import bean.Association;
import bean.Page;
import bean.Student;
import java.util.List;

public interface MemberDao {
    //获取社团成员列表
    List<Student> getMembersForPage(Page page,String as_name);
    //删除社团成员
    int deleteMember(int stu_id,String as_name);
    //查询社团成员
    List<Student> getStudentForSearch(String as_name, Page page);
    //修改社团信息
    int updateAssociationMessage(Association association,String oldName);
    //获取社团发布的活动列表
    List<Activity> getAssociationActivityList(String as_name);
}
