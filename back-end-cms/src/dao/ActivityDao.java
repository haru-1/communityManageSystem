package dao;

import bean.Activity;
import bean.Association;
import bean.Page;

import java.util.List;

public interface ActivityDao {
    //获取活动集合
    List<Activity> getActivityForPage(Page page);
    //获取分类的活动集合
    List<Activity> getActivityLevelForPage(Page page,String act_level);
    //获取活动详细信息
    Activity getActivityDetail(String act_name);
    //搜索相关活动
    List<Activity> getActivityForSearch(String act_name, Page page);


}
