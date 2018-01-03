package dev.mvc.message;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("dev.mvc.message.MessageDAO")
public interface MessageProcInter {
  /**
   * ���� ����
   * �ʿ��� �Ű�����
   * msg_title : ���� ����
   * msg : ���� ����
   * @param messageVO
   * @return �������� 
   */ 
  public int msg_create(int memberno_send, int memberno_recv, String msg_title, String msg_content);
  
  /**
   * <select id="confirm_member" parameterType="int">
   * ȸ����ȣ�� ������ �ش� ȸ���� �����ϴ��� Ȯ�� �Ѵ�.
   * @param memberno
   * @return
   */
  public int member_count(int memberno);
  
  
  /**
   * ���� ���� ����
   * �Ű������� ���� �޾� hashMap�� �����ѵ�
   * DAO�� ���� mybatis�� ȣ�� �Ѵ�.
   * @param memberno_send
   * @param msg_no
   * @return
   */
  public int msgsend_insert(int memberno_send, int msg_no);
  
  /**
   * <insert id="mrecv_insert" parameterType="HashMap">
   * �Ű������� ���޹޾� hashMap�� ������ �� DAO�� ���� mybatis ȣ��.
   * @param memberno_recv
   * @param msg_no
   * @return
   */
  public int msgrecv_insert(int memberno_recv, int msg_no);
  
  /**
   * <select id="serach_last_msg_no">
   * ���� �ֱٿ� ��ϵ� msg_no
   * @return
   */
  public int serach_last_msg_no();
  
  /**
   * <select id="msg_list" resultType="messageVO" parameterType="HashMap">
   * ���� ���� ��������
   * @param memberno_send
   * @return List<MessageVO>
   */
  public List<MessageVO> msg_list(int memberno_recv, String search_condition, String msgword, int nowpage);
  
  /**
   * <delete id="recv_del" parameterType="int">
   * ���� ������ ����
   * @param msg_no
   * @return
   */
  public int recv_del(int msg_no);
  
  /**
   * <select id="msg_send_list" resultType="messageVO" parameterType="HashMap">
   * ���� ���� ���� ���� 
   * @param memberno_send (�� ȸ�� ��ȣ)
   * @return
   */
  public List<MessageVO> msg_send_list(int memberno_send, String search_condition, String msgword, int nowpage);
  
  /**
   * <delete id="send_del">
   * ���� ������ ����.
   * @param msg_no
   * @return
   */
  public int send_del(int msg_no);
  
  /**
   * <select id="msg_repo_list" resultType="messageVO" parameterType="HashMap">
   * ���� ������ ����
   * @param memberno
   * @param search_condition
   * @param msgword
   * @return
   */
  public List<MessageVO> msg_repo_list(int memberno, String search_condition, String msgword, int nowpage);
  
  
  /**
   * <select id="msg_self_list" resultType="messageVO" parameterType="int">
   * ���� �� ��������
   * @return List<MessageVO>
   */
  public List<MessageVO> msg_self_list(int memberno, String search_condition, String msgword, int nowpage);
  
  /**
   * <select id="read_msg" parameterType="int" resultType="MessageVO">
   * ���� �б�
   * @param msg_no
   * @return
   */
  public MessageVO read_msg(int msg_no);
  
  /**
   * <update id="confirm" parameterType="int">
   * ���� �б� �� ���ſ���, ���Žð� ����
   * @param msg_no
   * @return 
   */
  public int confirm(int msg_no);
  
  /**
   * <xmp>
   * <select id="recv_search_count" resultType="int" parameterType="HashMap">
   * ���� ������ �˻��� ���� ����
   * </xmp>
   * @param hashMap 
   * @return
   */
  public int recv_search_count(int memberno_recv, String search_condition, String msgword);
  
  /**
   * <xmp>
   * <select id="msg_send_list" resultType="int" parameterType="HashMap">
   * ���� ������ �˻��� ���� ����
   * </xmp>
   * @param memberno_send
   * @param search_condition
   * @param msgword
   * @return
   */
  public int send_search_count(int memberno_send, String search_condition, String msgword);
  
  /**
   * <select id="repo_search_count" resultType="int" parameterType="HashMap">
   * ���� ������ �˻� ����
   * @param hashMap
   * @return �˻� ����
   */
  public int repo_search_count(int memberno, String search_condition, String msgword);
  
  /**
   * <xmp>
   * <select id="self_search_count" resultType="int" parameterType="HashMap">
   * ���� �� ������ �˻� ����
   * </xmp>
   * @param hashMap
   * @return
   */
  public int self_search_count(int memberno_recv, String search_condition, String msgword);
  
  /** 
   * <xmp>
   * <select id="msg_recv_all_count" resultType="int" parameterType="int">
   * ���� ������ ��� ����
   * </xmp>
   * @param memberno_recv
   * @return 
   */
  public int msg_recv_all_count(int memberno_recv);
  
  /**
   * <xmp>
   * <select id="msg_send_all_count" resultType="int" parameterType="int">
   * ���� ������ ��� ����
   * </xmp>
   * @param memberno_send
   * @return
   */
  public int msg_send_all_count(int memberno_send);
  
  /**
   * <select id="msg_repo_all_count" resultType="int" parameterType="int">
   * ���� ������ ��� ����
   * @param memberno
   * @return
   */
  public int msg_repo_all_count(int memberno);
  
  /**
   * <xmp>
   * <select id="msg_self_all_count" resultType="int" parameterType="int">
   * ���� �� ������ ��� ����
   * </xmp>
   * @param hashMap 
   * @return 
   */
  public int msg_self_all_count(int memberno);
  
  /**
   * <insert id="move_repo" parameterType="HashMap">
   * �����Կ� ������ �߰��Ѵ�.
   * @param hashMap
   * @return 
   */
  public int move_repo(int memberno, int msg_no);
   
  /**
   * ���� �������� ������ �����Ѵ�.
   * @param msg_no
   * @return
   */
  public int repo_del(int msg_no);
  
  /**
   * ������ ��� ����
   * @param search_count
   * @param nowPage
   * @param index
   * @return
   */
  public String paging(int search_count, int nowPage, int index);
  
  //=======================�޼��� ������ ���� Proc Interface=================================//
  
  /**
   * <select id="msg_list_admin" resultType="MessageVO">
   * ������ ���� ���� ����Ʈ ��� ��ȸ 
   * @param hashMap
   * @return
   */
  public List<MessageVO> msg_list_admin(HashMap<String, Object> hashMap);
  
  /**
   * <xmp>
   * <select id="message_admin_search_cnt" parameterType="HashMap" resultType="int">
   * ������ ���� ���� - �˻��� ��� ����
   * @param hashMap
   * </xmp>
   * @return
   */
  public int message_admin_search_cnt(HashMap<String, Object> hashMap);
  
  /**
   * <xmp>
   * ������ �������� ����¡ �޼ҵ�
   * </xmp>
   * @param search_count
   * @param nowPage
   * @return ������ HTML �ҽ�
   */
  public String paging_admin(int page_num, int search_count, int nowPage, String first_day, String second_day, String search_condition, String msgword);
  
  /** 
   * <xmp>
   * <delete id="message_del_admin" parameterType="int">
   * �����ڿ� ���� ���� �޼ҵ�
   * </xmp>
   * @param msg_no
   * @return
   */
  public int message_del_admin(int msg_no);
}
