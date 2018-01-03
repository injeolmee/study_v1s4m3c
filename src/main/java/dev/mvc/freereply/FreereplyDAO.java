package dev.mvc.freereply;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.freereply.FreereplyDAO")
public class FreereplyDAO implements FreereplyDAOInter {
  
  @Autowired
  SqlSessionTemplate mybatis;
  
  public FreereplyDAO() {
    // System.out.println(" => FreereplyDAO created");
  }

  /* ��� ��� */
  @Override
  public int create(FreereplyVO freereplyVO) {
    int count = mybatis.insert("freereply.create", freereplyVO);
    return count;
  }

  /* ���� ���� ���� */
  @Override
  public int updateAnsnum(FreereplyVO freereplyVO) {
    int count = mybatis.update("freereply.updateAnsnum", freereplyVO);
    return count;
  }

  /* ���� ��� */
  @Override
  public int reply(FreereplyVO freereplyVO) {
    int count = mybatis.insert("freereply.reply", freereplyVO);
    return count;
  }

  /* Ư�� ��� ��ȸ */
  @Override
  public FreereplyVO read(int freeno) {
    FreereplyVO freereplyVO = mybatis.selectOne("freereply.read", freeno);
    return freereplyVO;
  }

  /* ��� ��� + ����¡ */
  @Override
  public List<FreereplyVO> total_list_reply(HashMap<String, Object> hashMap) {
    List<FreereplyVO> total_list_reply = mybatis.selectList("freereply.total_list_reply", hashMap);
    return total_list_reply;
  }

  /* ��� �� ���ڵ� ���� ���� */
  @Override
  public int search_count(int freeno) {
    int count = mybatis.selectOne("freereply.search_count", freeno);
    return count;
  }

  /* ��� ���� */
  @Override
  public int update(FreereplyVO freereplyVO) {
    int count = mybatis.update("freereply.update", freereplyVO);
    return count;
  }

  /* ��� ���� */
  @Override
  public int delete(int freplyno) {
    int count = mybatis.delete("freereply.delete", freplyno);
    return count;
  }

  /* �θ� ����� seqno�� 0���� ����*/
  @Override
  public int update_seqno(int freplyno) {
    int count = mybatis.update("freereply.update_seqno", freplyno);
    return count;
  }

  /* �θ� ��ۿ� ���� ���� ó�� ��� content�� ������ ��� */
  @Override
  public int update_parent(int freplyno) {
    int count = mybatis.update("freereply.update_parent", freplyno);
    return count;
  }

  /* �Խñ� ������ ��� ��ü ����*/
  @Override
  public int delete_all(int freeno) {
    int count = mybatis.delete("freereply.delete_all", freeno);
    return count;
  }
  
  

}
