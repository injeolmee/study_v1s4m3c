package dev.mvc.room;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.room.RoomDAO")
public class RoomDAO implements RoomDAOInter {
  @Autowired
  private SqlSessionTemplate mybatis;
  
  public RoomDAO() {
    //System.out.println("--> RoomDAO created.");
  }

  
  /**
   * <Xmp>
   * 글 등록
   * <insert id="create" parameterType="RoomVO">
   * </Xmp>
   * @param roomVO
   * @return int
   */
  @Override
  public int create(RoomVO roomVO) {
    return mybatis.insert("room.create", roomVO);
  }

  
  /**
   * <Xmp>
   * 목록 (검색 & 페이징)
   * <select id="list" resultType="RoomVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return int
   */
  @Override
  public List<RoomVO> list(HashMap hashMap) {
    List<RoomVO> list = null;
    list = mybatis.selectList("room.list", hashMap);
 
    return list;
  }
  
  /**
   * 검색된 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * @return
   */
  @Override
  public int search_count(HashMap hashMap) {
    int cnt = mybatis.selectOne("room.search_count", hashMap);
    
    return cnt;
  }
  
  /**
   * 한건의 레코드 조회
   * <select id="read" resultType="RoomVO" parameterType="int">
   * @param rono 글 번호
   * @return
   */
  @Override
  public RoomVO read(int rono) {
    RoomVO roomVO = mybatis.selectOne("room.read", rono);
    return roomVO;
  }
  
  /**
   * <Xmp>
   * 수정폼
   * <update id="update" parameterType="int">
   * @param blogno
   * @return
   */
  @Override
  public RoomVO update(int rono) {
    RoomVO roomVO = mybatis.selectOne("room.read", rono);
    return roomVO;
  }

  /**
   * <Xmp>
   * 수정처리
   * <update id="update" parameterType="RoomVO"> 
   * </Xmp>
   * @param vo
   * @return
   */
  @Override
  public int update(RoomVO vo) {
    return mybatis.update("room.update", vo);
  }


  /**
   * <Xmp>
   * 삭제처리
   * <update id="delete" parameterType="int"> 
   * </Xmp>
   * @param rono
   * @return
   */
  @Override
  public int delete(int rono) {
    int count = mybatis.delete("room.delete", rono);
    return count;
  }

}
