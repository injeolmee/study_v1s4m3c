package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;

public interface NoticeProcInter {
  
  /**
   * 공지사항 등록
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
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @param search 검색 옵션
   * @return 페이징 생성 문자열
   */ 
  public String paging(int search_count, int nowPage, String word, String search);
  
  
  /**
   * 검색된 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * @return
   */
  public int search_count(HashMap hashMap);  
  
  
  /**
   * 공지사항 조회
   * <select id="notice_read" resultType="NoticeVO" parameterType="HashMap">
   * @param noticeno
   * @return
   */
  public NoticeVO notice_read(int noticeno);
  
  
  /**
   * 공지사항 수정
   * <update id ="notice_update" parameterType="NoticeVO">
   * @param noticeVO
   * @return
   */
  public int notice_update(NoticeVO noticeVO);
  
  
  /**
   * 공지사항 삭제
   * <delete id="notice_delete" parameterType= "int">
   * @param noticeno
   * @return
   */
  public int notice_delete(int noticeno);
  
  
  /**
   * 공지사항 우선순위 높임
   * <update id ="nseqno_up" parameterType= "int">
   * @param noticeno
   * @return
   */
  public int nseqno_up(int noticeno);
  
  
  /**
   * 공지사항 우선순위 낮춤
   * <update id ="nseqno_down" parameterType= "int">
   * @param noticeno
   * @return
   */
  public int nseqno_down(int noticeno);
  
  
  /**
   * 공지사항 조회수
   * <update id="ncnt_up" parameterType= "int">
   * @param noticeno
   * @return
   */
  public int ncnt_up(int noticeno);
  

}
