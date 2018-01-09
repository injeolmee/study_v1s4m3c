package dev.mvc.rvlike;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.rvlike.RvlikeProc")
public class RVlikeProc implements RvlikeProcInter {
  @Autowired
  @Qualifier("dev.mvc.rvlike.RvlikeDAO")
  private RvlikeDAOInter rvlikeDAO = null;

  @Override
  public int create(HashMap hashMap) {
    int count = rvlikeDAO.create(hashMap);
    
    return count;
  }

  @Override
  public int like_chk(HashMap hashMap) {
    return rvlikeDAO.like_chk(hashMap);
  }

  @Override
  public int check(HashMap hashMap) {
    return rvlikeDAO.check(hashMap);
  }

  @Override
  public int like_chk_y(HashMap hashMap) {
    return rvlikeDAO.like_chk_y(hashMap);
  }

  @Override
  public int like_chk_n(HashMap hashMap) {
    return rvlikeDAO.like_chk_n(hashMap);
  }
  
}
