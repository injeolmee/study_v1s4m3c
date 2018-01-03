package dev.mvc.category;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.category.CategoryProc")
public class CategoryProc implements CategoryProcInter {
  @Autowired
  @Qualifier("dev.mvc.category.CategoryDAO") 
  private CategoryDAOInter categoryDAO;
  
  public CategoryProc() {
    // System.out.println("--> CategoryProc created");
  }
  @Override
  public int cate_create(CategoryVO categoryVO) {
    return categoryDAO.cate_create(categoryVO);
  }

  @Override
  public List<CategoryVO> cate_list(int grpno) {
    return categoryDAO.cate_list(grpno);
  }
  
  @Override
  public List<Categrp_CategoryVO> grp_cate_list() {
    List<Categrp_CategoryVO> grp_cate_list = categoryDAO.grp_cate_list();
    return grp_cate_list;
  }

  @Override
  public CategoryVO cate_read(HashMap hashMap) {
    return categoryDAO.cate_read(hashMap);
  }

  @Override
  public int cate_update(CategoryVO categoryVO) {
    return categoryDAO.cate_update(categoryVO);
  }

  @Override
  public int cate_delete(HashMap hashMap) {
    return categoryDAO.cate_delete(hashMap);
  }

  @Override
  public int cateseqno_up(HashMap hashMap) {
    return categoryDAO.cateseqno_up(hashMap);
  }

  @Override
  public int cateseqno_down(HashMap hashMap) {
    return categoryDAO.cateseqno_down(hashMap);
  }


}
