package dev.mvc.qna;

import java.util.HashMap;
import java.util.List;

public interface QnaDAOInter {
  
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
   * 질문글 수정
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
   * <XMP>
   * 패스워드 검사
   * <select id="pwdchk" resultType="int">
   * </XMP>
   * @param map
   * @return
   */
  public int pwdchk(String qnapwd);
  
}
