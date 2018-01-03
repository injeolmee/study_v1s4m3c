package dev.mvc.shared;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.shared.SharedDAO")
public class SharedDAO implements SharedDAOInter {

  @Autowired
  private SqlSessionTemplate mybatis;
  
  public SharedDAO() {
    // System.out.println(" => SharedDAO created");
  }
  
  /* ��� */
  @Override
  public int create(SharedVO sharedVO) {
    int count = mybatis.insert("shared.create", sharedVO);
    return count;
  }

  /* ��� */
  @Override
  public List<SharedVO> list() {
    List <SharedVO> list = mybatis.selectList("shared.list");
    return list;
  }

  /* �˻� */
  @Override
  public List<SharedVO> list_search(HashMap hashMap) {
    List <SharedVO> list = mybatis.selectList("shared.list_search", hashMap);
    return list;
  }

  /* �˻� ���ڵ� ���� */
  @Override
  public int search_count(HashMap hashMap) {
    int count = mybatis.selectOne("shared.search_count", hashMap);
    return count;
  }

  /* ��ȸ */
  @Override
  public SharedVO read(int sharedno) {
    SharedVO sharedVO = mybatis.selectOne("shared.read", sharedno);
    return sharedVO;
  }

  /* ��ȸ�� ��� */
  @Override
  public int increaseCnt(int sharedno) {
    int count = mybatis.update("shared.increaseCnt", sharedno);
    return count;
  }

  /* ��õ�� ���*/
  @Override
  public int increaseLike(int sharedno) {
    int count = mybatis.update("shared.increaseLike", sharedno);
    return count;
  }

  /* ���� */
  @Override
  public int update(SharedVO sharedVO) {
    int count = mybatis.update("shared.update", sharedVO);
    return count;
  }

  /* �н����� �˻� */
  @Override
  public int passwd_check(HashMap<String, Object>  hashMap) {
    int count = mybatis.selectOne("shared.passwd_check", hashMap);
    return count;
  }

  /* ���� */
  @Override
  public int delete(int sharedno) {
    int count = mybatis.delete("shared.delete", sharedno);
    return count;
  }

  /* ������ ��ȸ */
  @Override
  public int read_pre(int sharedno) {
    int count = mybatis.selectOne("shared.read_pre", sharedno);
    return count;
  }

  /* ������ ��ȸ */
  @Override
  public int read_post(int sharedno) {
    int count = mybatis.selectOne("shared.read_post", sharedno);
    return count;
  }

  /* ���̵� �˻� */
  @Override
  public int member_check(HashMap<String, Object> hashMap) {
    int count = mybatis.selectOne("shared.member_check", hashMap);
    return count;
  }

}
