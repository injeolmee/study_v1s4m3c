package dev.mvc.std_recom;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.studylist.StudyListProcInter;
import net.sf.json.JSONObject;

@Controller
public class Std_RecomCont {

  @Autowired
  @Qualifier("dev.mvc.std_recom.Std_RecomProc")
  private Std_RecomProcInter std_recomProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.studylist.StudyListProc")
  private StudyListProcInter studylistProc = null;
  
  public Std_RecomCont() {
    // System.out.println("-->Std_RecomCont created");
  }

  
  /**
   * 하트출력
   * 
   * @return String
   */
  @ResponseBody
  @RequestMapping(value = "/nonuser/std_recom/heart.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String heart(HttpSession session, int stdlist_no) {
    // System.out.println("heart()");
    
    JSONObject obj = new JSONObject();
  
    int count = 2;
    
    if (session.getAttribute("memberno") != null) {
      
      int memberno =  (Integer) session.getAttribute("memberno");
      
      HashMap hashmap = new HashMap();
      
      hashmap.put("stdlist_no", stdlist_no);
      hashmap.put("memberno", memberno);
      
        count = std_recomProc.check(hashmap);
    }
   
    obj.put("count", count);
    
    // System.out.println(obj);
    
    return obj.toString();
  }
  
  
  /**
   * 하트 좋아요클릭시 0 -> 1
   * 
   * @return String
   */
  @ResponseBody
  @RequestMapping(value = "/user/std_recom/heart_up.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String heart_up(HttpSession session, int stdlist_no) {
    
    JSONObject obj = new JSONObject();
    int count = 0;    
    
    if (session.getAttribute("memberno") != null) {
          
          int memberno =  (Integer) session.getAttribute("memberno");
          
          HashMap hashmap = new HashMap();
          
          hashmap.put("stdlist_no", stdlist_no);
          hashmap.put("memberno", memberno);
          
          count = std_recomProc.good_ch_Y(hashmap);
          studylistProc.goodcnt_up(stdlist_no);
        }
    obj.put("count", count);
    
    return obj.toString();
  }
  
  /**
   * 하트 좋아요클릭시 1 -> 0
   * 
   * @return String
   */
  @ResponseBody
  @RequestMapping(value = "/user/std_recom/heart_down.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String heart_down(HttpSession session, int stdlist_no) {
    
    JSONObject obj = new JSONObject();
    int count = 0;    
    
    if (session.getAttribute("memberno") != null) {
          
          int memberno =  (Integer) session.getAttribute("memberno");
          
          HashMap hashmap = new HashMap();
          
          hashmap.put("stdlist_no", stdlist_no);
          hashmap.put("memberno", memberno);
          
          count = std_recomProc.good_ch_N(hashmap);
          studylistProc.goodcnt_down(stdlist_no);
        }
    obj.put("count", count);
    
    return obj.toString();
  }
  
}
