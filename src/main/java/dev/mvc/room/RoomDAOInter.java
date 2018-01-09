package dev.mvc.room;

import java.util.HashMap;
import java.util.List;

public interface RoomDAOInter {
  
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
   * <Xmp>
   * 검색된 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  public int search_count(HashMap hashMap); 
  
  
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
   * @param rono
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
