package dev.mvc.message;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.message.MessageDAO")
public class MessageDAO implements MessageDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis;
  
  /**
   * 쪽지 보내기
   * @param hashMap
   * @return
   */
  @Override
  public int msg_create(HashMap<String, Object> hashMap) {
    return mybatis.insert("message.msg_create", hashMap);
  }
  
  /**
   * 회원의 유무 확인
   * @param memberno
   * @return
   */
  @Override
  public int member_count(int memberno) {
    return mybatis.selectOne("message.member_count", memberno);
  }
  
  /**
   * 쪽지 발신 정보 저장
   * @param hashMap
   * @return
   */
  @Override
  public int msgsend_insert(HashMap<String, Integer> hashMap) {
    return mybatis.insert("message.msgsend_insert", hashMap);
  }
  
  /**
   * 쪽지 수신 정보 저장
   * @param hashMap
   * @return
   */
  @Override
  public int msgrecv_insert(HashMap<String, Integer> hashMap) {
    return mybatis.insert("message.msgrecv_insert", hashMap);
  }
  
  /**
   * 가장 최근에 등록된 쪽지 번호
   */
  @Override
  public int serach_last_msg_no() {
    return mybatis.selectOne("message.serach_last_msg_no");
  }

  /**
   * 내가 받은 쪽지함
   */
  @Override
  public List<MessageVO> msg_list(HashMap<String, Object> hashMap) {
    return mybatis.selectList("message.msg_list", hashMap);
  }

  /**
   * 받은 쪽지함 삭제
   * @param msg_no
   * @return
   */
  @Override
  public int recv_del(int msg_no) {
    return mybatis.delete("message.recv_del", msg_no);
  }

  /**
   * 내가 보낸 쪽지함
   */
  @Override
  public List<MessageVO> msg_send_list(HashMap<String, Object> hashMap) {
    return mybatis.selectList("message.msg_send_list", hashMap);
  }
  
  /**
   * 쪽지 보관함 리스트
   * @param hashMap
   * @return
   */
  @Override
  public List<MessageVO> msg_repo_list(HashMap<String, Object> hashMap) {
    return mybatis.selectList("message.msg_repo_list", hashMap); 
  }
  
  /**
   * 내게 쓴 쪽지함 리스트
   * @param hashMap
   * @return
   */
  @Override
  public List<MessageVO> msg_self_list(HashMap<String, Object> hashMap) {
    return mybatis.selectList("message.msg_self_list", hashMap);
  }
  
  /**
   * 쪽지 전송
   * @param msg_no
   * @return
   */
  @Override
  public int send_del(int msg_no) {
    return mybatis.delete("message.send_del", msg_no);
  }

  /**
   * 쪽지 읽기
   * @param msg_no
   * @return
   */
  @Override
  public MessageVO read_msg(int msg_no) {
    return mybatis.selectOne("message.read_msg",msg_no); 
  }

  /**
   * 쪽지 수신 여부 확인
   * @param msg_no
   * @return
   */
  @Override
  public int confirm(int msg_no) {
    return mybatis.update("message.confirm", msg_no);
  }

  /**
   * 받은 쪽지함에서의 검색 갯수
   */
  @Override
  public int recv_search_count(HashMap<String, Object> hashMap) {
    return mybatis.selectOne("message.recv_search_count", hashMap); 
  }
  
  /**
   * 보낸 쪽지함에서의 검색 갯수
   */
  @Override
  public int send_search_count(HashMap<String, Object> hashMap) {
    return mybatis.selectOne("message.send_search_count", hashMap);
  }
  
  /**
   * 쪽지 보관함 검색 갯수
   */
  @Override
  public int repo_search_count(HashMap<String, Object> hashMap) {
    return mybatis.selectOne("message.repo_search_count", hashMap);
  }
  
  /**
   * 내게 쓴 쪽지함 검색 갯수
   * @param hashMap
   * @return
   */
  @Override
  public int self_search_count(HashMap<String, Object> hashMap) {
    return mybatis.selectOne("message.self_search_count", hashMap);
  }

  /**
   * 받은 쪽지함 총 갯수(검색과 무관)
   * @param memberno_recv
   * @return
   */
  @Override
  public int msg_recv_all_count(int memberno_recv) {
    return mybatis.selectOne("message.msg_recv_all_count", memberno_recv);
  }

  /**
   * 보낸 쪽지함 총 갯수(검색과 무관)
   * @param memberno_send
   * @return
   */
  @Override
  public int msg_send_all_count(int memberno_send) {
    return mybatis.selectOne("message.msg_send_all_count", memberno_send);
  }
  
  /**
   * 쪽지 보관함의 갯수(검색과 무관)
   * @param memberno
   * @return
   */
  @Override
  public int msg_repo_all_count(int memberno) {
    return mybatis.selectOne("message.msg_repo_all_count", memberno);
  }
  
  /**
   * 내게 쓴 쪽지함 갯수(검색과 무관)
   * @param hashMap
   * @return 
   */
  @Override
  public int msg_self_all_count(int memberno) {
    return mybatis.selectOne("message.msg_self_all_count", memberno);
  }

  /**
   * 쪽지 보관함 이동
   * @param hashMap
   * @return
   */
  @Override
  public int move_repo(HashMap<String, Object> hashMap) {
    return mybatis.insert("message.move_repo", hashMap); 
  }

  /**
   * 쪽지 보관함 쪽지 삭제.
   * @param hashMap
   * @return
   */
  @Override
  public int repo_del(HashMap<String, Object> hashMap) {
    return mybatis.delete("message.repo_del", hashMap);
  }
  
  //=======================메세지 관리자 관련 DAO=================================//
  
  /**
   * [관리자] 쪽지 리스트 조회
   * @param hashMap
   * @return
   */
  @Override
  public List<MessageVO> msg_list_admin(HashMap<String, Object> hashMap) {
    return mybatis.selectList("message_admin.msg_list_admin", hashMap); 
  }

  /**
   * [관리자] 쪽지 리스트 갯수 조회
   * @param hashMap
   * @return
   */
  @Override
  public int message_admin_search_cnt(HashMap<String, Object> hashMap) {
    return mybatis.selectOne("message_admin.message_admin_search_cnt", hashMap);
  }

  /**
   * [관리자] 쪽지 삭제
   * *원본을 지우려면 발신/수신 정보도 모두 삭제 해야한다.
   * @param msg_no
   * @return
   */
  @Override
  public int message_del_admin(int msg_no) {
    return mybatis.delete("message_admin.message_del_admin", msg_no);
  }
  
}
