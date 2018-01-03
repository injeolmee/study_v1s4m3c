package dev.mvc.freelike;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.freelike.FreelikeProc")
public class FreelikeProc implements FreelikeProcInter {
  
  @Autowired
  @Qualifier("dev.mvc.freelike.FreelikeDAO")
  private FreelikeDAOInter freelikeDAO;
  
  public FreelikeProc() {
    // System.out.println(" => FreelikeProc created");
  }

  /* ���ƿ� ��� */
  @Override
  public int create(HashMap hashMap) {
    int count = freelikeDAO.create(hashMap);
    return count;
  }

  /* �̹� �ԷµǾ� �ִ� ȸ�� ���� Ȯ�� */
  @Override
  public int good_chk(HashMap hashMap) {
    return freelikeDAO.good_chk(hashMap);
  }

  /* ȸ�� �� �Խñ۸��� ���ƿ�/�Ⱦ�� ���� Ȯ�� */
  @Override
  public int check(HashMap hashMap) {
    return freelikeDAO.check(hashMap);
  }

  /* ȸ���� ���ƿ並 üũ�Ѵٸ� ���ƿ� üũ���θ� 1�� �ٲ� */
  @Override
  public int good_chk_y(HashMap hashMap) {
    return freelikeDAO.good_chk_y(hashMap);
  }

  /* ȸ���� ���ƿ䰡 üũ�� ���¿��� ���ƿ並 �ٽ� üũ�Ѵٸ� ���ƿ� üũ���θ� 0�� �ٲ� */
  @Override
  public int good_chk_n(HashMap hashMap) {
    return freelikeDAO.good_chk_n(hashMap);
  }
  
}
