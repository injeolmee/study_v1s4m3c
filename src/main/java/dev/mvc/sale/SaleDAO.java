package dev.mvc.sale;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.sale.SaleDAO")
public class SaleDAO implements SaleDAOInter {

  @Autowired
  private SqlSessionTemplate mybatis;
  
  public SaleDAO() {
    // System.out.println(" => SaleDAO created ");
  }
  
  /* ��� */
  @Override
  public int create(SaleVO saleVO) {
    int count = mybatis.insert("sale.create", saleVO);
    return count;
  }

  /* �˻� + ��� + ����¡ */
  @Override
  public List<SaleVO> list_search(HashMap hashMap) {
    List <SaleVO> list = mybatis.selectList("sale.list_search", hashMap);
    return list;
  }

  /* �˻��� ���ڵ� ���� ���� */
  @Override
  public int search_count(HashMap hashMap) {
    int count = mybatis.selectOne("sale.search_count", hashMap);
    return count;
  }

  /* �Խñ� ��ȸ */
  @Override
  public SaleVO read(int saleno) {
    SaleVO saleVO = mybatis.selectOne("sale.read", saleno);
    return saleVO;
  }

  /* ������ ��ȸ */
  @Override
  public int read_pre(int saleno) {
    int count = mybatis.selectOne("sale.read_pre", saleno);
    return count;
  }

  /* ������ ��ȸ */
  @Override
  public int read_post(int saleno) {
    int count = mybatis.selectOne("sale.read_post", saleno);
    return count;
  }

  /* ��ȸ�� ��� */
  @Override
  public int increaseCnt(int saleno) {
    int count = mybatis.update("sale.increaseCnt", saleno);
    return count;
  }

  /* ���� */
  @Override
  public int update(SaleVO saleVO) {
    int count = mybatis.update("sale.update", saleVO);
    return count;
  }

  /* ���� */
  @Override
  public int delete(int saleno) {
    int count = mybatis.delete("sale.delete", saleno);
    return count;
  }

  /* ���̵� �˻� */
  @Override
  public int member_check(HashMap<String, Object> hashMap) {
    int count = mybatis.selectOne("sale.member_check", hashMap);
    return count;
  }

  /* ������ �Խñ� ���*/
  @Override
  public int create_admin(SaleVO saleVO) {
    return mybatis.insert("sale.create_admin", saleVO);
  }

}
