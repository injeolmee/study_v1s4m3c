package dev.mvc.mystudy;

import java.util.List;

import dev.mvc.studylist.StudyListVO;

public interface StudyProcInter {
  /**
   * <xmp>
   * 스터디 그룹의 목록을 모두 출력한다.
   * <select id="readList" resultType="StudylistVO">
   * </xmp>
   * @return
   */
  public List<StudyListVO> list();
  
  /**
   * <xmp>
   * 회원들의 정보를 모두 출력한다.
   * <select id="readList" resultType="MemberlistVO">
   * </xmp>
   * @return
   */
  public List<MemberlistVO> mem_list();
  
  //public List<MemberlistVO> join_test();
  
  /**
   * <xmp>
   * 내가 참여하고 있는 스터디 목록
   * <select id="mystudylist" resultType="StudyListVO" parameterType="int">
   * </xmp>
   * @param memberno
   * @return List<StudyListVO>
   */
  public List<StudyListVO> mystudylist(int memberno);
  
  /**
   * 현재 회원이 신청한 스터디 정보 및 상태
   * @param memberno
   * @return
   */
  public List<My_apply_listVO> my_apply_list(int memberno);
  
  /**
   * <xmp>
   * <delete id="cancel_apply" parameterType="int">
   * 스터디 참가 신청 취소
   * </xmp> 
   * @param recuritno
   * @return
   */
  public int cancel_apply(int recuritno);
}
