package dao.impl;

import bean.Association;
import bean.Page;
import dao.AssociationDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static util.DBUtil.getConn;

public class AssociationImpl extends BaseDao implements AssociationDao {
    //获取社团集合
    public List<Association> getAssociationForPage(Page page) {
        List<Association> associations = new ArrayList<Association>();
        try {
            // 获取连接
            getCon();
            // 为page分页，查询总记录数
            String countsql = "SELECT count(*) FROM association where as_status='通过'";
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
            String sql = "SELECT as_name,as_introduce,as_number FROM association where as_status='通过' ORDER BY as_id limit ?,?";
            // 执行sql ，传入分页的起始页，每页大小
            exeQuery(sql, page.getItemStart(), page.getPageSize());
            // 获取查询结果
            ResultSet rs = (ResultSet) ps.executeQuery();
            // 如果存在结果将其封装为一个新的association对象，添加到list集合
            while (rs.next()) {
                Association association = new Association();
                association.setAs_name(rs.getString("as_name"));

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
        System.out.println("association"+associations);
        return associations;
    }

    //获取分类的社团集合
    public List<Association> getAssociationLevelForPage(Page page, String as_level) {
        List<Association> associations = new ArrayList<Association>();
        try {
            // 获取连接
            getCon();
            // 为page分页，查询总记录数
            String countsql = "SELECT count(*) FROM association where as_status='通过'";
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
            String sql = "SELECT as_name,as_introduce,as_number FROM association WHERE as_level = ? and as_status='通过' ORDER BY as_id limit ?,?";
            // 执行sql ，传入分页的起始页，每页大小
            exeQuery(sql,as_level, page.getItemStart(), page.getPageSize());
            // 获取查询结果
            ResultSet rs = (ResultSet) ps.executeQuery();
            // 如果存在结果将其封装为一个新的association对象，添加到list集合
            while (rs.next()) {
                Association association = new Association();
                association.setAs_name(rs.getString("as_name"));
                association.setAs_introduce(rs.getString("as_introduce"));
                association.setAs_number(rs.getString("as_number"));
                // 将新的association对象添加到associations集合中
                associations.add(association);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 关闭所有连接
            closeAll();
        }
        return associations;
    }

    //获取社团详细信息
    public Association getAssociationDetail(String as_name) {
        Association association = new Association();
        int as_actnumber = 0;
        try {
            // 获取连接
            getCon();

            String countsql = "SELECT count(act_id) FROM conduct,association WHERE conduct.as_id = association.as_id AND as_name = ? and as_status='通过'";
            // 执行sql
            exeQuery(countsql,as_name);
            ResultSet countResultSet = (ResultSet) ps.executeQuery();
            // 如果存在结果，将其赋值给itemCont
            if (countResultSet.next()) {
                as_actnumber = countResultSet.getInt(1);
            }
            // 要执行的sql语句
            String sql = "select as_id,as_name,as_introduce,stu_name,as_number,as_level from association,student where student.stu_id = association.stu_id and as_name=?";
            // 执行sql，并传入参数：社团名
            exeQuery(sql, as_name);
            // 获取执行结果
            ResultSet rs = (ResultSet) ps.executeQuery();
            // 如果结果存在，将其封装为新的community对象
            if (rs.next()) {
                /*
                 * 获取结果中的每一个字段
                 */
                association.setAs_name(rs.getString("as_id"));
                association.setAs_name(rs.getString("as_name"));
                association.setAs_introduce(rs.getString("as_introduce"));
                association.setAs_number(rs.getString("as_number"));
                association.setAs_level(rs.getString("as_level"));
                association.setStu_name(rs.getString("stu_name"));
                association.setAs_actnumber(String.valueOf(as_actnumber));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 关闭所有连接
            closeAll();
        }
        return association;
    }

    //搜索相关社团
    public List<Association> getAssociationForSearch(String as_name, Page page) {

        List<Association> associations = new ArrayList<Association>();
        try {
            // 获取连接
            getCon();
            // 为page分页，查询总记录数
            String countsql = "SELECT count(*) FROM association where as_status='通过' and as_name = ?";
            // 执行sql，并传入参数：模糊字段
            exeQuery(countsql, "%" + as_name + "%");
            // 获取查询结果
            ResultSet countResultSet = (ResultSet) ps.executeQuery();
            // 如果结果存在,将其赋值给itemCont
            if (countResultSet.next()) {
                int count = countResultSet.getInt(1);
                page.setItemCont(count);
            }
            // 要查询的社团详细信息
            String sql = "SELECT as_name,as_introduce,as_number FROM association where as_status='通过' and as_name LIKE ?  ORDER BY as_id limit ?,?";
            // 执行sql，并传入参数：模糊字段，查询起始页，每页大小
            exeQuery(sql, "%" + as_name + "%", page.getItemStart(), page.getPageSize());
            // 获取查询结果
            ResultSet rs = (ResultSet) ps.executeQuery();
            // 如果结果存在，将其封装为新的association对象，添加到list集合
            while (rs.next()) {
                Association association = new Association();
                association.setAs_name(rs.getString("as_name"));
                association.setAs_introduce(rs.getString("as_introduce"));
                association.setAs_number(rs.getString("as_number"));
                // 将新的association对象添加到associations集合中
                associations.add(association);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 关闭所有连接
            closeAll();
        }
        return associations;
    }
}
