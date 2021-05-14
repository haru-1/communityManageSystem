package dao;

import bean.Activity;

public interface AttendDao {
    //参加活动
    int addAttendActivity(int stu_id,String act_name);
    //申请活动
    int addApplyActivity(Activity activity);
    //审批申请信息（批准/拒绝）
    int updateApplyState(int act_id,String state);
    //添加活动
    int addActivity(Activity activity,String as_name);
    //删除活动
    int deleteActivity(int act_id);
}
