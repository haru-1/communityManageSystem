package dao.impl;

import bean.Activity;
import dao.AttendDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AttendImpl extends BaseDao implements AttendDao {
    //学生参加活动
    public int addAttendActivity(int stu_id,String act_name) {
        int msg = 0;
        //获取当前时间
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        try {
            //获取连接
            getCon();
            // 查询社团id
            String countsql = "SELECT act_id FROM activity WHERE act_name = ?";
            // 执行sql
            exeQuery(countsql, act_name);
            ResultSet countResultSet = (ResultSet) ps.executeQuery();
            // 如果存在结果，将其赋值给as_id
            if (countResultSet.next()) {
                int act_id = countResultSet.getInt(1);
                //要执行的插入SQL语句
                String sql = "insert into attend(act_id,stu_id,att_date) values(?,?,?)";
                //执行SQL，并传参数，并获手影响行数
                msg = exeUpdate(sql,act_id,stu_id, formatter.format(date));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭所有连接
            closeAll();
        }
        return msg;
    }
    //社长申请活动
    public int addApplyActivity(Activity activity) {
        int msg = 0;
        int res = 0;
        int as_id = 0;
        try {
            //获取连接
            getCon();
            //获取as_id
            String idsql = "SELECT as_id FROM association WHERE as_name = ?";
            // 执行sql
            exeQuery(idsql,activity.getAs_name());
            ResultSet idResultSet = (ResultSet) ps.executeQuery();
            // 如果查询结果存在，将其赋值给itemCont
            if (idResultSet.next()) {
                as_id = idResultSet.getInt(1);
            }
            // 查询id最大值
            String countsql = "SELECT MAX(act_id) FROM activity";
            // 执行sql
            exeQuery(countsql);
            ResultSet countResultSet = (ResultSet) ps.executeQuery();
            // 如果存在结果，将其赋值给as_id
            if (countResultSet.next()) {
                int act_id = countResultSet.getInt(1) + 1;
                //要执行的插入SQL语句
                String sql = "insert into activity(act_id,act_name,act_content,act_form,act_address,act_regStart,act_regEnd,act_start,act_end,act_number,act_level,act_status) values(?,?,?,?,?,?,?,?,?,?,?,?)";
                //执行SQL，并传参数，并获手影响行数
                msg = exeUpdate(sql,act_id,activity.getAct_name(),activity.getAct_content(),activity.getAct_form()
                ,activity.getAct_address(),activity.getAct_regStart(),activity.getAct_regEnd(),activity.getAct_start(), activity.getAct_end(),activity.getAct_number(),activity.getAct_level(),"未审批");
                if(msg!=0){
                    String sql1 = "insert into conduct(as_id,act_id) values(?,?)";
                    res = exeUpdate(sql1,as_id,act_id);
                }
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
    //审批申请信息（批准/拒绝）
    public int updateApplyState(int act_id, String state) {
        int result = 0;
        int result2 = 0;
        try {
            //获取连接
            getCon();
            //要执行的更新SQL
            String sql = "update activity set act_status = ? where act_id = ?";
            //执行SQL传入参数，并获取手影响行数
            result = exeUpdate(sql,state,act_id);
            if(state != "通过"&&result!=0){
                result2 = deleteActivity(act_id);
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
    //添加活动
    public int addActivity(Activity activity,String as_name) {
        int result = 0;
        int result2 = 0;
        int as_id = 0;
        try {
            //获取连接
            getCon();
            // 查询id最大值
            String countsql = "SELECT MAX(act_id) FROM activity";
            // 执行sql
            exeQuery(countsql);
            ResultSet countResultSet = (ResultSet) ps.executeQuery();
            // 如果存在结果，将其赋值给as_id
            if (countResultSet.next()) {
                int act_id = countResultSet.getInt(1) + 1;
                //要执行的插入SQL语句
                String sql = "insert into activity(act_id,act_name,act_content,act_form,act_address,act_regStart,act_regEnd,act_start,act_end,act_number,act_level,act_status) values(?,?,?,?,?,?,?,?,?,?,?,?)";
                //执行SQL，并传参数，并获手影响行数
                result = exeUpdate(sql,act_id,activity.getAct_name(),activity.getAct_content(),activity.getAct_form()
                        ,activity.getAct_address(),activity.getAct_regStart(),activity.getAct_regEnd(),activity.getAct_start(), activity.getAct_end(),activity.getAct_number(),activity.getAct_level(),"未审批");
                if(result!=0){
                    String idsql = "select as_id from association where as_name = ?";
                    exeQuery(idsql,as_name);
                    ResultSet idResultSet = (ResultSet) ps.executeQuery();
                    if(idResultSet.next()){
                        as_id = idResultSet.getInt(1);
                    }
                    String sql2 = "insert into conduct(as_id,act_id) values(?,?)";
                    result2 = exeUpdate(sql2,as_id,act_id);
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
    //删除活动
    public int deleteActivity(int act_id) {
        int result = 0;
        int result2 = 0;
        try {
            //获取连接
            getCon();
            //要执行的删除SQL
            String sql = "delete from conduct where act_id=?";
            //执行SQL传入参数，并获取受影响行数
            result = exeUpdate(sql, act_id);
            if(result !=0){
                String sql2 = "delete from activity where act_id=?";
                result2 = exeUpdate(sql2, act_id);
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
}
