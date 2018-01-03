package dev.mvc.studylist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

public class checkboxTestCont {

  // http://localhost:9090/study/studylist/checkedseoul.do
  @RequestMapping(value = "/studylist/checkedseoul.do", method = RequestMethod.GET)
  public ModelAndView seoul() {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/studylist/checkboxTest"); // webapp/studylist/checkboxTest.jsp

    return mav;
  }
  
  
  @RequestMapping(value = "/studylist/checkedseoul.do", method = RequestMethod.POST)
  public String checkseoul(){
    return null;
    
  }

}
