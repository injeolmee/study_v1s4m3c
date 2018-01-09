package dev.mvc.studylist;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.studylist.StudyListDAO")
public class StudyListDAO implements StudyListDAOInter{
  @Autowired
  private SqlSessionTemplate mybatis;
  
  public StudyListDAO() {
    // System.out.println("--> StudyListDAO created.");
  }

  @Override
  public int create(StudyListVO studyListVO) {
    
    return mybatis.insert("studylist.create", studyListVO);
  }

  @Override
  public List<StudyListVO> list() {
    
    return mybatis.selectList("studylist.list");
  }

  @Override
  public int update(StudyListVO studyListVO) {
    
    return mybatis.update("studylist.update", studyListVO);
  }

  @Override
  public int delete(int stdlist_no) {
    
    return mybatis.delete("studylist.delete", stdlist_no);
  }


  @Override
  public StudyList_MemberVO read(StudyList_MemberVO studyList_memberVO) {
    
    return mybatis.selectOne("studylist.read", studyList_memberVO);
  }

  @Override
  public int up_currnum(int stdlist_no) {
    
    return mybatis.update("studylist.up_currnum", stdlist_no);
  }

  @Override
  public int up_cnt(int stdlist_no) {
   
    return mybatis.update("studylist.up_cnt", stdlist_no);
  }

  @Override
  public StudyListVO read_std(int stdlist_no) {
   
    return mybatis.selectOne("studylist.read_std", stdlist_no);
  }

  @Override
  public int stdlist_no() {
    
    return mybatis.selectOne("studylist.stdlist_no");
  }

  @Override
  public List<StudyListVO> search_list1(HashMap hashmap) {
    
    return mybatis.selectList("studylist.search_list1", hashmap);
  }

  @Override
  public List<StudyListVO> search_list2(HashMap hashmap) {
    
    return mybatis.selectList("studylist.search_list2", hashmap);
  }

  @Override
  public List<StudyListVO> search_list3(HashMap hashmap) {
    
    return mybatis.selectList("studylist.search_list3", hashmap);
  }
  
  @Override
  public int search_count(HashMap hashmap) {
    
    return mybatis.selectOne("studylist.search_count", hashmap);
  }

  @Override
  public int goodcnt_up(int stdlist_no) {
    
    return mybatis.update("studylist.goodcnt_up", stdlist_no);
  }

  @Override
  public int goodcnt_down(int stdlist_no) {
    
    return mybatis.update("studylist.goodcnt_down", stdlist_no);
  }

  @Override
  public List<StudyListVO> rank_top5() {
    
    return mybatis.selectList("studylist.rank_top5");
  }

  @Override
  public int check_stdno(HashMap hashmap) {
    
    return mybatis.selectOne("studylist.check_stdno", hashmap);
  }




}
