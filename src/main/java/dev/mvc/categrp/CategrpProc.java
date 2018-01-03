package dev.mvc.categrp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.categrp.CategrpProc")
public class CategrpProc implements CategrpProcInter {
  @Autowired
  @Qualifier("dev.mvc.categrp.CategrpDAO") 
  private CategrpDAO categrpDAO;
  
  public CategrpProc() {
    // System.out.println("--> CategrpProc created");
  }
  @Override
  public int grp_create(CategrpVO categrpVO) {
    return categrpDAO.grp_create(categrpVO);
  }

  @Override
  public List<CategrpVO> grp_list() {
    return categrpDAO.grp_list();
  }

  @Override
  public CategrpVO grp_read(int grpno) {
    return categrpDAO.grp_read(grpno);
  }

  @Override
  public int grp_update(CategrpVO categrpVO) {
    return categrpDAO.grp_update(categrpVO);
  }

  @Override
  public int grp_delete(int categrpVO) {
    return categrpDAO.grp_delete(categrpVO);
  }

  @Override
  public int grpseqno_up(int grpno) {
    return categrpDAO.grpseqno_up(grpno);
  }

  @Override
  public int grpseqno_down(int grpno) {
    return categrpDAO.grpseqno_down(grpno);
  }
  @Override
  public int grpno_check(int grpno) {
    return categrpDAO.grpno_check(grpno);
  }

}
