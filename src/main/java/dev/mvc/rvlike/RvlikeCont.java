package dev.mvc.rvlike;

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

import dev.mvc.review.ReviewProcInter;
import dev.mvc.review.ReviewVO;
import net.sf.json.JSONArray;

@Controller
public class RvlikeCont {
  @Autowired
  @Qualifier("dev.mvc.rvlike.RvlikeProc")
  private RvlikeProcInter rvlikeProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc")
  private ReviewProcInter reviewProc = null;
  
  public RvlikeCont() {
    
  }
  
  /**
   * 하트 좋아요클릭시 0 -> 1
   * 
   * @return String
   */
  @ResponseBody
  @RequestMapping(value = "/user/rvlike/like_up.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String like_up(HttpSession session, int rvno) {
    
    JSONObject obj = new JSONObject();
    int count = 0;    
    
    if (session.getAttribute("memberno") != null) {
          
      int memberno =  (Integer) session.getAttribute("memberno");
      
      HashMap hashmap = new HashMap();
      
      hashmap.put("rvno", rvno);
      hashmap.put("memberno", memberno);
      
      rvlikeProc.like_chk_y(hashmap);
      count = reviewProc.likecnt_up(rvno);
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
  @RequestMapping(value = "/user/rvlike/like_down.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String like_down(HttpSession session, int rvno) {
    
    JSONObject obj = new JSONObject();
    int count = 0;    
    
    if (session.getAttribute("memberno") != null) {
          
      int memberno =  (Integer) session.getAttribute("memberno");
      
      HashMap hashmap = new HashMap();
      
      hashmap.put("rvno", rvno);
      hashmap.put("memberno", memberno);
      
      rvlikeProc.like_chk_n(hashmap);
      count = reviewProc.likecnt_down(rvno);  
    }
    obj.put("count", count);
    
    return obj.toString();
  }
  
}
