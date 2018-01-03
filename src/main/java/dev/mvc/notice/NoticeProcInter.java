package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;

public interface NoticeProcInter {
  
  /**
   * �������� ���
   * <insert id="notice_create" parameterType="NoticeVO"> 
   * @param NoticeVO
   * @return
   */
  public int notice_create(NoticeVO noticeVO);
  
  
  /**
   * <select id="notice_list" resultType="NoticeVO" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public List<NoticeVO> notice_list(HashMap hashMap);
  
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @param search �˻� �ɼ�
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String word, String search);
  
  
  /**
   * �˻��� ���ڵ� ����
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * @return
   */
  public int search_count(HashMap hashMap);  
  
  
  /**
   * �������� ��ȸ
   * <select id="notice_read" resultType="NoticeVO" parameterType="HashMap">
   * @param noticeno
   * @return
   */
  public NoticeVO notice_read(int noticeno);
  
  
  /**
   * �������� ����
   * <update id ="notice_update" parameterType="NoticeVO">
   * @param noticeVO
   * @return
   */
  public int notice_update(NoticeVO noticeVO);
  
  
  /**
   * �������� ����
   * <delete id="notice_delete" parameterType= "int">
   * @param noticeno
   * @return
   */
  public int notice_delete(int noticeno);
  
  
  /**
   * �������� �켱���� ����
   * <update id ="nseqno_up" parameterType= "int">
   * @param noticeno
   * @return
   */
  public int nseqno_up(int noticeno);
  
  
  /**
   * �������� �켱���� ����
   * <update id ="nseqno_down" parameterType= "int">
   * @param noticeno
   * @return
   */
  public int nseqno_down(int noticeno);
  
  
  /**
   * �������� ��ȸ��
   * <update id="ncnt_up" parameterType= "int">
   * @param noticeno
   * @return
   */
  public int ncnt_up(int noticeno);
  

}
