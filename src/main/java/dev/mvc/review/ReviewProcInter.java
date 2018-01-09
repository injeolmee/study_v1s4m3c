package dev.mvc.review;

import java.util.HashMap;
import java.util.List;

public interface ReviewProcInter {
  
  /**
   * <Xmp>
   * 리뷰 등록
   * <insert id="create" parameterType="ReviewVO">
   * </Xmp>
   * @param categoryVO
   * @return
   */
  public int create(ReviewVO reviewVO);
  
  
  /**
   * <Xmp>
   * 카테고리 그룹별 목록
   * <select id="list" parameterType="Ro_Mem_ReviewVO">
   * </Xmp>
   * @param reviewVO
   * @return
   */
  public List<ReviewVO> list(HashMap hashMap);

  
  /**
   * 레코드 갯수
   * <select id="search_count" resultType="int">
   * @return
   */
  public int search_count(int rono); 
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param rono 스터디룸 게시글 번호 
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @return 페이징 생성 문자열
   */ 
  public String paging(int rono, int search_count, int nowPage);
  
  
  /**
   * 리뷰글 읽기
   * @param rvno
   * @return
   */
  public ReviewVO read(int rvno);
  
  
  /**
   * <XMP>
   * 리뷰글 좋아요 수 증가
   * </XMP>
   * @param rvno
   * @return
   */
  public int likecnt_up(int rvno);
  
  /**
   * <XMP>
   * 리뷰글 좋아요 수 감소
   * </XMP>
   * @param rvno
   * @return
   */
  public int likecnt_down(int rvno);
  
  
  /**
   * 리뷰글 수정 폼
   * <update id="update" parameterType="ReviewVO">
   * @param rvno
   * @return
   */
  public ReviewVO update(int rvno);
  
  
  /**
   * 리뷰글 수정 처리
   * @param reviewVO
   * @return
   */
  public int update(ReviewVO reviewVO);
  
  
  /**
   * 리뷰글 삭제 처리
   * <delete id="delete" parameterType="int">
   * @param rvno
   * @return
   */
  public int delete(int rvno);
  
  
  /**
   * 리뷰글 rono별 갯수
   * <delete id="delete" parameterType="int">
   * @param rono
   * @return
   */
  public int countByRono(int rono);
  
  /**
   * 리뷰글 rono별 삭제
   * <delete id="delete" parameterType="int">
   * @param rono
   * @return
   */
  public int deleteByRono(int rono);

  /**
   * <XMP>
   * 등록된 리뷰의 가장큰 리뷰번호값을 가져옴
   * <select id="rvno" resultType="int">
   * </XMP>
   * @param rvno
   * @return
   */
  public int rvno();
  
}
