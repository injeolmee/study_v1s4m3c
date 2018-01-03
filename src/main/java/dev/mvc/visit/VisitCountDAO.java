package dev.mvc.visit;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component//("visitCountDAO")//"dev.mvc.visit.VisitCountDAO")
public class VisitCountDAO {
  @Autowired(required=false)
  private SqlSessionTemplate mybatis;

  
  public void setMybatis(SqlSessionTemplate mybatis) {
    this.mybatis = mybatis;
    // System.out.println(getClass().getName()+"=====>"+this.mybatis);
  }

  public VisitCountDAO() {
    // System.out.println("visitcountdao");
  }
  
  
  public int setVisitTotalCount(){
    // System.out.println("=====>"+mybatis);
    return mybatis.insert("visit.setVisitTotalCount");
  }
  
  public int getVisitTodayCount(){
    return mybatis.selectOne("visit.getVisitTodayCount");
  }
  
  public int getVisitTotalCount(){
    return mybatis.selectOne("visit.getVisitTotalCount");
  }

  

}
