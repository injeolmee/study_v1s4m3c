package dev.mvc.studylist;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.studylist.StudyListProc")
public class StudyListProc implements StudyListProcInter {

  @Autowired
  @Qualifier("dev.mvc.studylist.StudyListDAO")
  private StudyListDAOInter studyListDAO = null;

  public StudyListProc() {
    System.out.println("--> StudyListProc created.");
  }

  /**
   * ���͵� �׷� �Է�
   */
  @Override
  public int create(StudyListVO studyListVO) {

    return studyListDAO.create(studyListVO);
  }

  /**
   * ���͵� ����Ʈ ���
   */
  @Override
  public List<StudyListVO> list() {

    return studyListDAO.list();
  }

  /**
   * ���͵� ����Ʈ ����
   */
  @Override
  public int update(StudyListVO studyListVO) {

    return studyListDAO.update(studyListVO);
  }

  /**
   * ���͵� ����Ʈ ����
   */
  @Override
  public int delete(int stdlist_no) {

    return studyListDAO.delete(stdlist_no);
  }

  /**
   * ���͵� ����Ʈ �о���� ���͵� ����Ʈ + ȸ�� ����
   */
  @Override
  public StudyList_MemberVO read(StudyList_MemberVO studyList_memberVO) {
    return studyListDAO.read(studyList_memberVO);
  }

  /**
   * ������ �������� ���� �ο��� ����
   */
  @Override
  public int up_currnum(int stdlist_no) {

    return studyListDAO.up_currnum(stdlist_no);
  }

  /**
   * ���͵𸮽�Ʈ �� ��ȸ�� ����
   */
  @Override
  public int up_cnt(int stdlist_no) {

    return studyListDAO.up_cnt(stdlist_no);
  }

  /**
   * ���͵𸮽�Ʈ�� �� �ϳ� �о����
   */
  @Override
  public StudyListVO read_std(int stdlist_no) {

    return studyListDAO.read_std(stdlist_no);
  }

  /**
   * ���͵𸮽�Ʈ��ȣ ��������
   */
  @Override
  public int stdlist_no() {

    return studyListDAO.stdlist_no();
  }

  /**
   * üũ�ڽ��� ���� �˻�
   */
  @Override
  public List<StudyListVO> search_list1(HashMap hashmap) {

    return studyListDAO.search_list1(hashmap);
  }

  @Override
  public List<StudyListVO> search_list2(HashMap hashmap) {

    return studyListDAO.search_list2(hashmap);
  }

  @Override
  public List<StudyListVO> search_list3(HashMap hashmap) {

    /*
     * ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ���� 1������: nowPage = 1, (1-1) * 10
     * => 0 2������: nowPage = 2, (2-1) * 10 => 10 3������: nowPage = 3, (3-1) * 10 =>
     * 20
     */
    int beginOfPage = ((Integer) hashmap.get("nowPage") - 1) * StudyList.RECORD_PER_PAGE;
    int startNum = beginOfPage + 1; // ���� rownum/ 1������: 1/ 2������: 11
    int endNum = beginOfPage + StudyList.RECORD_PER_PAGE;
    ; // ���� rownum/ 1������: 10/ 2������: 20
    /*
     * 1������: WHERE r >= 1 AND r <= 10 2������: WHERE r >= 11 AND r <= 20 3������:
     * WHERE r >= 21 AND r <= 30
     */
    hashmap.put("startNum", startNum);
    hashmap.put("endNum", endNum);

    return studyListDAO.search_list3(hashmap);
  }

  /**
   * 1 ���������� ���� ���� ������: 11 / 22 [����] 11 12 13 14 15 16 17 18 19 20 [����]
   *
   * @param search_count
   *          �˻�(��ü) ���ڵ��
   * @param nowPage
   *          ���� ������
   * @return ����¡ ���� ���ڿ�
   */
  public String paging(int search_count, int nowPage) {

    int totalPage = (int) (Math.ceil((double) search_count / StudyList.RECORD_PER_PAGE)); // ��ü
                                                                                          // ������
    int totalGrp = (int) (Math.ceil((double) totalPage / StudyList.PAGE_PER_BLOCK));// ��ü
                                                                                    // �׷�
    int nowGrp = (int) (Math.ceil((double) nowPage / StudyList.PAGE_PER_BLOCK)); // ����
                                                                                 // �׷�
    int startPage = ((nowGrp - 1) * StudyList.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������
                                                                   // ��� ����
    int endPage = (nowGrp * StudyList.PAGE_PER_BLOCK); // Ư�� �׷��� ������ ��� ����

    StringBuffer str = new StringBuffer();

    str.append("<ul class='pagination'>");
    // str.append("���� ������: " + nowPage + " / " + totalPage + " ");

    int _nowPage = (nowGrp - 1) * StudyList.PAGE_PER_BLOCK; // ���� �������� �̵�

    if (nowGrp >= 2) {
      str.append("<ul class='pager'>");
      str.append("<A onclick='search_topic(" + _nowPage + ");'>����</A>");
      str.append("</ul>");
    }

    for (int i = startPage; i <= endPage; i++) {
      if (i > totalPage) {
        break;
      }

      if (nowPage == i) {
        str.append("<li class='active'><A onclick='search_topic(" + i + ");'>" + i + "</A></li>"); // ����
                                                                                                   // ������,
                                                                                                   // ����
      } else {

        // ���� �������� �ƴ� ������
        str.append("<li><A onclick='search_topic(" + i + ");'>" + i + "</A></li>");
      }
    }

    _nowPage = (nowGrp * StudyList.PAGE_PER_BLOCK) + 1; // 10�� ���� �������� �̵�

    if (nowGrp < totalGrp) {
      str.append("<ul class='pager'>");
      str.append("<A onclick='search_topic(" + _nowPage + ");'>����</A>");
      str.append("</ul>");
    }
    str.append("</ul>");

    return str.toString();
  }

  @Override
  public int search_count(HashMap hashmap) {

    return studyListDAO.search_count(hashmap);
  }

  @Override
  public int goodcnt_up(int stdlist_no) {
    
    return studyListDAO.goodcnt_up(stdlist_no);
  }

  @Override
  public int goodcnt_down(int stdlist_no) {
    
    return studyListDAO.goodcnt_down(stdlist_no);
  }

  @Override
  public List<StudyListVO> rank_top5() {
    
    return studyListDAO.rank_top5();
  }

}
