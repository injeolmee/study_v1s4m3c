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
    System.out.println(" --> main.jsp 호출");
    ModelAndView mav=new ModelAndView();
    mav.setViewName("/main/index");
    
    return mav; 
  }
  
  @RequestMapping(value="/user/mystudy/mystudy.do", method=RequestMethod.GET)
  public ModelAndView myStudy(HttpServletRequest request){
    System.out.println(" --> mystudy_main.jsp 호출");
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
   * 나의 신청 현황.
   * @return
   */
  
  @RequestMapping(value="/user/mystudy/my_apply_list.ajax", method=RequestMethod.GET) 
  public ModelAndView my_apply_list(HttpServletRequest request){
    System.out.println(" --> my_apply_list() 호출");
    
    ModelAndView mav=new ModelAndView();
    
    // 현재 로그인 중인 회원의 세션을 통해 "회원번호" 조회
    HttpSession session=request.getSession(false);
    int memberno=(Integer)session.getAttribute("memberno");
    
    List<My_apply_listVO> my_apply_list=studyProc.my_apply_list(memberno);

    mav.addObject("result", my_apply_list);
    
    mav.setViewName("jsonView");

    return mav;
  }
  
  // @ResponseBody : 자바 객체를 HTTP응답 몸체로 변환.
  // @RequestBody  : Http 요청 몸체를 자바 객체로 변환.
  
  @ResponseBody 
  @RequestMapping(value="/user/mystudy/cancel_apply.ajax", method=RequestMethod.POST) 
  public String cancel_apply(HttpServletRequest request, String[] recuritno){
    // 문자열로 받을 때.
    // @RequestBody 가 있으면 URL의 파라미터의 형태 그대로 받아(문자열) 들인다. --> String recuritno 일 때.
    // 하지만, @RequestBody가 없으면 값만 ,(콤마)를 기준으로 문자열로 받는다.  ex) 2,1
    
    // 문자 배열로 받을 때.
    // String[] recuritno 이면, 키, 값 중에 값만을 문자 배열에 저장한다. ex) recuritno[0]= 2, recuritno[1]=1...
    // @RequestBody가 있으면 415에러가 발생한다. --> Not Surportted Media Type
    
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
      // 여러개의 신청 리스트가 성공적으로 삭제 됨.
      // 삭제 요청한 갯수 == 삭제된 갯수
      obj.put("result", "OK");
    }else{
      obj.put("result", "FAILED");
    }
    
    return obj.toString();
  }
 

  
  // AJAX를 통한 회원 번호를 이용한 회원 정보 조회. JSON 정보 전달.
  /*@ResponseBody
  @RequestMapping(value="/test/meminfo.do", method=RequestMethod.GET, produces="text/plain; charset=UTF-8")
  public String meminfo(int memberno){
    System.out.println(" --> meminfo.do 호출(GET)");
    
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
