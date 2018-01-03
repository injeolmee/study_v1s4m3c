package dev.mvc.contest;

import java.util.HashMap;
import java.util.List;

public interface ContestProcInter {

  /**
   * <XMP>
   * 공모전 정보 등록
   * <insert id="create" parameterType="ContestVO">
   * </XMP>
   * @param contestVO
   * @return
   */
  public int create(ContestVO contestVO);
  
  /**
   * <XMP>
   * 공모전 전체 목록
   * <select id="list_all_contest" resultType="ContestVO">
   * </XMP>
   * @return
   * @throws Exception 
   */
  public List<ContestVO> list_all_contest(HashMap hashMap) throws Exception;
  
  /**
   * <XMP>
   * 공모전 전체 목록
   * <select id="list_by_count" resultType="ContestVO">
   * </XMP>
   * @return
   */
  public List<ContestVO> list_by_count();
  
  /**
   * <XMP>
   * 공모전 추천수에 따른 목록
   * <select id="list_by_good" resultType="ContestVO">
   * </XMP>
   * @return
   */
  public List<ContestVO> list_by_good();
  
  /**
   * <XMP>
   * 게시글의 처음 번호
   * <select id="min" resultType="int">
   * </XMP>
   * @return
   */
  public int min();
  
  /**
   * <XMP>
   * 게시글의 마지막 번호
   * <select id="max" resultType="int">
   * </XMP>
   * @return
   */
  public int max();
  
  /**
   * <XMP>
   * 공모전 글 조회수증가
   * <update id="increaseCnt" parameterType="int">
   * </XMP>
   * @param conNo
   * @return
   */
  public int increaseCnt(int conNo);
  
  /**
   * <XMP>
   * 좋아요 수 증가
   * </XMP>
   * @param conNo
   * @return
   */
  public int goodcnt_up(int conNo);
  
  /**
   * <XMP>
   * 좋아요 수 감소
   * </XMP>
   * @param conNo
   * @return
   */
  public int goodcnt_down(int conNo);
  
  /**
   * <XMP>
   * category별 검색된 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </XMP>
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * <xmp>
   * 공모전 글 전체 갯수
   * <select id="total_count" resultType="int">
   * </xmp>
   * @return
   */
  public int total_count();
  
  /**
   * <XMP>
   * 진행중인 공모전 글 갯수
   * <select id="day_count" resultType="int">
   * </XMP>
   * @return
   */
  public int day_count();
  
  /**
   * 한건의 레코드 조회
   * <select id="read" resultType="ContestVO" parameterType="int">
   * @param conNo 글 번호
   * @return
   * @throws Exception 
   */
  public ContestVO read(int conNo) throws Exception;
  
  /**
   * 수정 폼
   * @param conNo
   * @return
   */
  public ContestVO update(int conNo);
  
  /**
   * 수정처리
   * <update id="update" parameterType="ContestVO"> 
   * @param ContestVO
   * @return
   */
  public int update(ContestVO contestVO);
  
  /**
   * <XMP>
   * 썸네일 수정 폼
   * <update id="update_thumb" parameterType="ContestVO">
   * </XMP>
   * @param conNo
   * @return
   */
  public ContestVO update_thumb(int conNo);
  
  /**
   * <XMP>
   * 썸네일 수정 수정
   * <update id="update_thumb" parameterType="ContestVO">
   * </XMP>
   * @param contestVO
   * @return
   */
  public int update_thumb(ContestVO contestVO);
  
  
  /**
   * <XMP>
   * 이미지 수정 폼
   * <update id="update_img" parameterType="ContestVO">
   * </XMP>
   * @param conNo
   * @return
   */
  public ContestVO update_img(int conNo);
  
  /**
   * <XMP>
   * 이미지 수정 처리
   * <update id="update_img" parameterType="ContestVO">
   * </XMP>
   * @param contestVO
   * @return
   */
  public int update_img(ContestVO contestVO);
  
  /**
   * <XMP>
   * 일반 파일 수정 폼
   * <update id="update_file" parameterType="ContestVO">
   * </XMP>
   * @param conNo
   * @return
   */
  public ContestVO update_file(int conNo);
  
  /**
   * <XMP>
   * 일반 파일 수정 처리
   * <update id="update_file" parameterType="ContestVO">
   * </XMP>
   * @param contestVO
   * @return
   */
  public int update_file(ContestVO contestVO);
  
  /**
   * 삭제 처리
   * <delete id="delete" parameterType="int">
   * @param conNo
   * @return
   */
  public int delete(int conNo);
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */ 
  public String paging(int search_count, int nowPage, String conWord);
  
}
