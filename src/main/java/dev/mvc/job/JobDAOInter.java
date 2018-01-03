package dev.mvc.job;

import java.util.HashMap;
import java.util.List;

public interface JobDAOInter {
  
  /**
   * <XMP>
   * 채용정보 등록
   * <insert id="create" parameterType="JobVO">
   * </XMP>
   * @param jobVO
   * @return
   */
  public int create(JobVO jobVO);
  
  /**
   * <XMP>
   * 채용정보 목록
   * <select id="list_all_jobinfo" resultType="JobVO" parameterType="HashMap">
   * </XMP>
   * @return
   */
  public List<JobVO> list_all_jobinfo(HashMap hashMap);
  
  /**
   * <XMP>
   * 채용정보 조회수에 따른 목록
   * <select id="list_by_count" resultType="JobVO">
   * </XMP> 
   * @return
   */
  public List<JobVO> list_by_count();
  
  /**
   * <XMP>
   * 채용정보 조회
   * <select id="read" resultType="JobVO" parameterType="int">
   * </XMP>
   * @param jobNo
   * @return
   */
  public JobVO read(int jobNo);
  
  /**
   * <XMP>
   * 채용정보의 마지막 번호
   * <select id="max" resultType="int">
   * </XMP>
   * @return
   */
  public int max();
  
  /**
   * <XMP>
   * 채용정보의 처음 번호
   * <select id="mini" resultType="int">
   * </XMP>
   * @return
   */
  public int mini();
  
  /**
   * <XMP>
   * 채용정보 글 조회수 증가
   * <update id="increaseCnt" parameterType="int">
   * </XMP>
   * @param jobNo
   * @return
   */
  public int increaseCnt(int jobNo);
  
  /**
   * <XMP>
   * 검색된 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * <XMP>
   * 수정처리
   * <update id="update" parameterType="JobVO">
   * </XMP>
   * @param jobVO
   * @return
   */
  public int update(JobVO jobVO);
  
  /**
   * <XMP>
   * 수정처리 썸네일
   * <update id="update_thumb" parameterType="JobVO">
   * </XMP>
   * @param jobVO
   * @return
   */
  public int update_thumb(JobVO jobVO);
  
  /**
   * <XMP>
   * 수정처리 파일
   * <update id="update_file" parameterType="JobVO">
   * </XMP>
   * @param jobVO
   * @return
   */
  public int update_file(JobVO jobVO);
  
  /**
   * <XMP>
   * 삭제 처리
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param jobNo
   * @return
   */
  public int delete(int jobNo);
  
}
