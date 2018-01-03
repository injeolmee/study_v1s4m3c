package dev.mvc.freelike;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.freelike.FreelikeDAO")
public class FreelikeDAO implements FreelikeDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis;
  
  public FreelikeDAO() {
    // System.out.println("---> FreelikeDAO created.");
  }
  
  /* ���ƿ� ��� */
  @Override
  public int create(HashMap hashMap) {
    int count = mybatis.insert("freelike.create", hashMap);
    return count;
  }

  /* �̹� �ԷµǾ� �ִ� ȸ�� ���� Ȯ�� */
  @Override
  public int good_chk(HashMap hashMap) {
    return mybatis.selectOne("freelike.good_chk", hashMap);
  }

  /* ȸ������ , ���͵�׷츶�� ���ƿ� �Ⱦ�� ���� Ȯ�� */
  @Override
  public int check(HashMap hashMap) {
    return mybatis.selectOne("freelike.check", hashMap);
  }

  /*  ȸ���� ���ƿ並 üũ�Ѵٸ� ���ƿ� üũ���θ� 1�� �ٲ� */
  @Override
  public int good_chk_y(HashMap hashMap) {
    return mybatis.update("freelike.good_chk_y", hashMap);
  }

  /* ȸ���� ���ƿ䰡 üũ�� ���¿��� ���ƿ並 �ٽ� üũ�Ѵٸ� ���ƿ� üũ���θ� 0�� �ٲ� */
  @Override
  public int good_chk_n(HashMap hashMap) {
    return mybatis.update("freelike.good_chk_n", hashMap);
  }
  
}
