package dev.mvc.freelike;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.free.FreeProcInter;

@Controller
public class FreelikeCont {
  
  @Autowired
  @Qualifier("dev.mvc.freelike.FreelikeProc")
  private FreelikeProcInter freelikeProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.free.FreeProc")
  private FreeProcInter freeProc = null;
  
  public FreelikeCont() {
    // System.out.println(" => FreelikeCont created");
  }
  
  /**
   * 회원과 관련된 좋아요 체크 입력 확인
   * @param session 로그인된 회원번호
   * @param freeno 글번호
   * @return count => 체크되었다면 1, 아니면 2가 출력됨.
   */
  @ResponseBody
  @RequestMapping(value="/user/freelike/like.do", method = RequestMethod.GET, produces="application/text;charset=UTF-8")
  public String good(HttpSession session, int freeno) {
    
    JSONObject obj = new JSONObject();
    int count = 2; // 회원의 체크여부와 관련된 변수 선언 (디폴트값 2)
    
    if (session.getAttribute("memberno") != null) { // session 안의 회원번호가 null이 아니라면 (즉 로그인이 되어 있다면)
      HashMap hashMap = new HashMap();
      
      int memberno =  (Integer)session.getAttribute("memberno");
 
      hashMap.put("freeno", freeno);
      hashMap.put("memberno", memberno);
      
      count = freelikeProc.check(hashMap); // 이미 체크된 회원인지 확인
    }
   
    obj.put("count", count);
    
    return obj.toJSONString();
  }
  
  /**
   * 좋아요 클릭시 0에서 1로 변경
   * @return String
   */
  @ResponseBody
  @RequestMapping(value = "/user/freelike/like_up.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String good_up(HttpSession session, int freeno) {
    
    JSONObject obj = new JSONObject();
    int count = 0;
    
    if (session.getAttribute("memberno") != null) { // session 안의 회원번호가 null이 아니라면 (즉 로그인이 되어 있다면)
      HashMap hashmap = new HashMap();
      
      int memberno =  (Integer) session.getAttribute("memberno");
      
      hashmap.put("freeno", freeno);
      hashmap.put("memberno", memberno);
      
      count = freelikeProc.good_chk_y(hashmap);
      freeProc.increaseLike(freeno); // 좋아요를 1로 변경
    }
    
    obj.put("count", count);
    
    return obj.toString();
  }
  
  /**
   * 좋아요 클릭시 1에서 0으로 변경
   * @return String
   */
  @ResponseBody
  @RequestMapping(value = "/user/freelike/like_down.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String good_down(HttpSession session, int freeno) {
    
    JSONObject obj = new JSONObject();
    int count = 0;    
    
    if (session.getAttribute("memberno") != null) { // session 안의 회원번호가 null이 아니라면 (즉 로그인이 되어 있다면)
      HashMap hashmap = new HashMap();
          
      int memberno =  (Integer) session.getAttribute("memberno");

      hashmap.put("freeno", freeno);
      hashmap.put("memberno", memberno);
      
      count = freelikeProc.good_chk_n(hashmap);
      freeProc.decreaseLike(freeno); // 좋아요를 0으로 변경
    }
    obj.put("count", count);
    
    return obj.toString();
  }
  
}
