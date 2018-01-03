package dev.mvc.sharedreply;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.sharedreply.SharedreplyDAO")
public class SharedreplyDAO implements SharedreplyDAOInter  {
  
  @Autowired
  SqlSessionTemplate mybatis;
  
  public SharedreplyDAO() {
    // System.out.println(" => SharedreplyDAO created");
  }

  /* ��� ��� */
  @Override
  public int create(SharedreplyVO sharedreplyVO) {
    int count = mybatis.insert("sharedreply.create", sharedreplyVO);
    return count;
  }

  /* ���� ���� ���� */
  @Override
  public int updateAnsnum(SharedreplyVO sharedreplyVO) {
    int count = mybatis.update("sharedreply.updateAnsnum", sharedreplyVO);
    return count;
  }

  /* ���� ��� */
  @Override
  public int reply(SharedreplyVO sharedreplyVO) {
    int count = mybatis.insert("sharedreply.reply", sharedreplyVO);
    return count;
  }

  /* Ư�� ��� ��ȸ */
  @Override
  public SharedreplyVO read(int shreplyno) {
    SharedreplyVO sharedreplyVO = mybatis.selectOne("sharedreply.read", shreplyno);
    return sharedreplyVO;
  }

  /* ��� ��� + ����¡ */
  @Override
  public List<SharedreplyVO> total_list_reply(HashMap<String, Object> hashMap) {
    List<SharedreplyVO> total_list_reply = mybatis.selectList("sharedreply.total_list_reply", hashMap);
    return total_list_reply;
  }

  /* ��� �� ���ڵ� ���� ���� */
  @Override
  public int search_count(int sharedno) {
    int count = mybatis.selectOne("sharedreply.search_count", sharedno);
    return count;
  }

  /* ��� ���� */
  @Override
  public int update(SharedreplyVO sharedreplyVO) {
    int count = mybatis.update("sharedreply.update", sharedreplyVO);
    return count;
  }

  /* ��� ���� */
  @Override
  public int delete(int shreplyno) {
    int count = mybatis.delete("sharedreply.delete", shreplyno);
    return count;
  }

  /* �θ� ����� seqno�� 0���� ����*/ 
  @Override
  public int update_seqno(int shreplyno) {
    int count = mybatis.update("sharedreply.update_seqno", shreplyno);
    return count;
  }

  /* �θ� ��ۿ� ���� ���� ó�� ��� content�� ������ ��� */
  @Override
  public int update_parent(int shreplyno) {
    int count = mybatis.update("sharedreply.update_parent", shreplyno);
    return count;
  }

  /* �Խñ� ������ ��� ��ü ���� */
  @Override
  public int delete_all(int sharedno) {
    int count = mybatis.delete("sharedreply.delete_all", sharedno);
    return count;
  }
  
}
