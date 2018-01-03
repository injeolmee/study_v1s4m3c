package dev.mvc.qna;

import java.util.HashMap;
import java.util.List;

public interface QnaDAOInter {
  
  /**
   * <XMP>
   * QnA �Խ��� �� ���
   * <insert id="create" parameterType="QnaVO">
   * </XMP>
   * @param qnaVO
   * @return
   */
  public int create(QnaVO qnaVO);
  
  /**
   * <XMP>
   * QnA �Խ��� ���
   * <select id="list_all_qna" resultType="QnaVO">
   * </XMP>
   * @param hashMap
   * @return
   */
  public List<QnaVO> list_all_qna(HashMap hashMap);
  
  /**
   * <XMP>
   * �Ѱ��� ���ڵ� ��ȸ
   * <select id="read" resultType="QnaVO" parameterType="int">
   * </XMP>
   * @param qnano
   * @return
   */
  public QnaVO read(int qnano);
  
  /**
   * <XMP>
   * ������ ����
   * <update id="update" parameterType="QnaVO">
   * </XMP>
   * @param qnaVO
   * @return
   */
  public int update(QnaVO qnaVO);
  
  /**
   * <XMP>
   * ������ ����
   * <delete id="delete" parameterType="int">
   * </XMP>
   * @param qnano
   * @return
   */
  public int delete(int qnano);
  
  /**
   * <XMP>
   * ��ȸ�� ����
   * <update id="increaseCnt" parameterType="int">
   * </XMP>
   * @param qnano
   * @return
   */
  public int increaseCnt(int qnano);
  
  /**
   * <XMP>
   * ���� �� ��ü ����
   * <select id="total_count" resultType="int">
   * </XMP>
   * @return
   */
  public int total_count();
  
  /**
   * <XMP>
   * qna�� �˻��� ���ڵ� ����
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </XMP>
   * @param hashMap
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * <XMP>
   * �н����� �˻�
   * <select id="pwdchk" resultType="int">
   * </XMP>
   * @param map
   * @return
   */
  public int pwdchk(String qnapwd);
  
}
