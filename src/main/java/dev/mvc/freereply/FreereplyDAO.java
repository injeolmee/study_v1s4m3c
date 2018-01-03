package dev.mvc.freereply;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.freereply.FreereplyDAO")
public class FreereplyDAO implements FreereplyDAOInter {
  
  @Autowired
  SqlSessionTemplate mybatis;
  
  public FreereplyDAO() {
    // System.out.println(" => FreereplyDAO created");
  }

  /* 댓글 등록 */
  @Override
  public int create(FreereplyVO freereplyVO) {
    int count = mybatis.insert("freereply.create", freereplyVO);
    return count;
  }

  /* 대댓글 순서 변경 */
  @Override
  public int updateAnsnum(FreereplyVO freereplyVO) {
    int count = mybatis.update("freereply.updateAnsnum", freereplyVO);
    return count;
  }

  /* 대댓글 등록 */
  @Override
  public int reply(FreereplyVO freereplyVO) {
    int count = mybatis.insert("freereply.reply", freereplyVO);
    return count;
  }

  /* 특정 댓글 조회 */
  @Override
  public FreereplyVO read(int freeno) {
    FreereplyVO freereplyVO = mybatis.selectOne("freereply.read", freeno);
    return freereplyVO;
  }

  /* 댓글 목록 + 페이징 */
  @Override
  public List<FreereplyVO> total_list_reply(HashMap<String, Object> hashMap) {
    List<FreereplyVO> total_list_reply = mybatis.selectList("freereply.total_list_reply", hashMap);
    return total_list_reply;
  }

  /* 댓글 총 레코드 갯수 산출 */
  @Override
  public int search_count(int freeno) {
    int count = mybatis.selectOne("freereply.search_count", freeno);
    return count;
  }

  /* 댓글 수정 */
  @Override
  public int update(FreereplyVO freereplyVO) {
    int count = mybatis.update("freereply.update", freereplyVO);
    return count;
  }

  /* 댓글 삭제 */
  @Override
  public int delete(int freplyno) {
    int count = mybatis.delete("freereply.delete", freplyno);
    return count;
  }

  /* 부모 댓글의 seqno를 0으로 변경*/
  @Override
  public int update_seqno(int freplyno) {
    int count = mybatis.update("freereply.update_seqno", freplyno);
    return count;
  }

  /* 부모 댓글에 대해 삭제 처리 대신 content를 변경해 출력 */
  @Override
  public int update_parent(int freplyno) {
    int count = mybatis.update("freereply.update_parent", freplyno);
    return count;
  }

  /* 게시글 삭제시 댓글 전체 삭제*/
  @Override
  public int delete_all(int freeno) {
    int count = mybatis.delete("freereply.delete_all", freeno);
    return count;
  }
  
  

}
