package dev.mvc.notice_reply;

import java.util.HashMap;
import java.util.List;

public interface NrepDAOInter {
  
  
/**
 * ������� ���
 * <insert id="nrep_create" parameterType="NrepVO">
 * @param nrepVO
 * @return
 */
  public int nrep_create(NrepVO nrepVO);

  
  /**
   * ������� ����Ʈ
   * <select id="nrep_list" resultType="NrepVO" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public List<NrepVO> nrep_list(HashMap hashMap);
  
  
  /**
   * �������� �ۺ� ��� ����
   * <select id = 'search_count' resultType="int" parameterType="int">
   * @param noticeno
   * @return
   */
  public int search_count(int noticeno);
  
  
  /**
   * �����ۺ� ��� �� �ϳ��� ��� ����
   * <select id="nrep_read" resultType="NrepVO"  parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public NrepVO nrep_read(HashMap hashMap);
  
  
  /**
   * ��ۼ���
   * <update id="nrep_update" parameterType="NrepVO">
   * @param nrepVO
   * @return
   */
  public int nrep_update(NrepVO nrepVO);
  
  
  /**
   * ��� �ϳ� ����
   * <delete id="nrep_delete" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public int nrep_delete(HashMap hashMap);
  
  
  /**
   * ���� �ۺ� ��� ��� ����
   * <delete id="nrep_deleteAll" parameterType="int">
   * @param noticeno
   * @return
   */
  public int nrep_deleteAll(int noticeno);
  
  
  /**
   * ��� �� ȸ����ȣ�� �´��� �˻�
   * <select id= "nrep_ck_memberno" resultType="int" parameterType="HashMap">
   * @param hashMap
   * @return
   */
  public int nrep_ck_memberno(HashMap hashMap);
  
  
}
