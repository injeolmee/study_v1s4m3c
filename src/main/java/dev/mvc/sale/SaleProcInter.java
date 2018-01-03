package dev.mvc.sale;

import java.util.HashMap;
import java.util.List;

import dev.mvc.shared.SharedVO;

public interface SaleProcInter {
  
  /**
   * <XMP>
   * 게시글 등록
   * <insert id="create" parameterType="SaleVO">
   * </XMP>
   * @param saleVO
   * @return
   */
  public int create (SaleVO saleVO);
  
  /**
   * <XMP>
   * 게시글 목록
   * <select id="list" resultType="SaleVO">
   * </XMP>
   * @return
   */
  public List <SaleVO> list();
  
  /**
   * <XMP>
   * 검색 + 목록 + 페이징
   * <select id="list" resultType="SaleVO" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public List <SaleVO> list_search (HashMap hashMap);
  
  /**
   * <XMP>
   * 게시판 목록 Grid형
   * </XMP>
   * @return
   */
  public List<SaleVO> list_grid(HashMap hashMap);
  
  /**
   * <XMP>
   * 검색 레코드 갯수 산출
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int search_count (HashMap hashMap);
  
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
   * <select id="read" resultType="SaleVO" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public SaleVO read (int saleno);
  
  /**
   * <XMP>
   * 게시글 이전글 조회
   * <select id="read_pre" resultType="int" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public int read_pre (int saleno);
  
  /**
   * <XMP>
   * 게시글 다음글 조회
   * <select id="read_post" resultType="int" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public int read_post (int saleno);
  
  /**
   * <XMP>
   * 조회수 상승
   * <update id="increaseCnt" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public int increaseCnt (int saleno);

  /**
   * <XMP>
   * 패스워드 검사
   * <select id="passwd_check" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int passwd_check (SaleVO saleVO);
  
  /**
   * <XMP>
   * 게시글 수정
   * <update id="update" parameterType="SaleVO">
   * </XMP>
   * @param saleVO
   * @return
   */
  public int update(SaleVO saleVO);
  
  /**
   * <XMP>
   * 게시글 삭제
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param saleno
   * @return
   */
  public int delete(int saleno);
  
  /**
   * <XMP>
   * 아이디 검사
   * <select id="member_check" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param saleVO
   * @return
   */
  public int member_check (SaleVO saleVO);
  
}
