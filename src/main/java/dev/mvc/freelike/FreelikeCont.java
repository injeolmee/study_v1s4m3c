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
   * ȸ���� ���õ� ���ƿ� üũ �Է� Ȯ��
   * @param session �α��ε� ȸ����ȣ
   * @param freeno �۹�ȣ
   * @return count => üũ�Ǿ��ٸ� 1, �ƴϸ� 2�� ��µ�.
   */
  @ResponseBody
  @RequestMapping(value="/user/freelike/like.do", method = RequestMethod.GET, produces="application/text;charset=UTF-8")
  public String good(HttpSession session, int freeno) {
    
    JSONObject obj = new JSONObject();
    int count = 2; // ȸ���� üũ���ο� ���õ� ���� ���� (����Ʈ�� 2)
    
    if (session.getAttribute("memberno") != null) { // session ���� ȸ����ȣ�� null�� �ƴ϶�� (�� �α����� �Ǿ� �ִٸ�)
      HashMap hashMap = new HashMap();
      
      int memberno =  (Integer)session.getAttribute("memberno");
 
      hashMap.put("freeno", freeno);
      hashMap.put("memberno", memberno);
      
      count = freelikeProc.check(hashMap); // �̹� üũ�� ȸ������ Ȯ��
    }
   
    obj.put("count", count);
    
    return obj.toJSONString();
  }
  
  /**
   * ���ƿ� Ŭ���� 0���� 1�� ����
   * @return String
   */
  @ResponseBody
  @RequestMapping(value = "/user/freelike/like_up.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String good_up(HttpSession session, int freeno) {
    
    JSONObject obj = new JSONObject();
    int count = 0;
    
    if (session.getAttribute("memberno") != null) { // session ���� ȸ����ȣ�� null�� �ƴ϶�� (�� �α����� �Ǿ� �ִٸ�)
      HashMap hashmap = new HashMap();
      
      int memberno =  (Integer) session.getAttribute("memberno");
      
      hashmap.put("freeno", freeno);
      hashmap.put("memberno", memberno);
      
      count = freelikeProc.good_chk_y(hashmap);
      freeProc.increaseLike(freeno); // ���ƿ並 1�� ����
    }
    
    obj.put("count", count);
    
    return obj.toString();
  }
  
  /**
   * ���ƿ� Ŭ���� 1���� 0���� ����
   * @return String
   */
  @ResponseBody
  @RequestMapping(value = "/user/freelike/like_down.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String good_down(HttpSession session, int freeno) {
    
    JSONObject obj = new JSONObject();
    int count = 0;    
    
    if (session.getAttribute("memberno") != null) { // session ���� ȸ����ȣ�� null�� �ƴ϶�� (�� �α����� �Ǿ� �ִٸ�)
      HashMap hashmap = new HashMap();
          
      int memberno =  (Integer) session.getAttribute("memberno");

      hashmap.put("freeno", freeno);
      hashmap.put("memberno", memberno);
      
      count = freelikeProc.good_chk_n(hashmap);
      freeProc.decreaseLike(freeno); // ���ƿ並 0���� ����
    }
    obj.put("count", count);
    
    return obj.toString();
  }
  
}
