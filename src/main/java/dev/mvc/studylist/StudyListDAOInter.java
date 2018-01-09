package dev.mvc.studylist;

import java.util.HashMap;
import java.util.List;

public interface StudyListDAOInter {

  /**
   * <xml> 스터디그룹 등록 <insert id="create" parameterType="StudyListVO"> </xml>
   * 
   * @param StudyListVO
   * @return int
   */
  public int create(StudyListVO studyListVO);

  /**
   * <xml> 스터디그룹 목록 <select id="list" resultType="StudyListVO"> </xml>
   * 
   * @param StudyListVO
   * @return List
   */
  public List<StudyListVO> list();

  /**
   * <xml> 체크박스를 이용한 검색
   * <select id = 'search_list1' resultType="StudyListVO" parameterType=
   * "Hashmap"> </xml>
   * 
   * @param Hashmap
   * @return StudyListVO
   */
  public List<StudyListVO> search_list1(HashMap hashmap);

  /**
   * <xml> select option을 이용한 검색
   * <select id = 'search_list2' resultType="StudyListVO" parameterType=
   * "Hashmap"> </xml>
   * 
   * @param Hashmap
   * @return StudyListVO
   */
  public List<StudyListVO> search_list2(HashMap hashmap);

  /**
   * <xml> select option를 이용한 검색 + ajax 페이징
   * <select id = 'search_list3' resultType="StudyListVO" parameterType=
   * "Hashmap"> </xml>
   * 
   * @param Hashmap
   * @return StudyListVO
   */
  public List<StudyListVO> search_list3(HashMap hashmap);

  /**
   * <xml> 스터디모집글과 회원정보를 조회한다.
   * <select id="read" resultType="StudyListVO" parameterType="hashMap" > </xml>
   * 
   * @param StudyList_MemberVO
   * @return StudyList_MemberVO
   */
  public StudyList_MemberVO read(StudyList_MemberVO studyList_memberVO);

  /**
   * <xml> stdlist_no 별 조회
   * <select id="read_std" resultType="StudyListVO" parameterType="int"> </xml>
   * 
   * @param stdlist_no
   * @return StudtListVO
   */
  public StudyListVO read_std(int stdlist_no);

  /**
   * <xml> 스터디모집 내용을 수정합니다. <update id="update" parameterType="StudyListVO">
   * </xml>
   * 
   * @param studyListVO
   * @return
   */
  public int update(StudyListVO studyListVO);

  /**
   * <xml> 스터디모집 내용 삭제합니다. <delete id="delete" parameterType="int"> </xml>
   * 
   * @param stdlist_no
   * @return
   */
  public int delete(int stdlist_no);

  /**
   * <xmp> 팀장이 회원 승인 신청을 하게 되면 현재인원수 +1
   * <update id="up_currnum" parameterType="int"> </xmp>
   * 
   * @param stdlist_no
   * @return
   */
  public int up_currnum(int stdlist_no);

  /**
   * <xmp> 조회수 +1 <update id="up_cnt" parameterType="int"> </xmp>
   * 
   * @param stdlist_no
   * @return
   */
  public int up_cnt(int stdlist_no);

  /**
   * <xmp> <!-- 스터디리스트의 번호 조회 --> <select id="stdlist_no"> </xmp>
   * 
   * @param
   * @return
   */
  public int stdlist_no();

  /**
   * <xmp> <!-- select option 검색으로 검색된 개수 -->
   * <select id = 'search_count' resultType="int" parameterType="HashMap">
   * </xmp>
   * 
   * @param categoryno
   * @return
   */
  public int search_count(HashMap hashmap);
  
  /**
   * <xmp> 스터디그룹별 좋아요수 증가 <update id="goodcnt_up" parameterType="int"> </xmp>
   * 
   * @param stdlist_no
   * @return
   */
  public int goodcnt_up(int stdlist_no);
  
  /**
   * <xmp> 스터디그룹별 좋아요수 감소 <update id="goodcnt_down" parameterType="int"> </xmp>
   * 
   * @param stdlist_no
   * @return
   */
  public int goodcnt_down(int stdlist_no);
  
  /**
   * <xml> 
   *  좋아요수별로 top 다섯개 컬럼 
   * <select id='rank_top5' resultType="StudyListVO"> 
   * </xml>
   * 
   * @param 
   * @return List<StudyListVO>
   */
  public List<StudyListVO> rank_top5();
  
  /**
   * <xml> 
   *  스터디그룹을 등록한 id 가 맞는지 검사
   * <select id="check_stdno" resultType="int" parameterType="Hashmap">
   * </xml>
   * 
   * @param hashmap
   * @return int
   */
  public int check_stdno(HashMap hashmap);

}
