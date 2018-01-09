package dev.mvc.recruit;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.studylist.StudyListVO;

@Component("dev.mvc.recruit.RecruitProc")
public class RecruitProc implements RecruitProcInter{

  @Autowired
  @Qualifier("dev.mvc.recruit.RecruitDAO")
  private RecruitDAOInter recruitDAO = null;
  
  public RecruitProc(){
    // System.out.println("--> RecruitProc created.");
  }

  /**
   * ȸ���� ���͵�׷� ��û�� �ϰ� �Ǹ� ��û���̺� �Է�
   */
  @Override
  public int create(RecruitVO recruitVO) {
    
    return recruitDAO.create(recruitVO);
  }

  /**
   * ���͵� �׷캰 ��û����Ʈ
   */
  @Override
  public List<Recruit_MemberVO> recruit_list(int stdlist_no) {
    
    return recruitDAO.recruit_list(stdlist_no);
  }

  /**
   *  ���� ���� �ʱ�ȭ
   *  Y , L
   */
  @Override
  public int leader_auth(HashMap hashmap) {
    
    return recruitDAO.leader_auth(hashmap);
  }

  /**
   *  ���εǸ� ȸ�� ���͵�׷쿡 ����
   *  Y, T
   */
  @Override
  public int confirm_Y(HashMap hashmap) {
    
    return recruitDAO.confirm_Y(hashmap);
  }

  /**
   * ȸ�� ����
   */
  @Override
  public int confirm_N(HashMap hashmap) {
    
    return recruitDAO.confirm_N(hashmap);
  }
  
  /**
   *  ���͵� �׷��ϰ� ���ÿ� ��û���̺� �ڵ����� ����
   *  �����Է°� ���ÿ� ���� ����
   */
  @Override
  public int create(StudyListVO studyListVO) {
    
    return recruitDAO.create(studyListVO);
  }

  
  /**
   *  ���͸��׷� ��� ������ ���͵� ���� ����Ʈ
   */
  @Override
  public List<Recruit_MemberVO> recruit_list_Y(int stdlist_no) {
    
    return recruitDAO.recruit_list_Y(stdlist_no);
  }

  @Override
  public int check_memberno(HashMap hashmap) {
    
    return recruitDAO.check_memberno(hashmap);
  }

  @Override
  public int delete(int stdlist_no) {
    
    return recruitDAO.delete(stdlist_no);
  }
  
  @Override
  public String check_leader(HashMap<String, Object> hashMap) {
    return recruitDAO.check_leader(hashMap); 
  }



}
