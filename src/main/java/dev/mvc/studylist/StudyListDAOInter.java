package dev.mvc.studylist;

import java.util.HashMap;
import java.util.List;

public interface StudyListDAOInter {

  /**
   * <xml> ���͵�׷� ��� <insert id="create" parameterType="StudyListVO"> </xml>
   * 
   * @param StudyListVO
   * @return int
   */
  public int create(StudyListVO studyListVO);

  /**
   * <xml> ���͵�׷� ��� <select id="list" resultType="StudyListVO"> </xml>
   * 
   * @param StudyListVO
   * @return List
   */
  public List<StudyListVO> list();

  /**
   * <xml> üũ�ڽ��� �̿��� �˻�
   * <select id = 'search_list1' resultType="StudyListVO" parameterType=
   * "Hashmap"> </xml>
   * 
   * @param Hashmap
   * @return StudyListVO
   */
  public List<StudyListVO> search_list1(HashMap hashmap);

  /**
   * <xml> select option�� �̿��� �˻�
   * <select id = 'search_list2' resultType="StudyListVO" parameterType=
   * "Hashmap"> </xml>
   * 
   * @param Hashmap
   * @return StudyListVO
   */
  public List<StudyListVO> search_list2(HashMap hashmap);

  /**
   * <xml> select option�� �̿��� �˻� + ajax ����¡
   * <select id = 'search_list3' resultType="StudyListVO" parameterType=
   * "Hashmap"> </xml>
   * 
   * @param Hashmap
   * @return StudyListVO
   */
  public List<StudyListVO> search_list3(HashMap hashmap);

  /**
   * <xml> ���͵�����۰� ȸ�������� ��ȸ�Ѵ�.
   * <select id="read" resultType="StudyListVO" parameterType="hashMap" > </xml>
   * 
   * @param StudyList_MemberVO
   * @return StudyList_MemberVO
   */
  public StudyList_MemberVO read(StudyList_MemberVO studyList_memberVO);

  /**
   * <xml> stdlist_no �� ��ȸ
   * <select id="read_std" resultType="StudyListVO" parameterType="int"> </xml>
   * 
   * @param stdlist_no
   * @return StudtListVO
   */
  public StudyListVO read_std(int stdlist_no);

  /**
   * <xml> ���͵���� ������ �����մϴ�. <update id="update" parameterType="StudyListVO">
   * </xml>
   * 
   * @param studyListVO
   * @return
   */
  public int update(StudyListVO studyListVO);

  /**
   * <xml> ���͵���� ���� �����մϴ�. <delete id="delete" parameterType="int"> </xml>
   * 
   * @param stdlist_no
   * @return
   */
  public int delete(int stdlist_no);

  /**
   * <xmp> ������ ȸ�� ���� ��û�� �ϰ� �Ǹ� �����ο��� +1
   * <update id="up_currnum" parameterType="int"> </xmp>
   * 
   * @param stdlist_no
   * @return
   */
  public int up_currnum(int stdlist_no);

  /**
   * <xmp> ��ȸ�� +1 <update id="up_cnt" parameterType="int"> </xmp>
   * 
   * @param stdlist_no
   * @return
   */
  public int up_cnt(int stdlist_no);

  /**
   * <xmp> <!-- ���͵𸮽�Ʈ�� ��ȣ ��ȸ --> <select id="stdlist_no"> </xmp>
   * 
   * @param
   * @return
   */
  public int stdlist_no();

  /**
   * <xmp> <!-- select option �˻����� �˻��� ���� -->
   * <select id = 'search_count' resultType="int" parameterType="HashMap">
   * </xmp>
   * 
   * @param categoryno
   * @return
   */
  public int search_count(HashMap hashmap);
  
  /**
   * <xmp> ���͵�׷캰 ���ƿ�� ���� <update id="goodcnt_up" parameterType="int"> </xmp>
   * 
   * @param stdlist_no
   * @return
   */
  public int goodcnt_up(int stdlist_no);
  
  /**
   * <xmp> ���͵�׷캰 ���ƿ�� ���� <update id="goodcnt_down" parameterType="int"> </xmp>
   * 
   * @param stdlist_no
   * @return
   */
  public int goodcnt_down(int stdlist_no);
  
  /**
   * <xml> 
   *  ���ƿ������ top �ټ��� �÷� 
   * <select id='rank_top5' resultType="StudyListVO"> 
   * </xml>
   * 
   * @param 
   * @return List<StudyListVO>
   */
  public List<StudyListVO> rank_top5();
  
  /**
   * <xml> 
   *  ���͵�׷��� ����� id �� �´��� �˻�
   * <select id="check_stdno" resultType="int" parameterType="Hashmap">
   * </xml>
   * 
   * @param hashmap
   * @return int
   */
  public int check_stdno(HashMap hashmap);

}
