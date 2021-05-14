package dao;

import bean.ApplyMessage;
import bean.Page;
import bean.Student;

import java.util.List;

public interface ApplyDao {
    //学生申请加入社团
    int addApply(ApplyMessage applyMessage);
    //学生获取社团审批信息
    List<ApplyMessage> getMyApplyMessage(int stu_id);
    //社长获取所属社团审批信息
    List<ApplyMessage> getAllApplyMessage(int stu_id,String as_name);
    //审批申请信息（批准/拒绝）
    int updateApplyState(ApplyMessage applyMessage);
    //加入社团
    int addMember(String as_name,int stu_id);

}
