package dev.mvc.my_std_catelist;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.my_std_catelist.My_std_catelistProc")
public class My_std_catelistProc implements My_std_catelistProcInter{

  @Autowired
  @Qualifier("dev.mvc.my_std_catelist.My_std_catelistDAO")
  private My_std_catelistDAOInter my_std_catelistDAO;
  
  @Override
  public int insert(HashMap<String, Integer> hashMap) {
    return my_std_catelistDAO.insert(hashMap);
  }
  
  @Override
  public int delete(int stdlist_no) {
    return my_std_catelistDAO.delete(stdlist_no);
  }
  
}