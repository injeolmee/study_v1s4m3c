package dev.mvc.salereply;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.salereply.SalereplyDAO")
public class SalereplyDAO implements SalereplyDAOInter {

  @Autowired
  private SqlSessionTemplate mybatis;
  
  public SalereplyDAO() {
    // System.out.println(" => SalereplyDAO created");
  }

  /* 등록 */
  @Override
  public int create(SalereplyVO salereplyVO) {
    int count = mybatis.insert("salereply.create", salereplyVO);
    return count;
  }

  /* 목록 */
  @Override
  public List<SalereplyVO> list_reply(int saleno) {
    List<SalereplyVO> list_reply = mybatis.selectList("salereply.list_reply", saleno);
    return list_reply;
  }

  /* 대댓글 순서 변경 */
  @Override
  public int updateAnsnum(SalereplyVO salereplyVO) {
    int count = mybatis.update("salereply.updateAnsnum", salereplyVO);
    return count;
  }

  /* 대댓글 등록 */
  @Override
  public int reply(SalereplyVO salereplyVO) {
    int count = mybatis.insert("salereply.reply", salereplyVO);
    return count;
  }

  /* 댓글 조회 */
  @Override
  public SalereplyVO read(int sreplyno) {
    SalereplyVO salereplyVO = mybatis.selectOne("salereply.read", sreplyno);
    return salereplyVO;
  }

  /* 댓글 목록 + 페이징 */
  @Override
  public List<SalereplyVO> total_list_reply(HashMap<String, Object> hashMap) {
    List<SalereplyVO> total_list_reply = mybatis.selectList("salereply.total_list_reply", hashMap);
    return total_list_reply;
  }

  /* 댓글의 총 레코드 갯수 */
  @Override
  public int search_count(int saleno) {
    int count = mybatis.selectOne("salereply.search_count", saleno);
    return count;
  }

  /* 댓글 수정 */
  @Override
  public int update(SalereplyVO salereplyVO) {
    int count = mybatis.update("salereply.update", salereplyVO);
    return count;
  }

  /* 댓글 삭제 */
  @Override
  public int delete(int sreplyno) {
    int count = mybatis.delete("salereply.delete", sreplyno);
    return count;
  }

  /* 부모 댓글의 seqno를 0으로 변경*/
  @Override
  public int update_seqno(int sreplyno) {
    int count = mybatis.update("salereply.update_seqno", sreplyno);
    return count;
  }

  /* 부모 댓글에 대해 삭제 처리 대신 content를 변경해 출력 */
  @Override
  public int update_parent(int sreplyno) {
    int count = mybatis.update("salereply.update_parent", sreplyno);
    return count;
  }
  
  /* 게시글 삭제시 댓글 전체 삭제 */
  @Override
  public int delete_all(int saleno) {
    int count = mybatis.delete("salereply.delete_all", saleno);
    return count;
  }


  
}
