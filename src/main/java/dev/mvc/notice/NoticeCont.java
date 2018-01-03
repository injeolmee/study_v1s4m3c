package dev.mvc.notice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.notice_reply.NrepProcInter;
import dev.mvc.notice_reply.NrepVO;
import dev.mvc.studylist.StudyListVO;
import dev.mvc.studylist_reply.ReplyVO;
import nation.web.tool.Tool;
import net.sf.json.JSONArray;

@Controller
public class NoticeCont {
  @Autowired
  @Qualifier("dev.mvc.notice.NoticeProc")
  private NoticeProcInter noticeProc;
  
  @Autowired
  @Qualifier("dev.mvc.notice_reply.NrepProc")
  private NrepProcInter nrepProc;
  
  public NoticeCont() {
  }
  
  
  /**
   * �������� ���
   * http://localhost:9090/study/admin/notice/notice_create.do
   * @return
   */
  @RequestMapping(value = "/admin/notice/notice_create.do", method= RequestMethod.GET)
  public ModelAndView notice_create() {
    ModelAndView mav = new ModelAndView();
    System.out.println("�������� ���");
    mav.setViewName("/admin/notice/notice_create"); 
    return mav;
  }
  
  
  /**
   * �������� ��� ó��
   * @param request
   * @param noticeVO
   * @return
   */
  @RequestMapping(value = "/admin/notice/notice_create.do", method= RequestMethod.POST)
  public ModelAndView notice_create(HttpServletRequest request, NoticeVO noticeVO) {
    System.out.println("�������� ó��");
    String root = request.getContextPath(); 
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/notice/message");
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>(); 
    
    if (noticeProc.notice_create(noticeVO) == 1) {
      mav.setViewName("redirect:/nonuser/notice/notice_list.do"); 
    } else {
      msgs.add("�������� ��� ����<br>");
      links.add("<button type= 'button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links.add("<button type= 'button' onclick=\"location.href='" + root + "/nonuser/notice/notice_list.do'\">���</button>");
    }
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }
  
  
  /**
   * �������� ���
   * @param noticeVO
   * @return
   */
  @RequestMapping(value="/nonuser/notice/notice_list.do", method=RequestMethod.GET)
  public ModelAndView notice_list(NoticeVO noticeVO){
    // System.out.println("--> notice_list() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/notice/notice_list"); // 
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    String word = noticeVO.getWord();
    String search = noticeVO.getSearch();
    int nowPage = noticeVO.getNowPage();
    
    word = Tool.checkNull(word);
    
    System.out.println("word:" + word);
    System.out.println("search:" + search);
    System.out.println("nowPage:" + nowPage);
    
    hashMap.put("word", word);
    hashMap.put("search", search);
    hashMap.put("nowPage", nowPage);
    mav.addObject("nowPage", nowPage);
        
    List<NoticeVO> notice_list = noticeProc.notice_list(hashMap);
    mav.addObject("notice_list", notice_list);
    
    int search_count = noticeProc.search_count(hashMap); // �˻� ���ڵ� ����
    mav.addObject("search_count", search_count);
    System.out.println("search_count: " + search_count);
    
    String paging = noticeProc.paging(search_count, nowPage, word, search);
    mav.addObject("paging", paging);
    
    return mav;
  }
  
  
  /**
   * �������� ��ȸ
   * @param noticeno
   * @return
   */
  @RequestMapping(value="/nonuser/notice/notice_read.do", method=RequestMethod.GET)
  public ModelAndView notice_read(int noticeno, int nowPage, NrepVO nrepVO){
    // System.out.println("--> notice_read GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/notice/notice_read"); // webapp/member/mem_read.jsp
    
    HashMap hashMap = new HashMap();

    // ����� �о���� ���� ���� ��
    noticeno = nrepVO.getNoticeno();
    int nrepno = nrepVO.getNrepno();

    hashMap.put("noticeno", noticeno);
    hashMap.put("nrepno", nrepno);
    
    nrepVO = nrepProc.nrep_read(hashMap);
    NoticeVO noticeVO = noticeProc.notice_read(noticeno); 
    noticeProc.ncnt_up(noticeno);
    mav.addObject("nowPage", nowPage);
    mav.addObject("noticeVO", noticeVO);
    
    return mav;
  } 
  
  
  /**
   * �������� ���� ��
   * @param noticeno
   * @return
   */
  @RequestMapping(value = "/admin/notice/notice_update.do", method = RequestMethod.GET)
  public ModelAndView notice_update(int noticeno, int nowPage) {
    // System.out.println("--> notice_update() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/notice/notice_update"); 
  
    NoticeVO noticeVO = noticeProc.notice_read(noticeno);
    
    mav.addObject("noticeVO", noticeVO);
    mav.addObject("nowPage", nowPage);
    return mav;
  }
  
  
  /**
   * �������� ���� ó��
   * @param request
   * @param noticeVO
   * @return
   */
  @RequestMapping(value="/admin/notice/notice_update.do", method=RequestMethod.POST)
  public ModelAndView notice_update(HttpServletRequest request, NoticeVO noticeVO, int nowPage) {
    System.out.println("--> notice_update() POST called.");
    String root = request.getContextPath();
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/notice/message"); 
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (noticeProc.notice_update(noticeVO) == 1) {
      mav.setViewName("redirect:/nonuser/notice/notice_read.do?noticeno="+noticeVO.getNoticeno()+"&nowPage="+nowPage+"&word="+noticeVO.getWord()+"&search="+noticeVO.getSearch()); 
      
    } else {
      msgs.add("�������� ������ �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links.add("<button type= 'button' onclick=\"location.href='" + root + "/nonuser/notice/notice_list.do?nowPage='"+nowPage+"&search="+noticeVO.getSearch()+"&word="+noticeVO.getWord()+"'\">���</button>");
    }
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  
  /**
   * ��� ���� ����
   * @param noticeno
   * @return
   */
  @RequestMapping(value = "/admin/notice/nseqno_up.do", method = RequestMethod.POST)
  public ModelAndView nseqno_up(int noticeno) {
    // System.out.println("--> nseqno_up() POST executed");
    ModelAndView mav = new ModelAndView();
    
    if (noticeProc.nseqno_up(noticeno) == 1) {
      mav.setViewName("redirect:/nonuser/notice/notice_list.do"); 
    }
    
    return mav;
  }
  
  
  /**
   * ��� ���� ����
   * @param noticeno
   * @return
   */
  @RequestMapping(value = "/admin/notice/nseqno_down.do", method = RequestMethod.POST)
  public ModelAndView nseqno_down(int noticeno) {
    // System.out.println("--> nseqno_down() POST executed");
    ModelAndView mav = new ModelAndView();
    
    if (noticeProc.nseqno_down(noticeno) == 1) {
      mav.setViewName("redirect:/nonuser/notice/notice_list.do"); 
    }
    
    return mav;
  }
  
  
  /**
   * �������� ���� ��
   * @param noticeno
   * @return
   */
  @RequestMapping(value = "/admin/notice/notice_delete.do", method = RequestMethod.GET)
  public ModelAndView notice_delete(int noticeno) {
    // System.out.println("--> notice_delete() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/notice/notice_delete"); 

    NoticeVO noticeVO = noticeProc.notice_read(noticeno);
    mav.addObject("noticeVO", noticeVO);

    return mav;
  }
  
  
  /**
   * �������� ���� ó��
   * @param request
   * @param noticeVO
   * @param nowPage
   * @return
   */
  @RequestMapping(value = "/admin/notice/notice_delete.do", method = RequestMethod.POST)
  public ModelAndView notice_delete(HttpServletRequest request, NoticeVO noticeVO) {
    System.out.println("--> notice_delete() POST executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/notice/message"); 
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    String root = request.getContextPath();
    
    System.out.println(noticeVO.getNoticeno());
    
    if (noticeProc.notice_delete(noticeVO.getNoticeno()) == 1) {
      mav.setViewName("redirect:/nonuser/notice/notice_list.do"); 
    } else {
      msgs.add("��α� �� ���� ����<br>");
      links.add("<button type= 'button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links.add("<button type= 'button' onclick=\"location.href='" + root + "/home.do'\">����</button>");
    }
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  
  
  

}
