package dev.mvc.sharedreply;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.sharedreply.SharedreplyDAO")
public class SharedreplyDAO implements SharedreplyDAOInter  {
  
  @Autowired
  SqlSessionTemplate mybatis;
  
  public SharedreplyDAO() {
    // System.out.println(" => SharedreplyDAO created");
  }

  /* 댓글 등록 */
  @Override
  public int create(SharedreplyVO sharedreplyVO) {
    int count = mybatis.insert("sharedreply.create", sharedreplyVO);
    return count;
  }

  /* 대댓글 순서 변경 */
  @Override
  public int updateAnsnum(SharedreplyVO sharedreplyVO) {
    int count = mybatis.update("sharedreply.updateAnsnum", sharedreplyVO);
    return count;
  }

  /* 대댓글 등록 */
  @Override
  public int reply(SharedreplyVO sharedreplyVO) {
    int count = mybatis.insert("sharedreply.reply", sharedreplyVO);
    return count;
  }

  /* 특정 댓글 조회 */
  @Override
  public SharedreplyVO read(int shreplyno) {
    SharedreplyVO sharedreplyVO = mybatis.selectOne("sharedreply.read", shreplyno);
    return sharedreplyVO;
  }

  /* 댓글 목록 + 페이징 */
  @Override
  public List<SharedreplyVO> total_list_reply(HashMap<String, Object> hashMap) {
    List<SharedreplyVO> total_list_reply = mybatis.selectList("sharedreply.total_list_reply", hashMap);
    return total_list_reply;
  }

  /* 댓글 총 레코드 갯수 산출 */
  @Override
  public int search_count(int sharedno) {
    int count = mybatis.selectOne("sharedreply.search_count", sharedno);
    return count;
  }

  /* 댓글 수정 */
  @Override
  public int update(SharedreplyVO sharedreplyVO) {
    int count = mybatis.update("sharedreply.update", sharedreplyVO);
    return count;
  }

  /* 댓글 삭제 */
  @Override
  public int delete(int shreplyno) {
    int count = mybatis.delete("sharedreply.delete", shreplyno);
    return count;
  }

  /* 부모 댓글의 seqno를 0으로 변경*/ 
  @Override
  public int update_seqno(int shreplyno) {
    int count = mybatis.update("sharedreply.update_seqno", shreplyno);
    return count;
  }

  /* 부모 댓글에 대해 삭제 처리 대신 content를 변경해 출력 */
  @Override
  public int update_parent(int shreplyno) {
    int count = mybatis.update("sharedreply.update_parent", shreplyno);
    return count;
  }

  /* 게시글 삭제시 댓글 전체 삭제 */
  @Override
  public int delete_all(int sharedno) {
    int count = mybatis.delete("sharedreply.delete_all", sharedno);
    return count;
  }
  
}
