package dev.mvc.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import nation.web.tool.Tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import dev.mvc.contest.Contest;

@Repository("dev.mvc.job.JobProc")
public class JobProc implements JobProcInter {
  
  @Autowired
  @Qualifier("dev.mvc.job.JobDAO")
  private JobDAOInter jobDAO;

  @Override
  public int create(JobVO jobVO) {
    int count = jobDAO.create(jobVO);
    
    return count;
  }

  @Override
  public List<JobVO> list_all_jobinfo(HashMap hashMap) throws Exception {
    /* 
      ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
   1������: nowPage = 1, (1-1) * 10 => 0
   2������: nowPage = 2, (2-1) * 10 => 10
   3������: nowPage = 3, (3-1) * 10 => 20   
   */    
   int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Job.RECORD_PER_PAGE;
   int startNum = beginOfPage + 1; // ���� rownum/ 1������: 1/ 2������: 11
   int endNum = beginOfPage + Job.RECORD_PER_PAGE; // ���� rownum/ 1������: 10/ 2������: 20
   /*    
   1������: WHERE r >= 1 AND r <= 10
   2������: WHERE r >= 11 AND r <= 20
   3������: WHERE r >= 21 AND r <= 30
   */   
   hashMap.put("startNum", startNum);
   hashMap.put("endNum", endNum);
    
    List<JobVO> list = jobDAO.list_all_jobinfo(hashMap);
    
    for (int i = 0; i < list.size(); i++) {
      JobVO jobVO = list.get(i);
      String jobStart = jobVO.getJobStart();
      String jobEnd = jobVO.getJobEnd();
      
      long time = System.currentTimeMillis();
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      String today = df.format(new Date(time));
      
      Date date_s = df.parse(jobStart);
      Date date_e = df.parse(jobEnd);
      
      String startDate = new SimpleDateFormat("yyyy-MM-dd").format(date_s);
      String endDate = new SimpleDateFormat("yyyy-MM-dd").format(date_e);
      
      long now = (df.parse(today)).getTime();
      long start = (df.parse(startDate)).getTime();
      long end = (df.parse(endDate)).getTime();
      
      long diff_s = start - now;
      long diff_e = end - now;
      long jobRemain_s = diff_s / (24 * 60 * 60 * 1000);
      long jobRemain_e = diff_e / (24 * 60 * 60 * 1000);
      
      jobVO.setJobRemain_s(jobRemain_s);
      jobVO.setJobRemain_e(jobRemain_e);
    }
    
    return list;
  }
  
  @Override
  public List<JobVO> list_by_count() {
    List<JobVO> list = jobDAO.list_by_count();
    
    return list;
  }

  @Override
  public JobVO read(int jobNo) throws Exception {
    JobVO jobVO = jobDAO.read(jobNo);
    
    long jobSize1 = jobVO.getJobSize1();
    if (jobSize1 > 0) {
      String size1Label = Tool.unit(jobSize1);
      jobVO.setSize1Label(size1Label);
    }
    
    long jobSize2 = jobVO.getJobSize2();
    if (jobSize2 > 0) {
      String size2Label = Tool.unit(jobSize2);
      jobVO.setSize2Label(size2Label);
    }
    
    String jobStart = jobVO.getJobStart();
    String jobEnd = jobVO.getJobEnd();
    
    String jobCont = jobVO.getJobCont();
    jobCont = Tool.convertChar(jobCont);
    jobVO.setJobCont(jobCont);
    
    long time = System.currentTimeMillis();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String today = df.format(new Date(time));
    
    Date date_e = df.parse(jobEnd);
    Date date_s = df.parse(jobStart);
    
    String endDate = new SimpleDateFormat("yyyy-MM-dd").format(date_e);
    String startDate = new SimpleDateFormat("yyyy-MM-dd").format(date_s);
    
    long now = (df.parse(today)).getTime();
    long end = (df.parse(endDate)).getTime();
    long start = (df.parse(startDate)).getTime();
    
    long diff_e = end - now;
    long jobRemain_e = diff_e / (24 * 60 * 60 * 1000);
    
    long diff_s = start - now;
    long jobRemain_s = diff_s / (24 * 60 * 60 * 1000);
    
    jobVO.setJobRemain_e(jobRemain_e);
    jobVO.setJobRemain_s(jobRemain_s);
    
    return jobVO;
  }

  @Override
  public int max() {
    return jobDAO.max();
  }

  @Override
  public int mini() {
    return jobDAO.mini();
  }

  @Override
  public int increaseCnt(int jobNo) {
    return jobDAO.increaseCnt(jobNo);
  }
  
  @Override
  public int search_count(HashMap hashMap) {
    return jobDAO.search_count(hashMap);
  }

  @Override
  public int update(JobVO jobVO) {
    return jobDAO.update(jobVO);
  }
  
  @Override
  public JobVO update(int jobNo) {
    JobVO jobVO = jobDAO.read(jobNo);
    
    return jobVO;
  }

  @Override
  public int update_thumb(JobVO jobVO) {
    return jobDAO.update_thumb(jobVO);
  }
  
  @Override
  public JobVO update_thumb(int jobNo) {
    JobVO jobVO = jobDAO.read(jobNo);
    
    return jobVO;
  }

  @Override
  public JobVO update_file(int jobNo) {
    JobVO jobVO = jobDAO.read(jobNo);
    
    return jobVO;
  }

  @Override
  public int update_file(JobVO jobVO) {
    return jobDAO.update_file(jobVO);
  }
  
  @Override
  public int delete(int jobNo) {
    return jobDAO.delete(jobNo);
  }

  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String jobWord){ 
    int totalPage = (int)(Math.ceil((double)search_count/Job.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Job.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Job.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Job.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Job.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 0px;");  //str.append("    border: 1px;");
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    border-radius: 5px;");    
    str.append("    background-color: #FECE1A;"); 
    str.append("    color: #666666;"); 
    str.append("    font-size: 1em;"); 
    str.append("    font-weight: bold;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #ffffff;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }");  
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
//    str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 
 
    int _nowPage = (nowGrp-1) * Contest.PAGE_PER_BLOCK; // ���� �������� �̵� 
    
      if (nowGrp >= 2){ 
        str.append("<span class='span_box_1'><A href='./list_all_jobinfo.do?jobWord="+jobWord+"&nowPage="+_nowPage+"'>����</A></span>"); 
      } 
   
      for(int i=startPage; i<=endPage; i++){ 
        if (i > totalPage){ 
          break; 
        } 
    
        if (nowPage == i){ 
          str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
        }else{
          // ���� �������� �ƴ� ������
          str.append("<span class='span_box_1'><A href='./list_all_jobinfo.do?jobWord="+jobWord+"&nowPage="+i+"'>"+i+"</A></span>");   
        } 
      } 
       
      _nowPage = (nowGrp * Contest.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
      if (nowGrp < totalGrp){ 
        str.append("<span class='span_box_1'><A href='./list_all_jobinfo.do?&jobWord="+jobWord+"&nowPage="+_nowPage+"'>����</A></span>"); 
      } 
      str.append("</DIV>"); 
     
    return str.toString(); 
  } 
  
}
