package dev.mvc.room;

import java.util.HashMap;
import java.util.List;

public interface RoomProcInter {
  /**
   * <Xmp>
   * 스터디룸 글 등록
   * <insert id="create" parameterType="RoomVO">
   * </Xmp>
   * @param roomVO
   * @return int
   */
  public int create(RoomVO roomVO);
  
  
  /**
   * <Xmp>
   * 목록 (검색 & 페이징)
   * <select id="list" resultType="RoomVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public List<RoomVO> list(HashMap hashMap);
  
  
  /**
   * 검색된 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * @return
   */
  public int search_count(HashMap hashMap); 
  
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param ronalo 검색어
   * @return 페이징 생성 문자열
   */ 
  public String paging(int search_count, int nowPage, String ronalo); 
  
  
  /**
   * 한건의 레코드 조회
   * <select id="read" resultType="RoomVO" parameterType="int">
   * @param rono 글 번호
   * @return
   */
  public RoomVO read(int rono);
  
  
  /**
   * <Xmp>
   * 수정폼
   * <update id="update" parameterType="int">
   * </Xmp> 
   * @param room
   * @return
   */
  public RoomVO update(int rono);
  
  
  /**
   * <Xmp>
   * 수정처리
   * <update id="update" parameterType="RoomVO"> 
   * </Xmp>
   * @param vo
   * @return
   */
  public int update(RoomVO vo);
  
  
  /**
   * 삭제 처리
   * <delete id="delete" parameterType="int">
   * @param rono
   * @return
   */
  public int delete(int rono);

}
