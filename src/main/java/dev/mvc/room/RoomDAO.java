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
   * �� ���
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
   * ��� (�˻� & ����¡)
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
   * �˻��� ���ڵ� ����
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * @return
   */
  @Override
  public int search_count(HashMap hashMap) {
    int cnt = mybatis.selectOne("room.search_count", hashMap);
    
    return cnt;
  }
  
  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * <select id="read" resultType="RoomVO" parameterType="int">
   * @param rono �� ��ȣ
   * @return
   */
  @Override
  public RoomVO read(int rono) {
    RoomVO roomVO = mybatis.selectOne("room.read", rono);
    return roomVO;
  }
  
  /**
   * <Xmp>
   * ������
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
   * ����ó��
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
   * ����ó��
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
