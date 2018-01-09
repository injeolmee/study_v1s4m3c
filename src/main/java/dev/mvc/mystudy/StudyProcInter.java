package dev.mvc.mystudy;

import java.util.List;

import dev.mvc.studylist.StudyListVO;

public interface StudyProcInter {
  /**
   * <xmp>
   * ���͵� �׷��� ����� ��� ����Ѵ�.
   * <select id="readList" resultType="StudylistVO">
   * </xmp>
   * @return
   */
  public List<StudyListVO> list();
  
  /**
   * <xmp>
   * ȸ������ ������ ��� ����Ѵ�.
   * <select id="readList" resultType="MemberlistVO">
   * </xmp>
   * @return
   */
  public List<MemberlistVO> mem_list();
  
  //public List<MemberlistVO> join_test();
  
  /**
   * <xmp>
   * ���� �����ϰ� �ִ� ���͵� ���
   * <select id="mystudylist" resultType="StudyListVO" parameterType="int">
   * </xmp>
   * @param memberno
   * @return List<StudyListVO>
   */
  public List<StudyListVO> mystudylist(int memberno);
  
  /**
   * ���� ȸ���� ��û�� ���͵� ���� �� ����
   * @param memberno
   * @return
   */
  public List<My_apply_listVO> my_apply_list(int memberno);
  
  /**
   * <xmp>
   * <delete id="cancel_apply" parameterType="int">
   * ���͵� ���� ��û ���
   * </xmp> 
   * @param recuritno
   * @return
   */
  public int cancel_apply(int recuritno);
}
