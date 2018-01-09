package dev.mvc.mystudy;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.studylist.StudyListVO;

@Repository("dev.mvc.study.StudyDAO")
public class StudyDAO implements StudyDAOInter{

  @Autowired
  private SqlSessionTemplate mybatis;
  
  public StudyDAO() {
    System.out.println(" --> StudyDAO created.");
  }
  
  @Override
  public List<StudyListVO> list() {
    List<StudyListVO> list=mybatis.selectList("studylist.readList");
    return list;
  }

  @Override
  public List<MemberlistVO> mem_list() {
    List<MemberlistVO> mem_list=mybatis.selectList("memberlist.readList");
    return mem_list;
  }

  @Override
  public List<StudyListVO> mystudylist(int memberno) {
    List<StudyListVO> list=mybatis.selectList("studylist.mystudylist", memberno);
    return list;
  }

  @Override
  public List<My_apply_listVO> my_apply_list(int memberno) {
    List<My_apply_listVO> my_apply_list=mybatis.selectList("studylist.my_apply_list", memberno); 
    return my_apply_list; 
  }

  @Override
  public int cancel_apply(int recuritno) {
    int result=mybatis.delete("studylist.cancel_apply", recuritno);
    return result;
  } 

}
