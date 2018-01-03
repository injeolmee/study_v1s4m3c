package dev.mvc.qna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface QnaProcInter {

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
   * ������ ����(��)
   * </XMP>
   * @param qnano
   * @return
   */
  public QnaVO update(int qnano);
  
  /**
   * <XMP>
   * ������ ����(ó��)
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
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String qnatitle);
  
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
