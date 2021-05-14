package dao;

import bean.Association;
import bean.Page;

import java.util.List;

public interface AssociationDao {
    //获取社团集合
    List<Association> getAssociationForPage(Page page);
    //获取分类的社团集合
    List<Association> getAssociationLevelForPage(Page page,String as_level);
    //获取社团详细信息
    Association getAssociationDetail(String as_name);
    //搜索相关社团
    List<Association> getAssociationForSearch(String as_name, Page page);

}
