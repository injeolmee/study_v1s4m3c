package dev.mvc.my_pds;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.my_std_catelist.My_std_catelistVO;

@Repository("dev.mvc.my_pds.My_pdsDAO")
public class My_pdsDAO implements My_pdsDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis;

  @Override
  public int insert(My_pdsVO my_pdsVO) {
    return mybatis.insert("my_pds.insert", my_pdsVO);
  }

  @Override
  public List<My_pdsVO> list(HashMap<String, Object> hashMap) {
    return mybatis.selectList("my_pds.list", hashMap);
  }

  @Override
  public My_std_catelistVO search_mylistno(HashMap<String, Integer> hashMap) {
    return mybatis.selectOne("my_pds.search_mylistno", hashMap);
  }

  @Override
  public My_std_catelistVO search_cateno_stdlist_no(int mylistno) {
    return mybatis.selectOne("my_pds.search_cateno/stdlist_no", mylistno);
  }

  @Override
  public String search_memname(int memberno) {
    return mybatis.selectOne("my_pds.search_memname", memberno);
  }

  @Override
  public My_pdsVO read(int pdsno) {
    return mybatis.selectOne("my_pds.read", pdsno);
  }

  @Override
  public int update(My_pdsVO my_pdsVO) {
    return mybatis.update("my_pds.update", my_pdsVO);
  }

  @Override
  public int check_passwd(HashMap<String, Object> hashMap) {
    return mybatis.selectOne("my_pds.check_passwd", hashMap);
  }

  @Override
  public int delete(int pdsno) {
    return mybatis.delete("my_pds.delete", pdsno);
  }

  @Override
  public int inc_cnt(int pdsno) {
    return mybatis.update("my_pds.inc_cnt", pdsno);
  }

  @Override
  public int inc_like(int pdsno) {
    return mybatis.update("my_pds.inc_like", pdsno);
  }

  @Override
  public int lastest_pdsno() {
    return mybatis.selectOne("my_pds.lastest_pdsno");
  }

  @Override
  public int del_file(int pdsno) {
    return mybatis.delete("my_pds.del_file", pdsno);
  }

  /**
   * 조회된 글 수
   */
  @Override
  public int search_count(HashMap<String, Object> hashMap) {
    return mybatis.selectOne("my_pds.search_count", hashMap);
  }
  
}
