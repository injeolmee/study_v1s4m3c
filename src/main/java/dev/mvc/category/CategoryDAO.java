package dev.mvc.category;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.category.CategoryDAO")
public class CategoryDAO implements CategoryDAOInter {
  @Autowired
  private SqlSessionTemplate mybatis;
  
  public CategoryDAO() {
    // System.out.println("--> CategoryDAO created");
  }

  @Override
  public int cate_create(CategoryVO categoryVO) {
    return mybatis.insert("category.cate_create", categoryVO);
  }

  @Override
  public List<CategoryVO> cate_list(int grpno) {
    return mybatis.selectList("category.cate_list", grpno);
  }
  
  @Override
  public List<Categrp_CategoryVO> grp_cate_list() {
    return mybatis.selectList("category.grp_cate_list");
  }

  @Override
  public CategoryVO cate_read(HashMap hashMap) {
    return mybatis.selectOne("category.cate_read", hashMap);
  }

  @Override
  public int cate_update(CategoryVO categoryVO) {
    return mybatis.update("category.cate_update", categoryVO);
  }

  @Override
  public int cate_delete(HashMap hashMap) {
    return mybatis.delete("category.cate_delete", hashMap);
  }

  @Override
  public int cateseqno_up(HashMap hashMap) {
    return mybatis.update("category.cateseqno_up", hashMap);
  }

  @Override
  public int cateseqno_down(HashMap hashMap) {
    return mybatis.update("category.cateseqno_down", hashMap);
  }



}
