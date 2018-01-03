package dev.mvc.qnareply;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.qnareply.QnareplyDAO")
public class QnareplyDAO implements QnareplyDAOInter {
  @Autowired
  private SqlSessionTemplate mybatis;
  
  public QnareplyDAO() {
    
  }
  
  /* ��� ��� */
  @Override
  public int create(QnareplyVO qnareplyVO) {
    return mybatis.insert("qnareply.create", qnareplyVO);
  }

  /* Ư�� ��� ��ȸ */
  @Override
  public QnareplyVO read(int qrno) {
    return mybatis.selectOne("qnareply.read", qrno);
  }

  /* ��� ��� + ����¡ */
  @Override
  public List<QnareplyVO> list_all_qnareply(HashMap hashMap) {
    return mybatis.selectList("qnareply.list_all_qnareply", hashMap);
  }

  /* ��� �� ���ڵ� ���� ���� */
  @Override
  public int search_count(int qnano) {
    return mybatis.selectOne("qnareply.search_count", qnano);
  }

  /* ��� ���� */
  @Override
  public int update(QnareplyVO qnareplyVO) {
    return mybatis.update("qnareply.update", qnareplyVO);
  }

  /* ��� ���� */
  @Override
  public int delete(int qrno) {
    return mybatis.delete("qnareply.delete", qrno);
  }

  /* �Խñ� ������ ��� ��ü ���� */
  @Override
  public int delete_all(int qnano) {
    return mybatis.delete("qnareply.delete_all", qnano);
  }
  
}
