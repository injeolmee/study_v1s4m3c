package dev.mvc.salereply;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.salereply.SalereplyDAO")
public class SalereplyDAO implements SalereplyDAOInter {

  @Autowired
  private SqlSessionTemplate mybatis;
  
  public SalereplyDAO() {
    // System.out.println(" => SalereplyDAO created");
  }

  /* ��� */
  @Override
  public int create(SalereplyVO salereplyVO) {
    int count = mybatis.insert("salereply.create", salereplyVO);
    return count;
  }

  /* ��� */
  @Override
  public List<SalereplyVO> list_reply(int saleno) {
    List<SalereplyVO> list_reply = mybatis.selectList("salereply.list_reply", saleno);
    return list_reply;
  }

  /* ���� ���� ���� */
  @Override
  public int updateAnsnum(SalereplyVO salereplyVO) {
    int count = mybatis.update("salereply.updateAnsnum", salereplyVO);
    return count;
  }

  /* ���� ��� */
  @Override
  public int reply(SalereplyVO salereplyVO) {
    int count = mybatis.insert("salereply.reply", salereplyVO);
    return count;
  }

  /* ��� ��ȸ */
  @Override
  public SalereplyVO read(int sreplyno) {
    SalereplyVO salereplyVO = mybatis.selectOne("salereply.read", sreplyno);
    return salereplyVO;
  }

  /* ��� ��� + ����¡ */
  @Override
  public List<SalereplyVO> total_list_reply(HashMap<String, Object> hashMap) {
    List<SalereplyVO> total_list_reply = mybatis.selectList("salereply.total_list_reply", hashMap);
    return total_list_reply;
  }

  /* ����� �� ���ڵ� ���� */
  @Override
  public int search_count(int saleno) {
    int count = mybatis.selectOne("salereply.search_count", saleno);
    return count;
  }

  /* ��� ���� */
  @Override
  public int update(SalereplyVO salereplyVO) {
    int count = mybatis.update("salereply.update", salereplyVO);
    return count;
  }

  /* ��� ���� */
  @Override
  public int delete(int sreplyno) {
    int count = mybatis.delete("salereply.delete", sreplyno);
    return count;
  }

  /* �θ� ����� seqno�� 0���� ����*/
  @Override
  public int update_seqno(int sreplyno) {
    int count = mybatis.update("salereply.update_seqno", sreplyno);
    return count;
  }

  /* �θ� ��ۿ� ���� ���� ó�� ��� content�� ������ ��� */
  @Override
  public int update_parent(int sreplyno) {
    int count = mybatis.update("salereply.update_parent", sreplyno);
    return count;
  }
  
  /* �Խñ� ������ ��� ��ü ���� */
  @Override
  public int delete_all(int saleno) {
    int count = mybatis.delete("salereply.delete_all", saleno);
    return count;
  }

  /* ������ ��� ��� */
  @Override
  public int create_admin(SalereplyVO salereplyVO) {
    return mybatis.insert("salereply.create_admin", salereplyVO);
  }

  /* ������ ���� ��� */
  @Override
  public int reply_admin(SalereplyVO salereplyVO) {
    return mybatis.insert("salereply.reply_admin", salereplyVO);
  }

  /* �θ� ����� ��� ���� ����� �����ϴ��� �˻� */
  @Override
  public int parent_check(int sreplygrpno) {
    return mybatis.selectOne("salereply.parent_check", sreplygrpno);
  }

  /* ���۰� ���õǾ� �� ������ ������� �˻� */
  @Override
  public int reply_check(HashMap hashMap) {
    return mybatis.selectOne("salereply.reply_check", hashMap);
  }


  
}
