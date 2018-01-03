package dev.mvc.shared;

import java.util.HashMap;
import java.util.List;

public interface SharedProcInter {
  
  /**
   * <XMP>
   * 게시글 등록
   * <insert id="create" parameterType="SharedVO">
   * </XMP>
   * @param sharedVO
   * @return 등록할 게시글 갯수
   */
  public int create(SharedVO sharedVO);
  
  /**
   * <XMP>
   * 게시판 목록
   * <select id="list" resultType="SharedVO">
   * </XMP>
   * @return 게시판 목록
   */
  public List<SharedVO> list();
  
  /**
   * <XMP>
   * 게시판 목록 Grid형
   * </XMP>
   * @return
   */
  public List<SharedVO> list_grid(HashMap hashMap);
  
  /**
   * <XMP>
   * 게시판 검색 기능
   * <select id="list" resultType="SharedVO" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public List<SharedVO> list_search(HashMap hashMap);
  
  /**
   * <XMP>
   * 검색된 레코드 갯수 산출
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * <XMP>
   * 페이징 처리 (기본 목록형)
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음]  
   * </XMP>
   * @param nowPage 현재페이지
   * @param word 검색어
   * @param serach 검색할 Value Option
   * @return 페이징 생성 문자열
   */
  public String paging(int nowPage, int search_count, String word, String search); 
  
  /**
   * <XMP>
   * 페이징 처리 (Grid형)
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음]  
   * </XMP>
   * @param nowPage 현재페이지
   * @param word 검색어
   * @param serach 검색할 Value Option
   * @return 페이징 생성 문자열
   */
  public String paging_grid(int nowPage, int search_count, String word, String search); 
  
  /**
   * <XMP>
   * 게시글 조회
   * <select id="read" resultType="SharedVO" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public SharedVO read(int sharedno);
  
  /**
   * <XMP>
   * 조회수 상승
   * <update id="increaseCnt" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int increaseCnt (int sharedno);
  
  /**
   * <XMP>
   * 추천수 상승
   * <update id="increaseLike" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int increaseLike (int sharedno);
  
  /**
   * <XMP>
   * 패스워드 검사
   * <select id="passwd_check" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int passwd_check (SharedVO sharedVO);
  
  /**
   * <XMP>
   * 게시글 수정
   * <update id="update" parameterType="SharedVO">
   * </XMP>
   * @param sharedVO
   * @return
   */
  public int update (SharedVO sharedVO);
  
  /**
   * <XMP>
   * 게시글 삭제
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int delete (int sharedno);
  
  /**
   * <XMP>
   * 게시글 이전글 조회
   * <select id="read_pre" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int read_pre (int sharedno);
  
  /**
   * <XMP>
   * 게시글 다음글 조회
   * <select id="read_post" parameterType="int">
   * </XMP>
   * @param sharedno
   * @return
   */
  public int read_post (int sharedno);
  
  /**
   * <XMP>
   * 아이디 검사
   * <select id="member_check" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param sharedVO
   * @return
   */
  public int member_check (SharedVO sharedVO);
  
  

}
