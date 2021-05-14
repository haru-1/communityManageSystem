package dao.impl;

import bean.ApplyMessage;
import dao.ApplyDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApplyImpl extends BaseDao implements ApplyDao {

    //学生申请加入社团
    public int addApply(ApplyMessage applyMessage) {
        int msg = 0;
        //获取当前时间
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(111);
        try {
            //获取连接
            getCon();
            // 查询社团id
            String countsql = "SELECT as_id FROM association WHERE as_name = ?";
            // 执行sql
            exeQuery(countsql, applyMessage.getAs_name());
            ResultSet countResultSet = (ResultSet) ps.executeQuery();
            // 如果存在结果，将其赋值给as_id
            if (countResultSet.next()) {
                int as_id = countResultSet.getInt(1);
                //要执行的插入SQL语句
                String sql = "insert into applyassociation(as_id,stu_id,ap_content,ap_applyDate) values(?,?,?,?)";
                //执行SQL，并传参数，并获手影响行数
                msg = exeUpdate(sql,as_id,applyMessage.getStu_id(),applyMessage.getAp_content(),String.valueOf(formatter.format(date)));
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

    //学生获取社团审批信息
    public List<ApplyMessage> getMyApplyMessage(int stu_id) {
        List<ApplyMessage> applyMessages = new ArrayList<ApplyMessage>();
        try {
            // 获取连接
            getCon();
            String sql = "SELECT as_name,ap_content,ap_state,ap_applyDate,ap_passDate FROM applyassociation,association WHERE applyassociation.as_id = association.as_id AND applyassociation.stu_id = ?";
            exeQuery(sql, stu_id);
            // 获取查询结果
            ResultSet rs = (ResultSet) ps.executeQuery();
            // 如果存在结果将其封装为一个新的applyMessage对象，添加到list集合
            while (rs.next()){
                ApplyMessage applyMessage = new ApplyMessage();
                applyMessage.setAs_name(rs.getString("as_name"));
                applyMessage.setAp_content(rs.getString("ap_content"));
                applyMessage.setAp_state(rs.getString("ap_state"));
                applyMessage.setAp_applyDate(rs.getString("ap_applyDate"));
                applyMessage.setAp_passDate(rs.getString("ap_passDate"));
                applyMessages.add(applyMessage);
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 关闭所有连接
            closeAll();
        }
        return applyMessages;
    }

    //社长获取所属社团审批信息
    public List<ApplyMessage> getAllApplyMessage(int stu_id,String as_name) {
        List<ApplyMessage> applyMessages = new ArrayList<ApplyMessage>();
        String ap_state = "未审批";
        try {
            // 获取连接
            getCon();
            String sql = "SELECT ap_id,student.stu_id,stu_name,ap_content,ap_state,ap_applyDate,ap_passDate " +
                    "FROM applyassociation,association,student WHERE applyassociation.as_id = association.as_id " +
                    "AND applyassociation.stu_id = student.stu_id AND association.stu_id = ? AND as_name = ? AND ap_state = ?";
            exeQuery(sql, stu_id,as_name,ap_state);
            // 获取查询结果
            ResultSet rs = (ResultSet) ps.executeQuery();
            // 如果存在结果将其封装为一个新的applyMessage对象，添加到list集合
            while (rs.next()){
                ApplyMessage applyMessage = new ApplyMessage();
                applyMessage.setAp_id(rs.getInt("ap_id"));
                applyMessage.setStu_id(rs.getInt("stu_id"));
                applyMessage.setStu_name(rs.getString("stu_name"));
                applyMessage.setAp_content(rs.getString("ap_content"));
                applyMessage.setAp_state(rs.getString("ap_state"));
                applyMessage.setAp_applyDate(rs.getString("ap_applyDate"));
                applyMessage.setAp_passDate(rs.getString("ap_passDate"));
                applyMessages.add(applyMessage);
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 关闭所有连接
            closeAll();
        }
        return applyMessages;
    }

    //审批申请信息（批准/拒绝）
    public int updateApplyState(ApplyMessage applyMessage) {
        int result = 0;
        //获取当前时间
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(11);
        try {
            //获取连接
            getCon();
            //要执行的更新SQL
            String sql = "update applyassociation set ap_state = ?,ap_passDate = ? where ap_id = ?";
            //执行SQL传入参数，并获取手影响行数
            result = exeUpdate(sql,applyMessage.getAp_state(),formatter.format(date),applyMessage.getAp_id());
            if(applyMessage.getAp_state() == "批准"){
                addMember(applyMessage.getAs_name(),applyMessage.getStu_id());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭所有连接
            closeAll();
        }
        return result;
    }

    //加入社团
    public int addMember(String as_name, int stu_id) {
        int msg = 0;
        try {
            //获取连接
            getCon();
            // 查询社团id
            String countsql = "SELECT as_id FROM association WHERE as_name = ?";
            // 执行sql
            exeQuery(countsql, as_name);
            ResultSet countResultSet = (ResultSet) ps.executeQuery();
            // 如果存在结果，将其赋值给as_id
            if (countResultSet.next()) {
                int as_id = countResultSet.getInt(1);
                //要执行的插入SQL语句
                String sql = "insert into member(as_id,stu_id) values(?,?)";
                //执行SQL，并传参数，并获手影响行数
                msg = exeUpdate(sql, as_id,stu_id);
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
}
