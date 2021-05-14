package dao.impl;

import bean.Activity;
import bean.Association;
import bean.Page;
import dao.ActivityDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityImpl extends BaseDao implements ActivityDao {
    //获取活动集合
    public List<Activity> getActivityForPage(Page page) {
        List<Activity> activities = new ArrayList<Activity>();
        try {
            // 获取连接
            getCon();
            // 为page分页，查询总记录数
            String countsql = "SELECT count(*) FROM activity WHERE act_status = '通过'";
            // 执行sql
            exeQuery(countsql);
            ResultSet countResultSet = (ResultSet) ps.executeQuery();
            // 如果查询结果存在，将其赋值给itemCont
            if (countResultSet.next()) {
                int count = countResultSet.getInt(1);
                page.setItemCont(count);
            }
            // 分页查询活动的详细信息
            String sql = "SELECT act_name,act_regStart,act_regEnd,act_end,as_name FROM activity,conduct,association WHERE activity.act_id = conduct.act_id and conduct.as_id = association.as_id and act_status = '通过' ORDER BY activity.act_id limit ?,?";
            // 执行sql ，传入分页的起始页，每页大小
            exeQuery(sql, page.getItemStart(), page.getPageSize());
            // 获取查询结果
            ResultSet rs = (ResultSet) ps.executeQuery();
            // 如果存在结果将其封装为一个新的activity对象，添加到list集合
            while (rs.next()) {
                Activity activity = new Activity();
                activity.setAct_name(rs.getString("act_name"));
                activity.setAct_regStart(rs.getString("act_regStart"));
                activity.setAct_regEnd(rs.getString("act_regEnd"));
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

    //获取分类的活动集合
    public List<Activity> getActivityLevelForPage(Page page, String act_level) {
        List<Activity> activities = new ArrayList<Activity>();
        try {
            // 获取连接
            getCon();
            // 为page分页，查询总记录数
            String countsql = "SELECT count(*) FROM activity WHERE act_level = ? and act_status = '通过'";
            // 执行sql
            exeQuery(countsql,act_level);
            ResultSet countResultSet = (ResultSet) ps.executeQuery();
            // 如果查询结果存在，将其赋值给itemCont
            if (countResultSet.next()) {
                int count = countResultSet.getInt(1);
                page.setItemCont(count);
            }
            // 分页查询活动的详细信息
            String sql = "SELECT act_name,act_regStart,act_regEnd,act_end,as_name FROM activity,conduct,association WHERE activity.act_id = conduct.act_id and conduct.as_id = association.as_id and act_level = ? and act_status = '通过' ORDER BY activity.act_id limit ?,?";
            // 执行sql ，传入分页的起始页，每页大小
            exeQuery(sql,act_level, page.getItemStart(), page.getPageSize());
            // 获取查询结果
            ResultSet rs = (ResultSet) ps.executeQuery();
            // 如果存在结果将其封装为一个新的activity对象，添加到list集合
            while (rs.next()) {
                Activity activity = new Activity();
                activity.setAct_name(rs.getString("act_name"));
                activity.setAct_regStart(rs.getString("act_regStart"));
                activity.setAct_regEnd(rs.getString("act_regEnd"));
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

    //获取活动详细信息
    public Activity getActivityDetail(String act_name) {
        Activity activity = new Activity();
        try {
            // 获取连接
            getCon();
            // 查询活动详细信息
            String sql = "SELECT act_name,act_content,act_form,act_address,act_regStart,act_regEnd,act_start,act_end,act_number,act_level,as_name " +
                    "FROM activity,conduct,association WHERE activity.act_id = conduct.act_id and conduct.as_id = association.as_id and act_name = ?";
            // 执行sql
            exeQuery(sql,act_name);
            // 获取查询结果
            ResultSet rs = (ResultSet) ps.executeQuery();
            if(rs.next()) {
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
                activity.setAs_name(rs.getString("as_name"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 关闭所有连接，管道
            closeAll();
        }
        return activity;
    }

    //搜索相关活动
    public List<Activity> getActivityForSearch(String act_name, Page page) {
        List<Activity> activities = new ArrayList<Activity>();
        try {
            // 获取连接
            getCon();
            // 为page分页，查询总记录数
            String countsql = "SELECT count(*) FROM activity WHERE act_status = '通过' and act_name = ?";
            // 执行sql
            exeQuery(countsql,"%" + act_name + "%");
            ResultSet countResultSet = (ResultSet) ps.executeQuery();
            // 如果查询结果存在，将其赋值给itemCont
            if (countResultSet.next()) {
                int count = countResultSet.getInt(1);
                page.setItemCont(count);
            }
            // 分页查询活动的详细信息
            String sql = "SELECT act_name,act_regStart,act_regEnd,act_end,as_name FROM activity,conduct,association WHERE activity.act_id = conduct.act_id and conduct.as_id = association.as_id and act_status = '通过' and act_name LIKE ? ORDER BY activity.act_id limit ?,?";
            // 执行sql ，传入分页的起始页，每页大小
            exeQuery(sql,"%" + act_name + "%", page.getItemStart(), page.getPageSize());
            // 获取查询结果
            ResultSet rs = (ResultSet) ps.executeQuery();
            // 如果存在结果将其封装为一个新的activity对象，添加到list集合
            while (rs.next()) {
                Activity activity = new Activity();
                activity.setAct_name(rs.getString("act_name"));
                activity.setAct_regStart(rs.getString("act_regStart"));
                activity.setAct_regEnd(rs.getString("act_regEnd"));
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

}
