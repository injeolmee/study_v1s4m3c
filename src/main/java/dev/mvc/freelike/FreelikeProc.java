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

  /* 좋아요 등록 */
  @Override
  public int create(HashMap hashMap) {
    int count = freelikeDAO.create(hashMap);
    return count;
  }

  /* 이미 입력되어 있는 회원 인지 확인 */
  @Override
  public int good_chk(HashMap hashMap) {
    return freelikeDAO.good_chk(hashMap);
  }

  /* 회원 및 게시글마다 좋아요/싫어요 여부 확인 */
  @Override
  public int check(HashMap hashMap) {
    return freelikeDAO.check(hashMap);
  }

  /* 회원이 좋아요를 체크한다면 좋아요 체크여부를 1로 바꿈 */
  @Override
  public int good_chk_y(HashMap hashMap) {
    return freelikeDAO.good_chk_y(hashMap);
  }

  /* 회원이 좋아요가 체크된 상태에서 좋아요를 다시 체크한다면 좋아요 체크여부를 0로 바꿈 */
  @Override
  public int good_chk_n(HashMap hashMap) {
    return freelikeDAO.good_chk_n(hashMap);
  }
  
}
