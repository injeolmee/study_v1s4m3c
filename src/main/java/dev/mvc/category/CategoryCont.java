package dev.mvc.category;

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

import dev.mvc.categrp.CategrpProcInter;
import dev.mvc.categrp.CategrpVO;


@Controller
public class CategoryCont {
  @Autowired
  @Qualifier("dev.mvc.category.CategoryProc")
  private CategoryProcInter categoryProc;
  
  @Autowired
  @Qualifier("dev.mvc.categrp.CategrpProc")
  private CategrpProcInter categrpProc;
  
  
  public CategoryCont() {
    // System.out.println("--> CategoryCont created");
  }
  
  
  /**
   * 카테고리 등록
   * http://localhost:9090/study/admin/category/cate_create.do
   * @return
   */
  @RequestMapping(value = "/admin/category/cate_create.do", method = RequestMethod.GET)
  public ModelAndView cate_create() {
    // System.out.println("--> cate_create() GET executed");
    ModelAndView mav = new ModelAndView();

    return mav;
  }
  
  
  /**
   * 카테고리 등록 처리
   * @param categoryVO
   * @return
   */
  @RequestMapping(value = "/admin/category/cate_create.do", method = RequestMethod.POST)
  public ModelAndView cate_create(CategoryVO categoryVO) {
    System.out.println("--> cate_create() POST executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("admin/category/message"); // webapp/category/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    int grpno = categoryVO.getGrpno();

    if (categoryProc.cate_create(categoryVO) == 1) {
      mav.setViewName("redirect:/admin/category/cate_list.do?grpno=" + grpno);
    } else {
      msgs.add("카테고리 등록 실패<br>");
      links.add("<button type= 'button' onclick=\"history.back()\">다시 시도</button>");
    }

    links.add("<button type= 'button' onclick=\"location.href='./cate_list.do'\">목록</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }
  
  
  /**
   * 카테고리 리스트
   * http://localhost:9090/study/admin/category/cate_list.do
   * @return
   */
  @RequestMapping(value = "/admin/category/cate_list.do", method = RequestMethod.GET)
  public ModelAndView cate_list(int grpno) {
    // System.out.println("--> cate_list() POST executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("admin/category/cate_list");
       
    List<CategoryVO> cate_list = categoryProc.cate_list(grpno);
    mav.addObject("cate_list", cate_list);
    mav.addObject("grpno", grpno);
   
    return mav;
  }
  
  
  /**
   * 카테고리 수정
   * http://localhost:9090/study/stucategory/cate_update.do
   * @param cateno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/admin/category/cate_update.do", method = RequestMethod.GET, produces= "text/plain;charset=UTF-8")
  public String cate_update(int cateno, int grpno) {
    // System.out.println("--> cate_update() GET executed");
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>(); 
    hashMap.put("cateno", cateno);
    hashMap.put("grpno", grpno);
    
    CategoryVO categoryVO = categoryProc.cate_read(hashMap);    
    
    JSONObject obj = new JSONObject();
    obj.put("cateno", categoryVO.getCateno());
    obj.put("catename", categoryVO.getCatename());
    obj.put("cateseqno", categoryVO.getCateseqno());
    obj.put("catevisible", categoryVO.getCatevisible());
    obj.put("grpno", categoryVO.getGrpno());
   
    return obj.toJSONString();
  }
  
  
  /**
   * 카테고리 수정 처리
   * @param categoryVO
   * @return
   */
  @RequestMapping(value = "/admin/category/cate_update.do", method = RequestMethod.POST)
  public ModelAndView cate_update(CategoryVO categoryVO) {
    // System.out.println("--> cate_update() POST executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("admin/category/message"); 
    
    /*HashMap<Object, Object> hashMap = new HashMap<Object, Object>(); 
    int cateno = categoryVO.getCateno();
    
    hashMap.put("cateno", cateno);
    hashMap.put("grpno", grpno);*/
    
    int grpno = categoryVO.getGrpno();    

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    if (categoryProc.cate_update(categoryVO) == 1) {
      mav.setViewName("redirect:/admin/category/cate_list.do?grpno=" + grpno);
    } else {
      msgs.add("카테고리 변경 실패<br>");
      links.add("<button type= 'button' onclick=\"history.back()\">다시 시도</button>");
    }

    links.add("<button type= 'button' onclick=\"location.href='./cate_list.do'\">목록</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }
  
  
  /**
   * 카테고리 출력 순위 높임
   * @param cateno
   * @return
   */
  @RequestMapping(value = "/admin/category/cateseqno_up.do", method = RequestMethod.POST)
  public ModelAndView cateseqno_up(int cateno, int grpno) {
    // System.out.println("--> cateseqno_up() POST executed");
    ModelAndView mav = new ModelAndView();
    
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>(); 
    hashMap.put("cateno", cateno);
    hashMap.put("grpno", grpno);
    
    if (categoryProc.cateseqno_up(hashMap) == 1) {
      mav.setViewName("redirect:/admin/category/cate_list.do?grpno=" + grpno);
    }
    
    return mav;
  }
  
  
  /**
   * 카테고리 출력 순위 낮춤
   * @param cateno
   * @return
   */
  @RequestMapping(value = "/admin/category/cateseqno_down.do", method = RequestMethod.POST)
  public ModelAndView cateseqno_down(int cateno, int grpno) {
    // System.out.println("--> cateseqno_up() POST executed");
    ModelAndView mav = new ModelAndView();
    
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>(); 
    hashMap.put("cateno", cateno);
    hashMap.put("grpno", grpno);
    
    if (categoryProc.cateseqno_down(hashMap) == 1) {
      mav.setViewName("redirect:/admin/category/cate_list.do?grpno=" + grpno);
    }
    
    return mav;
  }
  

  /**
   * 카테고리 삭제
   * @param cateno
   * @return
   */
  @RequestMapping(value = "/admin/category/cate_delete.do", method = RequestMethod.POST)
  public ModelAndView cate_delete(int cateno, int grpno) {
    // System.out.println("--> cate_delete() POST executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("admin/category/message");  
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>(); 
    hashMap.put("cateno", cateno);
    hashMap.put("grpno", grpno);    
    
    if (categoryProc.cate_delete(hashMap) == 1) {
      mav.setViewName("redirect:/admin/category/cate_list.do?grpno=" + grpno);
    } else {
      msgs.add("카테고리 삭제 실패<br>");
      links.add("<button type= 'button' onclick=\"history.back()\">다시 시도</button>");
    }
    
    links.add("<button type= 'button' onclick=\"location.href='./cate_list.do'\">목록</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  
  // http://localhost:9090/study/admin/category/list_top.do
/*  @RequestMapping(value = "/admin/category/list_top.do", method = RequestMethod.GET)
  public ModelAndView list_top(HttpServletRequest request) {
    // System.out.println("--> list_top() POST executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("admin/category/list_top"); 
       
    List<CategrpVO> grp_list = categrpProc.grp_list();
    List<Categrp_CategoryVO> grp_cate_list = categoryProc.grp_cate_list();
    
    // Categrp: grpname, Category: catename 결합 목록
    ArrayList<String> grp_cate_namelist = new ArrayList<String>();   
    
    StringBuffer url = new StringBuffer(); // 카테고리 제목 링크 조합
    
    // 카테그룹 목록 추가
    for (int i = 0; i < grp_list.size(); i++) {
      String grpname = grp_list.get(i).getGrpname(); // 그룹 이름 추출      
      grp_cate_namelist.add("<LI class='categrp_name'>" + grpname + "</LI>"); // 그룹 이름 목록에 추가
      
      // 카테고리 목록 추가
      for (int j=0; j < grp_cate_list.size(); j++) {
        Categrp_CategoryVO categrp_CategoryVO =  grp_cate_list.get(j); // 그룹, 카테고리 테이블 조인 객체 추출
        String catename = categrp_CategoryVO.getCatename(); //  카테고리 이름 추출
        if (grpname.equals(categrp_CategoryVO.getGrpname()) == true && catename != null) {
          // 그룹 이름이 같으면서 카테고리 이름이 존재한다면 카테고리 이름을 name_title_list 목록에 추가
          url.append("  <A href='" + request.getContextPath()+ "/admin/category/cate_list.do?grpno=" + categrp_CategoryVO.getGrpno()+ "'>");
          url.append(catename);
          url.append("  </A>");
          url.append("  <br>");
          grp_cate_namelist.add(url.toString()); // 출력 목록에 하나의 category 추가 
          
          url.delete(0, url.toString().length()); 
          // 하나의 카테고리 항목이 조합되어 name_title_list에 추가되었음으로 StringBuffer 문자열 삭제 후 재사용 준비
        }
      }
    }
    
    mav.addObject("grp_cate_namelist", grp_cate_namelist); // jsp 페이지에 출력할 객체 저장
    
    return mav;
  }
  */
  
  // http://localhost:9090/study/admin/categrp/list_top.do
  @RequestMapping(value = "/admin/category/list_top.do", method = RequestMethod.GET)
  public ModelAndView grplist_top(HttpServletRequest request) {
    // System.out.println("--> grplist_top() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("admin/category/list_top"); 
       
    List<CategrpVO> grp_list = categrpProc.grp_list();
       
    ArrayList<String> grp_namelist = new ArrayList<String>();       
    
    // 카테그룹 목록 추가
    for (int i = 0; i < grp_list.size(); i++) {
      String grpname = grp_list.get(i).getGrpname(); // 그룹 이름 추출      
      grp_namelist.add(grpname); // 그룹 이름 목록에 추가      
      // System.out.println(grpname); 
    }
    
    mav.addObject("grp_namelist", grp_namelist); // jsp 페이지에 출력할 객체 저장
    
    return mav;
  }
 
  
  
  
  
  


}
