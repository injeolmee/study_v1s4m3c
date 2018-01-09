package dev.mvc.std_recom;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.std_recom.Std_RecomProc")
public class Std_RecomProc implements Std_RecomProcInter {

  @Autowired
  @Qualifier("dev.mvc.std_recom.Std_RecomDAO")
  private Std_RecomDAOInter std_recomDAO = null;

  public Std_RecomProc() {
    // System.out.println("--> std_recomProc created.");
  }

  @Override
  public int create(HashMap Hashmap) {

    return std_recomDAO.create(Hashmap);
  }

  @Override
  public int std_recom_check(HashMap Hashmap) {

    return std_recomDAO.std_recom_check(Hashmap);
  }

  @Override
  public int check(HashMap Hashmap) {

    return std_recomDAO.check(Hashmap);
  }

  @Override
  public int good_ch_Y(HashMap Hashmap) {

    return std_recomDAO.good_ch_Y(Hashmap);
  }

  @Override
  public int good_ch_N(HashMap Hashmap) {

    return std_recomDAO.good_ch_N(Hashmap);
  }

  @Override
  public int delete(int stdlist_no) {
    
    return std_recomDAO.delete(stdlist_no);
  }

}
