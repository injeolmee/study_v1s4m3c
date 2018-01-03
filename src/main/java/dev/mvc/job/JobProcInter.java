package dev.mvc.job;

import java.util.HashMap;
import java.util.List;

public interface JobProcInter {

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
   * @throws Exception 
   */
  public List<JobVO> list_all_jobinfo(HashMap hashMap) throws Exception;
  
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
   * @throws Exception 
   */
  public JobVO read(int jobNo) throws Exception;
  
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
   * 수정 폼
   * </XMP>
   * @param jobNo
   * @return
   */
  public JobVO update(int jobNo);
  
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
   * 썸네일 수정 폼
   * </XMP>
   * @param jobNo
   * @return
   */
  public JobVO update_thumb(int jobNo);
  
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
   * 파일 수정 폼
   * </XMP>
   * @param jobNo
   * @return
   */
  public JobVO update_file(int jobNo);
  
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
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */ 
  public String paging(int search_count, int nowPage, String jobWord);
  
}
