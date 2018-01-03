package dev.mvc.qna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface QnaProcInter {

  /**
   * <XMP>
   * QnA 게시판 글 등록
   * <insert id="create" parameterType="QnaVO">
   * </XMP>
   * @param qnaVO
   * @return
   */
  public int create(QnaVO qnaVO);
  
  /**
   * <XMP>
   * QnA 게시판 목록
   * <select id="list_all_qna" resultType="QnaVO">
   * </XMP>
   * @param hashMap
   * @return
   */
  public List<QnaVO> list_all_qna(HashMap hashMap);
  
  /**
   * <XMP>
   * 한건의 레코드 조회
   * <select id="read" resultType="QnaVO" parameterType="int">
   * </XMP>
   * @param qnano
   * @return
   */
  public QnaVO read(int qnano);
  
  /**
   * <XMP>
   * 질문글 수정(폼)
   * </XMP>
   * @param qnano
   * @return
   */
  public QnaVO update(int qnano);
  
  /**
   * <XMP>
   * 질문글 수정(처리)
   * <update id="update" parameterType="QnaVO">
   * </XMP>
   * @param qnaVO
   * @return
   */
  public int update(QnaVO qnaVO);  
  
  /**
   * <XMP>
   * 질문글 삭제
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param qnano
   * @return
   */
  public int delete(int qnano);
  
  /**
   * <XMP>
   * 조회수 증가
   * <update id="increaseCnt" parameterType="int">
   * </XMP>
   * @param qnano
   * @return
   */
  public int increaseCnt(int qnano);
  
  /**
   * <XMP>
   * 질문 글 전체 갯수
   * <select id="total_count" resultType="int">
   * </XMP>
   * @return
   */
  public int total_count();
  
  /**
   * <XMP>
   * qna별 검색된 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */ 
  public String paging(int search_count, int nowPage, String qnatitle);
  
  /**
   * <XMP>
   * 패스워드 검사
   * <select id="pwdchk" resultType="int">
   * </XMP>
   * @param map
   * @return
   */
  public int pwdchk(String qnapwd);
  
}
