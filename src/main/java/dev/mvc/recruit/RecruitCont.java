package dev.mvc.recruit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.studylist.StudyListProcInter;
import dev.mvc.studylist.StudyListVO;


@Controller
public class RecruitCont {

  @Autowired
  @Qualifier("dev.mvc.recruit.RecruitProc")
    private RecruitProcInter recruitProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.studylist.StudyListProc")
  private StudyListProcInter StudyListProc = null;
  
  public RecruitCont(){
    // System.out.println("-->RecruitCont created");
  }
  
  /**
   * 회원이 신청하게 되면 recruit에 저장
   * @param recruitVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/recruit/join.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String join(HttpSession session, RecruitVO recruitVO) {
    System.out.println("--> create() POST executed");
    
    JSONObject obj = new JSONObject();
    
    int memberno = (Integer) session.getAttribute("memberno");
       
    recruitVO.setMemberno(memberno);
    
    int count =  recruitProc.create(recruitVO);
        
    return obj.toJSONString();
  }
    
  
  /**
   * 스터디그룹별로 신청 리스트 출력
   * 신청리스트와 멤버테이블 조인
   * @param stdlist_no
   * @return
   */
  // http://localhost:9090/blog/studylist/list.do
  @RequestMapping(value = "/user/recruit/recruit_list.do", method = RequestMethod.GET)
  public ModelAndView list(int stdlist_no) {
    System.out.println("--> list() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/recruit/recruit_list"); // studylist/study_list.jsp
    
    List<Recruit_MemberVO> recruit_list = recruitProc.recruit_list(stdlist_no);
    
    mav.addObject("recruit_list", recruit_list);

    return mav;
  }
  
  
  /**
   * 스터디 신청리스트에 신청하게 되면 stdlist_no에 이미 신청된 memberno 인지 검사 
   * @param recruitVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/recruit/check_memberno.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String check_memberno(HttpSession session, RecruitVO recruitVO) {
    
    System.out.println("--> check_memberno() GET executed");
    
    //session을 통해 id의 memberno값을 가져오기.
    int memberno =  (Integer) session.getAttribute("memberno");
    
    int stdlist_no = recruitVO.getStdlist_no();
    
    
    HashMap hashmap = new HashMap();
    
    System.out.println("memberno:"+memberno);
    System.out.println("stdlist_no:"+stdlist_no);
    
    hashmap.put("memberno", memberno);
    hashmap.put("stdlist_no", stdlist_no);
    
    JSONObject obj = new JSONObject();
   
    int count =  recruitProc.check_memberno(hashmap);
    
    System.out.println("count:"+count);
     
    obj.put("count", count);
    obj.put("stdlist_no", stdlist_no);
     
    
    return obj.toString();
  }
  
  /**
   * 스터디그룹 팀장이 신청인원을 승인한다.
   * @param recruitVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/recruit/confirm_Y.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String confirm_Y(RecruitVO recruitVO) {
    
    System.out.println("--> confirm_Y() GET executed");
    
    JSONObject obj = new JSONObject();
    
    int stdlist_no = recruitVO.getStdlist_no();
    int memberno = recruitVO.getMemberno();
    
    HashMap hashmap = new HashMap();
    
    hashmap.put("stdlist_no", stdlist_no);
    hashmap.put("memberno", memberno);
    
    int count = recruitProc.confirm_Y(hashmap);

    StudyListProc.up_currnum(stdlist_no);
    
    System.out.println("count :"+count);
    
    obj.put("count", count);
    obj.put("stdlist_no", stdlist_no);
    
    return obj.toString(); 
  }
  
  /**
   * 스터디그룹 팀장이 신청인원을 거절한다.
   * @param recruitVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/recruit/confirm_N.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String confirm_N(RecruitVO recruitVO) {
    
    System.out.println("--> confirm_N() GET executed");
    
    JSONObject obj = new JSONObject();
    
    int stdlist_no = recruitVO.getStdlist_no();
    int memberno = recruitVO.getMemberno();
    
    System.out.println("memberno:"+memberno);
    
    HashMap hashmap = new HashMap();
    
    hashmap.put("stdlist_no", stdlist_no);
    hashmap.put("memberno", memberno);
    
    int count = recruitProc.confirm_N(hashmap);
    
    System.out.println("count :"+count);
    
    obj.put("count", count);
    obj.put("stdlist_no", stdlist_no);
    
    return obj.toString(); 
  }
  
  
}
