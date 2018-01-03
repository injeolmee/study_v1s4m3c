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
   * ���� ������
   * @param hashMap
   * @return
   */
  @Override
  public int msg_create(HashMap<String, Object> hashMap) {
    return mybatis.insert("message.msg_create", hashMap);
  }
  
  /**
   * ȸ���� ���� Ȯ��
   * @param memberno
   * @return
   */
  @Override
  public int member_count(int memberno) {
    return mybatis.selectOne("message.member_count", memberno);
  }
  
  /**
   * ���� �߽� ���� ����
   * @param hashMap
   * @return
   */
  @Override
  public int msgsend_insert(HashMap<String, Integer> hashMap) {
    return mybatis.insert("message.msgsend_insert", hashMap);
  }
  
  /**
   * ���� ���� ���� ����
   * @param hashMap
   * @return
   */
  @Override
  public int msgrecv_insert(HashMap<String, Integer> hashMap) {
    return mybatis.insert("message.msgrecv_insert", hashMap);
  }
  
  /**
   * ���� �ֱٿ� ��ϵ� ���� ��ȣ
   */
  @Override
  public int serach_last_msg_no() {
    return mybatis.selectOne("message.serach_last_msg_no");
  }

  /**
   * ���� ���� ������
   */
  @Override
  public List<MessageVO> msg_list(HashMap<String, Object> hashMap) {
    return mybatis.selectList("message.msg_list", hashMap);
  }

  /**
   * ���� ������ ����
   * @param msg_no
   * @return
   */
  @Override
  public int recv_del(int msg_no) {
    return mybatis.delete("message.recv_del", msg_no);
  }

  /**
   * ���� ���� ������
   */
  @Override
  public List<MessageVO> msg_send_list(HashMap<String, Object> hashMap) {
    return mybatis.selectList("message.msg_send_list", hashMap);
  }
  
  /**
   * ���� ������ ����Ʈ
   * @param hashMap
   * @return
   */
  @Override
  public List<MessageVO> msg_repo_list(HashMap<String, Object> hashMap) {
    return mybatis.selectList("message.msg_repo_list", hashMap); 
  }
  
  /**
   * ���� �� ������ ����Ʈ
   * @param hashMap
   * @return
   */
  @Override
  public List<MessageVO> msg_self_list(HashMap<String, Object> hashMap) {
    return mybatis.selectList("message.msg_self_list", hashMap);
  }
  
  /**
   * ���� ����
   * @param msg_no
   * @return
   */
  @Override
  public int send_del(int msg_no) {
    return mybatis.delete("message.send_del", msg_no);
  }

  /**
   * ���� �б�
   * @param msg_no
   * @return
   */
  @Override
  public MessageVO read_msg(int msg_no) {
    return mybatis.selectOne("message.read_msg",msg_no); 
  }

  /**
   * ���� ���� ���� Ȯ��
   * @param msg_no
   * @return
   */
  @Override
  public int confirm(int msg_no) {
    return mybatis.update("message.confirm", msg_no);
  }

  /**
   * ���� �����Կ����� �˻� ����
   */
  @Override
  public int recv_search_count(HashMap<String, Object> hashMap) {
    return mybatis.selectOne("message.recv_search_count", hashMap); 
  }
  
  /**
   * ���� �����Կ����� �˻� ����
   */
  @Override
  public int send_search_count(HashMap<String, Object> hashMap) {
    return mybatis.selectOne("message.send_search_count", hashMap);
  }
  
  /**
   * ���� ������ �˻� ����
   */
  @Override
  public int repo_search_count(HashMap<String, Object> hashMap) {
    return mybatis.selectOne("message.repo_search_count", hashMap);
  }
  
  /**
   * ���� �� ������ �˻� ����
   * @param hashMap
   * @return
   */
  @Override
  public int self_search_count(HashMap<String, Object> hashMap) {
    return mybatis.selectOne("message.self_search_count", hashMap);
  }

  /**
   * ���� ������ �� ����(�˻��� ����)
   * @param memberno_recv
   * @return
   */
  @Override
  public int msg_recv_all_count(int memberno_recv) {
    return mybatis.selectOne("message.msg_recv_all_count", memberno_recv);
  }

  /**
   * ���� ������ �� ����(�˻��� ����)
   * @param memberno_send
   * @return
   */
  @Override
  public int msg_send_all_count(int memberno_send) {
    return mybatis.selectOne("message.msg_send_all_count", memberno_send);
  }
  
  /**
   * ���� �������� ����(�˻��� ����)
   * @param memberno
   * @return
   */
  @Override
  public int msg_repo_all_count(int memberno) {
    return mybatis.selectOne("message.msg_repo_all_count", memberno);
  }
  
  /**
   * ���� �� ������ ����(�˻��� ����)
   * @param hashMap
   * @return 
   */
  @Override
  public int msg_self_all_count(int memberno) {
    return mybatis.selectOne("message.msg_self_all_count", memberno);
  }

  /**
   * ���� ������ �̵�
   * @param hashMap
   * @return
   */
  @Override
  public int move_repo(HashMap<String, Object> hashMap) {
    return mybatis.insert("message.move_repo", hashMap); 
  }

  /**
   * ���� ������ ���� ����.
   * @param hashMap
   * @return
   */
  @Override
  public int repo_del(HashMap<String, Object> hashMap) {
    return mybatis.delete("message.repo_del", hashMap);
  }
  
  //=======================�޼��� ������ ���� DAO=================================//
  
  /**
   * [������] ���� ����Ʈ ��ȸ
   * @param hashMap
   * @return
   */
  @Override
  public List<MessageVO> msg_list_admin(HashMap<String, Object> hashMap) {
    return mybatis.selectList("message_admin.msg_list_admin", hashMap); 
  }

  /**
   * [������] ���� ����Ʈ ���� ��ȸ
   * @param hashMap
   * @return
   */
  @Override
  public int message_admin_search_cnt(HashMap<String, Object> hashMap) {
    return mybatis.selectOne("message_admin.message_admin_search_cnt", hashMap);
  }

  /**
   * [������] ���� ����
   * *������ ������� �߽�/���� ������ ��� ���� �ؾ��Ѵ�.
   * @param msg_no
   * @return
   */
  @Override
  public int message_del_admin(int msg_no) {
    return mybatis.delete("message_admin.message_del_admin", msg_no);
  }
  
}
