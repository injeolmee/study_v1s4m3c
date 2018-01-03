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
  
  /* 좋아요 등록 */
  @Override
  public int create(HashMap hashMap) {
    int count = mybatis.insert("freelike.create", hashMap);
    return count;
  }

  /* 이미 입력되어 있는 회원 인지 확인 */
  @Override
  public int good_chk(HashMap hashMap) {
    return mybatis.selectOne("freelike.good_chk", hashMap);
  }

  /* 회원마다 , 스터디그룹마다 좋아요 싫어요 여부 확인 */
  @Override
  public int check(HashMap hashMap) {
    return mybatis.selectOne("freelike.check", hashMap);
  }

  /*  회원이 좋아요를 체크한다면 좋아요 체크여부를 1로 바꿈 */
  @Override
  public int good_chk_y(HashMap hashMap) {
    return mybatis.update("freelike.good_chk_y", hashMap);
  }

  /* 회원이 좋아요가 체크된 상태에서 좋아요를 다시 체크한다면 좋아요 체크여부를 0로 바꿈 */
  @Override
  public int good_chk_n(HashMap hashMap) {
    return mybatis.update("freelike.good_chk_n", hashMap);
  }
  
}
