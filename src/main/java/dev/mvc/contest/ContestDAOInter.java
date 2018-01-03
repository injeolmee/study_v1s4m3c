package dev.mvc.contest;

import java.util.HashMap;
import java.util.List;

public interface ContestDAOInter {
  
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
   */
  public List<ContestVO> list_all_contest(HashMap hashMap);
  
  /**
   * <XMP>
   * 공모전 조회수에 따른 목록
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
   * 게시글의 마지막 번호
   * <select id="max" resultType="int">
   * </XMP>
   * @return
   */
  public int max();
  
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
   * 한건의 레코드 조회
   * <select id="read" resultType="ContestVO" parameterType="int">
   * @param conNo 글 번호
   * @return
   */
  public ContestVO read(int conNo);
  
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
   * 수정처리
   * <update id="update" parameterType="ContestVO"> 
   * @param ContestVO
   * @return
   */
  public int update(ContestVO contestVO);
  
  /**
   * <XMP>
   * 썸네일 수정
   * <update id="update_thumb" parameterType="ContestVO">
   * </XMP>
   * @param contestVO
   * @return
   */
  public int update_thumb(ContestVO contestVO);
  
  /**
   * <XMP>
   * 이미지 수정
   * <update id="update_img" parameterType="ContestVO">
   * </XMP>
   * @param contestVO
   * @return
   */
  public int update_img(ContestVO contestVO);
  
  /**
   * <XMP>
   * 일반 파일 수정
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
  
  
  
}
