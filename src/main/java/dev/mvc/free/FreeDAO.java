package dev.mvc.free;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.free.FreeDAO")
public class FreeDAO implements FreeDAOInter {
  
  @Autowired
  private SqlSessionTemplate mybatis; // mybatis ����

  public FreeDAO() {
    //System.out.println(" => FreeDAO created");
  }

  /* ��� */
  @Override
  public int create(FreeVO freeVO) {
    int count = 0;
    count = mybatis.insert("free.create", freeVO);
    return count;
  }

  /* ��� */
  @Override
  public List<FreeVO> list() {
    List <FreeVO> list = mybatis.selectList("free.list");
    return list;
  }

  /* ��ȸ */
  @Override
  public FreeVO read(int freeno) {
    FreeVO freeVO = mybatis.selectOne("free.read", freeno);
    return freeVO;
  }
  
  /* ��ȸ�� ���� */
  @Override
  public int increaseCnt(int freeno) {
    int count = mybatis.update("free.increaseCnt", freeno);
    return count;
  }
  
  /* ���� */
  @Override
  public int update(FreeVO freeVO) {
    int count = mybatis.update("free.update", freeVO);
    return count;
  }

  /* ���� */
  @Override
  public int delete(int freeno) {
    int count = mybatis.delete("free.delete", freeno);
    return count;
  }

  /* �˻� */
  @Override
  public List<FreeVO> list_search(HashMap hashMap) {
    List <FreeVO> list = mybatis.selectList("free.list", hashMap);
    return list;
  }

  /* �˻� ���ڵ� ���� */
  @Override
  public int search_count(HashMap hashMap) {
    int count = mybatis.selectOne("free.search_count", hashMap);
    return count;
  }

  /* ��õ�� �ֻ��� 3�� ��� */
  @Override
  public List<FreeVO> list_like(HashMap<String, Object> hashMap2) {
    List <FreeVO> list_like = mybatis.selectList("free.list_like", hashMap2);
    return list_like;
  }

  /* ��ȸ�� �ֻ��� 3�� ��� */
  @Override
  public List<FreeVO> list_cnt(HashMap<String, Object> hashMap3) {
    List <FreeVO> list_cnt = mybatis.selectList("free.list_cnt", hashMap3);
    return list_cnt;
  }

  /* ������ ��ȸ */
  @Override
  public int read_pre(int freeno) {
    int count = mybatis.selectOne("free.read_pre", freeno);
    return count;
  }

  /* ������ ��ȸ */
  @Override
  public int read_post(int freeno) {
    int count = mybatis.selectOne("free.read_post", freeno);
    return count;
  }

  /* ���̵� �˻� */
  @Override
  public int member_check(HashMap<String, Object> hashMap) {
    int count = mybatis.selectOne("free.member_check", hashMap);
    return count;
  }
  
  /* ���ƿ䰡 0���� 1�� ���� */
  @Override
  public int increaseLike(int freeno) {
    int count = mybatis.update("free.increaseLike", freeno);
    return count;
  }
  
  /* ���ƿ䰡 1���� 0���� ���� */
  @Override
  public int decreaseLike(int freeno) {
    int count = mybatis.update("free.decreaseLike", freeno);
    return count;
  }

  /* ������ �Խñ� ��� */
  @Override
  public int create_admin(FreeVO freeVO) {
    return mybatis.insert("free.create_admin", freeVO);
  }

  
}
