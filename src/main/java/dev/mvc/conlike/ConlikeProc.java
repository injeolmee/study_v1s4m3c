package dev.mvc.conlike;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.conlike.ConlikeProc")
public class ConlikeProc implements ConlikeProcInter {
  @Autowired
  @Qualifier("dev.mvc.conlike.ConlikeDAO")
  private ConlikeDAOInter conlikeDAO = null;

  @Override
  public int create(HashMap hashMap) {
    int count = conlikeDAO.create(hashMap);
    
    return count;
  }

  @Override
  public int good_chk(HashMap hashMap) {
    return conlikeDAO.good_chk(hashMap);
  }

  @Override
  public int check(HashMap hashMap) {
    return conlikeDAO.check(hashMap);
  }

  @Override
  public int good_chk_y(HashMap hashMap) {
    return conlikeDAO.good_chk_y(hashMap);
  }

  @Override
  public int good_chk_n(HashMap hashMap) {
    return conlikeDAO.good_chk_n(hashMap);
  }
  
}
