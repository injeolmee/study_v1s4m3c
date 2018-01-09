package dev.mvc.free;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.free.FreeDAO")
public class FreeDAO implements FreeDAOInter {
  
  @Autowired
  private SqlSessionTemplate mybatis; // mybatis 설정

  public FreeDAO() {
    //System.out.println(" => FreeDAO created");
  }

  /* 등록 */
  @Override
  public int create(FreeVO freeVO) {
    int count = 0;
    count = mybatis.insert("free.create", freeVO);
    return count;
  }

  /* 목록 */
  @Override
  public List<FreeVO> list() {
    List <FreeVO> list = mybatis.selectList("free.list");
    return list;
  }

  /* 조회 */
  @Override
  public FreeVO read(int freeno) {
    FreeVO freeVO = mybatis.selectOne("free.read", freeno);
    return freeVO;
  }
  
  /* 조회수 증가 */
  @Override
  public int increaseCnt(int freeno) {
    int count = mybatis.update("free.increaseCnt", freeno);
    return count;
  }
  
  /* 수정 */
  @Override
  public int update(FreeVO freeVO) {
    int count = mybatis.update("free.update", freeVO);
    return count;
  }

  /* 삭제 */
  @Override
  public int delete(int freeno) {
    int count = mybatis.delete("free.delete", freeno);
    return count;
  }

  /* 검색 */
  @Override
  public List<FreeVO> list_search(HashMap hashMap) {
    List <FreeVO> list = mybatis.selectList("free.list", hashMap);
    return list;
  }

  /* 검색 레코드 갯수 */
  @Override
  public int search_count(HashMap hashMap) {
    int count = mybatis.selectOne("free.search_count", hashMap);
    return count;
  }

  /* 추천수 최상위 3개 출력 */
  @Override
  public List<FreeVO> list_like(HashMap<String, Object> hashMap2) {
    List <FreeVO> list_like = mybatis.selectList("free.list_like", hashMap2);
    return list_like;
  }

  /* 조회수 최상위 3개 출력 */
  @Override
  public List<FreeVO> list_cnt(HashMap<String, Object> hashMap3) {
    List <FreeVO> list_cnt = mybatis.selectList("free.list_cnt", hashMap3);
    return list_cnt;
  }

  /* 이전글 조회 */
  @Override
  public int read_pre(int freeno) {
    int count = mybatis.selectOne("free.read_pre", freeno);
    return count;
  }

  /* 다음글 조회 */
  @Override
  public int read_post(int freeno) {
    int count = mybatis.selectOne("free.read_post", freeno);
    return count;
  }

  /* 아이디 검사 */
  @Override
  public int member_check(HashMap<String, Object> hashMap) {
    int count = mybatis.selectOne("free.member_check", hashMap);
    return count;
  }
  
  /* 좋아요가 0에서 1로 변경 */
  @Override
  public int increaseLike(int freeno) {
    int count = mybatis.update("free.increaseLike", freeno);
    return count;
  }
  
  /* 좋아요가 1에서 0으로 변경 */
  @Override
  public int decreaseLike(int freeno) {
    int count = mybatis.update("free.decreaseLike", freeno);
    return count;
  }

  /* 관리자 게시글 등록 */
  @Override
  public int create_admin(FreeVO freeVO) {
    return mybatis.insert("free.create_admin", freeVO);
  }

  
}
