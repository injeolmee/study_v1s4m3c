package dev.mvc.shared;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.shared.SharedDAO")
public class SharedDAO implements SharedDAOInter {

  @Autowired
  private SqlSessionTemplate mybatis;
  
  public SharedDAO() {
    // System.out.println(" => SharedDAO created");
  }
  
  /* 등록 */
  @Override
  public int create(SharedVO sharedVO) {
    int count = mybatis.insert("shared.create", sharedVO);
    return count;
  }

  /* 목록 */
  @Override
  public List<SharedVO> list() {
    List <SharedVO> list = mybatis.selectList("shared.list");
    return list;
  }

  /* 검색 */
  @Override
  public List<SharedVO> list_search(HashMap hashMap) {
    List <SharedVO> list = mybatis.selectList("shared.list_search", hashMap);
    return list;
  }

  /* 검색 레코드 갯수 */
  @Override
  public int search_count(HashMap hashMap) {
    int count = mybatis.selectOne("shared.search_count", hashMap);
    return count;
  }

  /* 조회 */
  @Override
  public SharedVO read(int sharedno) {
    SharedVO sharedVO = mybatis.selectOne("shared.read", sharedno);
    return sharedVO;
  }

  /* 조회수 상승 */
  @Override
  public int increaseCnt(int sharedno) {
    int count = mybatis.update("shared.increaseCnt", sharedno);
    return count;
  }

  /* 추천수 상승*/
  @Override
  public int increaseLike(int sharedno) {
    int count = mybatis.update("shared.increaseLike", sharedno);
    return count;
  }

  /* 수정 */
  @Override
  public int update(SharedVO sharedVO) {
    int count = mybatis.update("shared.update", sharedVO);
    return count;
  }

  /* 패스워드 검사 */
  @Override
  public int passwd_check(HashMap<String, Object>  hashMap) {
    int count = mybatis.selectOne("shared.passwd_check", hashMap);
    return count;
  }

  /* 삭제 */
  @Override
  public int delete(int sharedno) {
    int count = mybatis.delete("shared.delete", sharedno);
    return count;
  }

  /* 이전글 조회 */
  @Override
  public int read_pre(int sharedno) {
    int count = mybatis.selectOne("shared.read_pre", sharedno);
    return count;
  }

  /* 다음글 조회 */
  @Override
  public int read_post(int sharedno) {
    int count = mybatis.selectOne("shared.read_post", sharedno);
    return count;
  }

  /* 아이디 검사 */
  @Override
  public int member_check(HashMap<String, Object> hashMap) {
    int count = mybatis.selectOne("shared.member_check", hashMap);
    return count;
  }

}
