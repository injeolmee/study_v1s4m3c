package dev.mvc.categrp;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.categrp.CategrpDAO")
public class CategrpDAO implements CategrpDAOInter {
  @Autowired
  private SqlSessionTemplate mybatis;
  
  public CategrpDAO() {
    // System.out.println("--> CategrpDAO created");
  }

  @Override
  public int grp_create(CategrpVO categrpVO) {
    return mybatis.insert("categrp.grp_create", categrpVO);
  }

  @Override
  public List<CategrpVO> grp_list() {
    return mybatis.selectList("categrp.grp_list");
  }

  @Override
  public CategrpVO grp_read(int grpno) {
    return mybatis.selectOne("categrp.grp_read", grpno);
  }

  @Override
  public int grp_update(CategrpVO categrpVO) {
    return mybatis.update("categrp.grp_update", categrpVO);
  }

  @Override
  public int grp_delete(int grpno) {
    return mybatis.delete("categrp.grp_delete", grpno);
  }

  @Override
  public int grpseqno_up(int grpno) {
    return mybatis.update("categrp.grpseqno_up", grpno);
  }

  @Override
  public int grpseqno_down(int grpno) {
    return mybatis.update("categrp.grpseqno_down", grpno);
  }

  @Override
  public int grpno_check(int grpno) {
    return mybatis.selectOne("categrp.grpno_check", grpno);
  }

}
