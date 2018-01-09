package dev.mvc.mystudy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.studylist.StudyListVO;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class MyStudyCont {
  
  @Autowired
  @Qualifier("dev.mvc.study.StudyProc")
  private StudyProcInter studyProc;
  
  @RequestMapping(value="/main.do", method=RequestMethod.GET)
  public ModelAndView main(){
    System.out.println(" --> main.jsp ȣ��");
    ModelAndView mav=new ModelAndView();
    mav.setViewName("/main/index");
    
    return mav; 
  }
  
  @RequestMapping(value="/user/mystudy/mystudy.do", method=RequestMethod.GET)
  public ModelAndView myStudy(HttpServletRequest request){
    System.out.println(" --> mystudy_main.jsp ȣ��");
    ModelAndView mav=new ModelAndView();
    mav.setViewName("/user/mystudy/mystudy_main");
    
    HttpSession session=request.getSession(false);
    int memberno=(Integer)session.getAttribute("memberno");
    
    session.removeAttribute("std_auth"); 
    
    System.out.println(" --> MyStudyCont.java - memberno:"+memberno);
    
    List<StudyListVO> mystudylist=studyProc.mystudylist(memberno);
    
    for(int i=0; i<mystudylist.size(); i++){
      StudyListVO listVO=mystudylist.get(i);
      System.out.println(listVO.getStdlist_title());
    }
    
    mav.addObject("mystudylist", mystudylist);
    
    return mav;
  }
  
  /**
   * ���� ��û ��Ȳ.
   * @return
   */
  
  @RequestMapping(value="/user/mystudy/my_apply_list.ajax", method=RequestMethod.GET) 
  public ModelAndView my_apply_list(HttpServletRequest request){
    System.out.println(" --> my_apply_list() ȣ��");
    
    ModelAndView mav=new ModelAndView();
    
    // ���� �α��� ���� ȸ���� ������ ���� "ȸ����ȣ" ��ȸ
    HttpSession session=request.getSession(false);
    int memberno=(Integer)session.getAttribute("memberno");
    
    List<My_apply_listVO> my_apply_list=studyProc.my_apply_list(memberno);

    mav.addObject("result", my_apply_list);
    
    mav.setViewName("jsonView");

    return mav;
  }
  
  // @ResponseBody : �ڹ� ��ü�� HTTP���� ��ü�� ��ȯ.
  // @RequestBody  : Http ��û ��ü�� �ڹ� ��ü�� ��ȯ.
  
  @ResponseBody 
  @RequestMapping(value="/user/mystudy/cancel_apply.ajax", method=RequestMethod.POST) 
  public String cancel_apply(HttpServletRequest request, String[] recuritno){
    // ���ڿ��� ���� ��.
    // @RequestBody �� ������ URL�� �Ķ������ ���� �״�� �޾�(���ڿ�) ���δ�. --> String recuritno �� ��.
    // ������, @RequestBody�� ������ ���� ,(�޸�)�� �������� ���ڿ��� �޴´�.  ex) 2,1
    
    // ���� �迭�� ���� ��.
    // String[] recuritno �̸�, Ű, �� �߿� ������ ���� �迭�� �����Ѵ�. ex) recuritno[0]= 2, recuritno[1]=1...
    // @RequestBody�� ������ 415������ �߻��Ѵ�. --> Not Surportted Media Type
    
    HttpSession session=request.getSession(false);
    
    int memberno=(Integer)session.getAttribute("memberno");
    
    int count=0;
    int result=0;
    for(int i=0; i<recuritno.length; i++){
      System.out.println(recuritno[i]);
      count=studyProc.cancel_apply(Integer.parseInt(recuritno[i]));
      
      if(count==1){
        result=result+1;
      }
    }
     
    JSONObject obj=new JSONObject();
    
    if(recuritno.length==result){
      // �������� ��û ����Ʈ�� ���������� ���� ��.
      // ���� ��û�� ���� == ������ ����
      obj.put("result", "OK");
    }else{
      obj.put("result", "FAILED");
    }
    
    return obj.toString();
  }
 

  
  // AJAX�� ���� ȸ�� ��ȣ�� �̿��� ȸ�� ���� ��ȸ. JSON ���� ����.
  /*@ResponseBody
  @RequestMapping(value="/test/meminfo.do", method=RequestMethod.GET, produces="text/plain; charset=UTF-8")
  public String meminfo(int memberno){
    System.out.println(" --> meminfo.do ȣ��(GET)");
    
    JSONObject obj=new JSONObject();
    
    MeminfoVO meminfoVO=meminfoProc.read(memberno);
    
    System.out.println("memberno : "+meminfoVO.getMemberno());
    System.out.println("membername : "+meminfoVO.getMembername());
    System.out.println("memberPasswd : "+meminfoVO.getPasswd());
    
    obj.put("memberno", memberno);
    obj.put("name", meminfoVO.getMembername());
    obj.put("passwd", meminfoVO.getPasswd());
    
    return obj.toJSONString();
  }*/
  
}
