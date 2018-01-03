package dev.mvc.categrp;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CategrpCont {
  @Autowired
  @Qualifier("dev.mvc.categrp.CategrpProc")
  private CategrpProcInter categrpProc;
  
  public CategrpCont() {
    // System.out.println("--> CategrpCont created");
  }
  
  
  /**
   * 카테그룹 등록
   * http://localhost:9090/study/admin/categrp/grp_create.do
   * @return
   */
  @RequestMapping(value = "/admin/categrp/grp_create.do", method = RequestMethod.GET)
  public ModelAndView grp_create() {
    // System.out.println("--> grp_create() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("admin/categrp/grp_create"); // webapp/category/create.jsp

    return mav;
  }
  
  
  /**
   * 카테그룹 등록 처리
   * @param categrpVO
   * @return
   */
  @RequestMapping(value = "/admin/categrp/grp_create.do", method = RequestMethod.POST)
  public ModelAndView grp_create(CategrpVO categrpVO) {
    // System.out.println("--> grp_create() POST executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("admin/categrp/message"); // webapp/category/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    if (categrpProc.grp_create(categrpVO) == 1) {
      mav.setViewName("redirect:/admin/categrp/grp_list.do");
    } else {
      msgs.add("카테그룹 등록 실패<br>");
      links.add("<button type= 'button' onclick=\"history.back()\">다시 시도</button>");
    }

    links.add("<button type= 'button' onclick=\"location.href='./grp_list.do'\">목록</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }
  
  
  /**
   * 카테그룹 리스트
   * http://localhost:9090/study/admin/categrp/grp_list.do
   * @return
   */
  @RequestMapping(value = "/admin/categrp/grp_list.do", method = RequestMethod.GET)
  public ModelAndView grp_list() {
    // System.out.println("--> list() POST executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("admin/categrp/grp_list"); 
       
    List<CategrpVO> grp_list = categrpProc.grp_list();
    mav.addObject("grp_list", grp_list);
   
    return mav;
  }
  
  
  /**
   * 카테그룹 수정
   * http://localhost:9090/study/admin/categrp/grp_update.do
   * @param grpno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/admin/categrp/grp_update.do", method = RequestMethod.GET, produces= "text/plain;charset=UTF-8")
  public String grp_update(int grpno) {
     // System.out.println("--> grp_update() GET executed");
    
    CategrpVO categrpVO = categrpProc.grp_read(grpno);
    
    JSONObject obj = new JSONObject();
    obj.put("grpno", categrpVO.getGrpno());
    obj.put("grpname", categrpVO.getGrpname());
    obj.put("grpseqno", categrpVO.getGrpseqno());
    obj.put("grpvisible", categrpVO.getGrpvisible());
   
    return obj.toJSONString();
  }
  
  
  /**
   * 카테그룹 수정 처리
   * @param categrpVO
   * @return
   */
  @RequestMapping(value = "/admin/categrp/grp_update.do", method = RequestMethod.POST)
  public ModelAndView grp_update(CategrpVO categrpVO) {
    // System.out.println("--> grp_update() POST executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("admin/categrp/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    if (categrpProc.grp_update(categrpVO) == 1) {
      mav.setViewName("redirect:/admin/categrp/grp_list.do");
    } else {
      msgs.add("카테그룹 변경 실패<br>");
      links.add("<button type= 'button' onclick=\"history.back()\">다시 시도</button>");
    }

    links.add("<button type= 'button' onclick=\"location.href='./grp_list.do'\">목록</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }
  
  
  /**
   * 카테그룹 우선순위 높임
   * @param grpno
   * @return
   */
  @RequestMapping(value = "/admin/categrp/grpseqno_up.do", method = RequestMethod.POST)
  public ModelAndView grpseqno_up(int grpno) {
    // System.out.println("--> grpseqno_up() POST executed");
    ModelAndView mav = new ModelAndView();
    
    if (categrpProc.grpseqno_up(grpno) == 1) {
      mav.setViewName("redirect:/admin/categrp/grp_list.do");
    }
    
    return mav;
  }
  
  
  /**
   * 카테그룹 우선순위 낮춤
   * @param grpno
   * @return
   */
  @RequestMapping(value = "/admin/categrp/grpseqno_down.do", method = RequestMethod.POST)
  public ModelAndView grpseqno_down(int grpno) {
    // System.out.println("--> grpseqno_down() POST executed");
    ModelAndView mav = new ModelAndView();
    
    if (categrpProc.grpseqno_down(grpno) == 1) {
      mav.setViewName("redirect:/admin/categrp/grp_list.do");
    }
    
    return mav;
  }
  

  /**
   * 카테그룹 삭제
   * @param grpno
   * @return
   */
  @RequestMapping(value = "/admin/categrp/grp_delete.do", method = RequestMethod.POST)
  public ModelAndView grp_delete(int grpno) {
    // System.out.println("--> grp_delete() POST executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("admin/categrp/message");
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (categrpProc.grp_delete(grpno) == 1) {
      mav.setViewName("redirect:/admin/categrp/grp_list.do");
    } else {
      msgs.add("카테고리 삭제 실패<br>");
      links.add("<button type= 'button' onclick=\"history.back()\">다시 시도</button>");
    }
    
    links.add("<button type= 'button' onclick=\"location.href='./grp_list.do'\">목록</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  
  /**
   * 카테그룹 번호 중복 검사
   * @param cateno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/admin/categrp/grpno_check.do", method = RequestMethod.GET, produces= "text/plain;charset=UTF-8")
  public String grpno_check(int grpno) {
    JSONObject obj = new JSONObject();

    int cnt = categrpProc.grpno_check(grpno);
    
    // System.out.println("cnt :" +cnt);
    obj.put("cnt", cnt);

    return obj.toJSONString();
  }
  
  
  


}
