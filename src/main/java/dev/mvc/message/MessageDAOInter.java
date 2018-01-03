package dev.mvc.message;

import java.util.HashMap;
import java.util.List;

public interface MessageDAOInter {
  
  //<insert id="msg_create" parameterType="MessageVO">
  /**
   * ���� ����
   * �ʿ��� �Ű�����
   * msg_title : ���� ����
   * msg : ���� ����
   * @param messageVO
   * @return ��������
   */
  public int msg_create(HashMap<String, Object> hashMap);
  
  //<select id="member_count" parameterType="int">
  /**
   * <select id="confirm_member" parameterType="int">
   * ȸ����ȣ�� ������ �ش� ȸ���� �����ϴ��� Ȯ�� �Ѵ�.
   * @param memberno
   * @return
   */
  public int member_count(int memberno);
  
  //<insert id="msgsend_insert" parameterType="HashMap">
  /**
   * <insert id="msgsend_insert" parameterType="HashMap">
   * ���� ���� ����. (���� ������ ���� ������ ����)
   * @param hashMap
   * @return int
   */
  public int msgsend_insert(HashMap<String, Integer> hashMap);
  
  //<insert id="msgrecv_insert" parameterType="HashMap">
  /**
   * <insert id="msgrecv_insert" parameterType="HashMap">
   * ���� ���� ����
   * @param hashMap
   * @return
   */
  public int msgrecv_insert(HashMap<String, Integer> hashMap);
  
  //<select id="serach_last_msg_no">
  /**
   * <select id="serach_last_msg_no">
   * ���� �ֱٿ� ��ϵ� msg_no
   * @return
   */
  public int serach_last_msg_no();
  
  //<select id="msg_list" resultType="messageVO" parameterType="HashMap">
  /**
   * <select id="msg_list" resultType="messageVO" parameterType="int">
   * ���� ���� ��������
   * @param memberno_send
   * @return List<MessageVO>
   */
  public List<MessageVO> msg_list(HashMap<String, Object> hashMap);
  
  //<delete id="recv_del" parameterType="int">
  /**
   * <delete id="recv_del" parameterType="int">
   * ���� ������ ����
   * @param msg_no
   * @return
   */
  public int recv_del(int msg_no);
  
  /**
   * <select id="msg_send_list" resultType="messageVO" parameterType="int">
   * ���� ���� ���� ����
   * @param memberno_send (�� ȸ�� ��ȣ)
   * @return
   */
  public List<MessageVO> msg_send_list(HashMap<String, Object> hashMap);
  
  
  //<select id="msg_repo_list" resultType="messageVO" parameterType="HashMap">
  /**
   * <select id="msg_repo_list" resultType="messageVO" parameterType="HashMap">
   * ���� ������ ����
   * @param hashMap
   * @return
   */
  public List<MessageVO> msg_repo_list(HashMap<String, Object> hashMap);
  
  //<select id="msg_self_list" resultType="messageVO" parameterType="HashMap"> 
  /**
   * <select id="msg_self_list" resultType="messageVO" parameterType="int">
   * ���� �� ��������
   * @return List<MessageVO>
   */
  public List<MessageVO> msg_self_list(HashMap<String, Object> hashMap);
  
  //<delete id="send_del">
  /**
   * <delete id="send_del">
   * ���� ������ ����.
   * @param msg_no
   * @return
   */
  public int send_del(int msg_no);
  
  //<select id="read_msg" parameterType="int" resultType="MessageVO">
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
   * ���� �����Կ��� ��ȸ�� ���� ����
   * </xmp>
   * @param hashMap 
   * @return
   */
  public int recv_search_count(HashMap<String, Object> hashMap);
  
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
   * <select id="msg_send_list" resultType="int" parameterType="HashMap">
   * ���� ������ �˻� ����
   * @param hashMap
   * @return 
   */
  public int send_search_count(HashMap<String, Object> hashMap);
  
  /**
   * <select id="repo_search_count" resultType="int" parameterType="HashMap">
   * ���� ������ �˻� ����
   * @param hashMap
   * @return �˻� ����
   */
  public int repo_search_count(HashMap<String, Object> hashMap);
  
  /**
   * <xmp>
   * <select id="self_search_count" resultType="int" parameterType="HashMap">
   * ���� �� ������ �˻� ����
   * </xmp>
   * @param hashMap
   * @return
   */
  public int self_search_count(HashMap<String, Object> hashMap);
  
  /**
   * <insert id="move_repo" parameterType="HashMap">
   * �����Կ� ������ �߰��Ѵ�.
   * @param hashMap
   * @return 
   */
  public int move_repo(HashMap<String, Object> hashMap);
  
  /**
   * <delete id="repo_del" parameterType="HashMap">
   * �������� ������ �����Ѵ�.
   * @param hashMap
   * @return
   */
  public int repo_del(HashMap<String, Object> hashMap);
  
  //=======================�޼��� ������ ���� �������̽�=================================//
  
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
  
  //<delete id="message_del_admin" parameterType="int">
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
