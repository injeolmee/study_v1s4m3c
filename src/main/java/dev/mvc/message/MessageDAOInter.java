package dev.mvc.message;

import java.util.HashMap;
import java.util.List;

public interface MessageDAOInter {
  
  //<insert id="msg_create" parameterType="MessageVO">
  /**
   * 쪽지 전송
   * 필요한 매개변수
   * msg_title : 쪽지 제목
   * msg : 쪽지 내용
   * @param messageVO
   * @return 성공여부
   */
  public int msg_create(HashMap<String, Object> hashMap);
  
  //<select id="member_count" parameterType="int">
  /**
   * <select id="confirm_member" parameterType="int">
   * 회원번호를 가지고 해당 회원이 존재하는지 확인 한다.
   * @param memberno
   * @return
   */
  public int member_count(int memberno);
  
  //<insert id="msgsend_insert" parameterType="HashMap">
  /**
   * <insert id="msgsend_insert" parameterType="HashMap">
   * 전송 내역 저장. (내가 전송한 쪽지 내역을 위해)
   * @param hashMap
   * @return int
   */
  public int msgsend_insert(HashMap<String, Integer> hashMap);
  
  //<insert id="msgrecv_insert" parameterType="HashMap">
  /**
   * <insert id="msgrecv_insert" parameterType="HashMap">
   * 수신 내역 저장
   * @param hashMap
   * @return
   */
  public int msgrecv_insert(HashMap<String, Integer> hashMap);
  
  //<select id="serach_last_msg_no">
  /**
   * <select id="serach_last_msg_no">
   * 가장 최근에 등록된 msg_no
   * @return
   */
  public int serach_last_msg_no();
  
  //<select id="msg_list" resultType="messageVO" parameterType="HashMap">
  /**
   * <select id="msg_list" resultType="messageVO" parameterType="int">
   * 내가 받은 쪽지정보
   * @param memberno_send
   * @return List<MessageVO>
   */
  public List<MessageVO> msg_list(HashMap<String, Object> hashMap);
  
  //<delete id="recv_del" parameterType="int">
  /**
   * <delete id="recv_del" parameterType="int">
   * 받은 쪽지함 제거
   * @param msg_no
   * @return
   */
  public int recv_del(int msg_no);
  
  /**
   * <select id="msg_send_list" resultType="messageVO" parameterType="int">
   * 내가 받은 쪽지 정보
   * @param memberno_send (내 회원 번호)
   * @return
   */
  public List<MessageVO> msg_send_list(HashMap<String, Object> hashMap);
  
  
  //<select id="msg_repo_list" resultType="messageVO" parameterType="HashMap">
  /**
   * <select id="msg_repo_list" resultType="messageVO" parameterType="HashMap">
   * 쪽지 보관함 정보
   * @param hashMap
   * @return
   */
  public List<MessageVO> msg_repo_list(HashMap<String, Object> hashMap);
  
  //<select id="msg_self_list" resultType="messageVO" parameterType="HashMap"> 
  /**
   * <select id="msg_self_list" resultType="messageVO" parameterType="int">
   * 내게 쓴 쪽지정보
   * @return List<MessageVO>
   */
  public List<MessageVO> msg_self_list(HashMap<String, Object> hashMap);
  
  //<delete id="send_del">
  /**
   * <delete id="send_del">
   * 보낸 쪽지함 제거.
   * @param msg_no
   * @return
   */
  public int send_del(int msg_no);
  
  //<select id="read_msg" parameterType="int" resultType="MessageVO">
  /**
   * <select id="read_msg" parameterType="int" resultType="MessageVO">
   * 쪽지 읽기
   * @param msg_no
   * @return
   */
  public MessageVO read_msg(int msg_no);
  
  /**
   * <update id="confirm" parameterType="int">
   * 쪽지 읽기 후 수신여부, 수신시간 변경
   * @param msg_no
   * @return
   */
  public int confirm(int msg_no);
  
  /**
   * <xmp>
   * <select id="recv_search_count" resultType="int" parameterType="HashMap">
   * 보낸 쪽지함에서 조회된 쪽지 갯수
   * </xmp>
   * @param hashMap 
   * @return
   */
  public int recv_search_count(HashMap<String, Object> hashMap);
  
  /**
   * <xmp>
   * <select id="msg_recv_all_count" resultType="int" parameterType="int">
   * 받은 쪽지함 모든 갯수
   * </xmp>
   * @param memberno_recv
   * @return
   */
  public int msg_recv_all_count(int memberno_recv);
  
  /**
   * <xmp>
   * <select id="msg_send_all_count" resultType="int" parameterType="int">
   * 보낸 쪽지함 모든 갯수
   * </xmp>
   * @param memberno_send
   * @return
   */
  public int msg_send_all_count(int memberno_send);
  
  /**
   * <select id="msg_repo_all_count" resultType="int" parameterType="int">
   * 쪽지 보관함 모든 갯수
   * @param memberno
   * @return
   */
  public int msg_repo_all_count(int memberno);
  
  /**
   * <xmp>
   * <select id="msg_self_all_count" resultType="int" parameterType="int">
   * 내게 쓴 쪽지함 모든 갯수
   * </xmp>
   * @param hashMap 
   * @return 
   */
  public int msg_self_all_count(int memberno);
  
  /**
   * <select id="msg_send_list" resultType="int" parameterType="HashMap">
   * 보낸 쪽지함 검색 갯수
   * @param hashMap
   * @return 
   */
  public int send_search_count(HashMap<String, Object> hashMap);
  
  /**
   * <select id="repo_search_count" resultType="int" parameterType="HashMap">
   * 쪽지 보관함 검색 갯수
   * @param hashMap
   * @return 검색 갯수
   */
  public int repo_search_count(HashMap<String, Object> hashMap);
  
  /**
   * <xmp>
   * <select id="self_search_count" resultType="int" parameterType="HashMap">
   * 내게 쓴 쪽지함 검색 갯수
   * </xmp>
   * @param hashMap
   * @return
   */
  public int self_search_count(HashMap<String, Object> hashMap);
  
  /**
   * <insert id="move_repo" parameterType="HashMap">
   * 보관함에 쪽지를 추가한다.
   * @param hashMap
   * @return 
   */
  public int move_repo(HashMap<String, Object> hashMap);
  
  /**
   * <delete id="repo_del" parameterType="HashMap">
   * 보관함의 쪽지를 삭제한다.
   * @param hashMap
   * @return
   */
  public int repo_del(HashMap<String, Object> hashMap);
  
  //=======================메세지 관리자 관련 인터페이스=================================//
  
  /**
   * <select id="msg_list_admin" resultType="MessageVO">
   * 관리자 전용 쪽지 리스트 목록 조회 
   * @param hashMap
   * @return
   */
  public List<MessageVO> msg_list_admin(HashMap<String, Object> hashMap);
  
  /**
   * <xmp>
   * <select id="message_admin_search_cnt" parameterType="HashMap" resultType="int">
   * 관리자 전용 쪽지 - 검색한 결과 갯수
   * @param hashMap
   * </xmp>
   * @return
   */
  public int message_admin_search_cnt(HashMap<String, Object> hashMap);
  
  //<delete id="message_del_admin" parameterType="int">
  /**
   * <xmp>
   * <delete id="message_del_admin" parameterType="int">
   * 관리자용 쪽지 삭제 메소드
   * </xmp>
   * @param msg_no
   * @return
   */
  public int message_del_admin(int msg_no);
}
