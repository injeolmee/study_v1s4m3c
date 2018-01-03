package dev.mvc.conlike;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.contest.ContestProcInter;

@Controller
public class ConlikeCont {
  @Autowired
  @Qualifier("dev.mvc.conlike.ConlikeProc")
  private ConlikeProcInter conlikeProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.contest.ContestProc")
  private ContestProcInter contestProc = null;
  
  public ConlikeCont() {
    
  }
  
  @ResponseBody
  @RequestMapping(value="/conlike/good.do", method = RequestMethod.GET, produces="application/text;charset=UTF-8")
  public String good(HttpSession session, int conNo) {
    JSONObject obj = new JSONObject();
    
    int count = 2;
    
    if (session.getAttribute("memberno") != null) {
      int memberno =  (Integer) session.getAttribute("memberno");
      
      HashMap hashmap = new HashMap();
      
      hashmap.put("conNo", conNo);
      hashmap.put("memberno", memberno);
      
      count = conlikeProc.check(hashmap);
    }
   
    obj.put("count", count);
    
    return obj.toJSONString();
  }
  

  /**
   * 하트 좋아요클릭시 0 -> 1
   * 
   * @return String
   */
  @ResponseBody
  @RequestMapping(value = "/conlike/good_up.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String good_up(HttpSession session, int conNo) {
    
    JSONObject obj = new JSONObject();
    int count = 0;    
    
    if (session.getAttribute("memberno") != null) {
          
      int memberno =  (Integer) session.getAttribute("memberno");
      
      HashMap hashmap = new HashMap();
      
      hashmap.put("conNo", conNo);
      hashmap.put("memberno", memberno);
      
      count = conlikeProc.good_chk_y(hashmap);
      contestProc.goodcnt_up(conNo);
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
  @RequestMapping(value = "/conlike/good_down.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String good_down(HttpSession session, int conNo) {
    
    JSONObject obj = new JSONObject();
    int count = 0;    
    
    if (session.getAttribute("memberno") != null) {
          
      int memberno =  (Integer) session.getAttribute("memberno");
      
      HashMap hashmap = new HashMap();
      
      hashmap.put("conNo", conNo);
      hashmap.put("memberno", memberno);
      
      count = conlikeProc.good_chk_n(hashmap);
      contestProc.goodcnt_down(conNo);  
    }
    obj.put("count", count);
    
    return obj.toString();
  }
  
}
