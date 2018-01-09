package dev.mvc.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import nation.web.tool.Tool;
import nation.web.tool.Upload;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.contest.ContestVO;

@Controller
public class JobCont {
  @Autowired
  @Qualifier("dev.mvc.job.JobProc")
  private JobProcInter jobProc = null;
  
  public JobCont() {
    //System.out.println("--> JobCont() Created.");
  }
  
  @RequestMapping(value="/admin/jobinfo/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    //System.out.println("---> create() GET Executed.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/jobinfo/create");
    
    return mav;
  }
  
  @RequestMapping(value="/admin/jobinfo/create.do", method=RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, JobVO jobVO) {
    //System.out.println("---> create() POST Executed.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/jobinfo/message");
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    // ---------------------------------------------------------------
    // 주소 조합 & 저장 시작
    // ---------------------------------------------------------------
    String jobLocal = jobVO.getJcity() + " " + jobVO.getJgu() + " " + jobVO.getJdong();
    jobVO.setJobLocal(jobLocal);
    // ---------------------------------------------------------------
    // 주소 조합 & 저장 종료
    // ---------------------------------------------------------------
    
    // -------------------------- 파일 전송 코드 시작 --------------------------
    String upDir = Tool.getRealPath(request, "/admin/jobinfo/storage");
    MultipartFile file1MF = jobVO.getFile1MF();
    MultipartFile file2MF = jobVO.getFile2MF();
    
    String jobFile1 = file1MF.getOriginalFilename();
    long jobSize1 = file1MF.getSize();
    String jobThumb = "";
    
    String jobFile2 = file2MF.getOriginalFilename();
    long jobSize2 = file2MF.getSize();
    String jobFstor2 = "";
    
    if (jobSize1 > 0) {
      jobFile1 = Upload.saveFileSpring(file1MF, upDir);
      if (Tool.isImage(jobFile1)) {
        jobThumb = Tool.preview(upDir, jobFile1, 120, 80);
      }
    }
    
    if (jobSize2 > 0) {
      jobFile2 = Upload.saveFileSpring(file2MF, upDir);
    }
    
    jobVO.setJobFile1(jobFile1);
    jobVO.setJobSize1(jobSize1);
    jobVO.setJobThumb(jobThumb);
    
    jobVO.setJobFile2(jobFile2);
    jobVO.setJobSize2(jobSize2);
    jobVO.setJobFstor2(jobFstor2);
    // -------------------------- 파일 전송 코드 시작 --------------------------
    
    if (jobProc.create(jobVO) == 1) {
      mav.setViewName("redirect:/nonuser/jobinfo/list_all_jobinfo.do");
    } 
    
    return mav;
  }
  
  @RequestMapping(value="/nonuser/jobinfo/list_all_jobinfo.do", method=RequestMethod.GET)
  public ModelAndView list_all_jobinfo(@RequestParam(value="jobWord", defaultValue="") String jobWord,
                                       @RequestParam(value="nowPage", defaultValue="1") int nowPage) throws Exception {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/jobinfo/list_all_jobinfo");
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("jobWord", jobWord);
    hashMap.put("nowPage", nowPage);
    
    List<JobVO> list = jobProc.list_all_jobinfo(hashMap);
    mav.addObject("list", list);
    
    List<JobVO> list_by_count = jobProc.list_by_count();
    mav.addObject("list_by_count", list_by_count);
    
    int search_count = jobProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    String paging = jobProc.paging(search_count, nowPage, jobWord);
    mav.addObject("paging", paging);
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
  
  @RequestMapping(value="/nonuser/jobinfo/read.do", method = RequestMethod.GET)
  public ModelAndView read(int jobNo, HttpSession session) throws Exception {
    jobProc.increaseCnt(jobNo);
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/jobinfo/read");
    
    JobVO jobVO = jobProc.read(jobNo);

    jobVO.setJobCont(Tool.convertChar(jobVO.getJobCont()));
    
    mav.addObject("jobVO", jobVO);
    mav.addObject("max", jobProc.max());
    mav.addObject("mini", jobProc.mini());
    
    return mav;
  }
  
  @RequestMapping(value="/admin/jobinfo/update.do", method=RequestMethod.GET)
  public ModelAndView update(int jobNo) throws Exception {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/jobinfo/update");
    
    JobVO jobVO = jobProc.read(jobNo);
    mav.addObject("jobVO", jobVO);
    
    return mav;
  }
  
  @RequestMapping(value="/admin/jobinfo/update.do", method=RequestMethod.POST)
  public ModelAndView update(HttpServletRequest request, JobVO jobVO) throws Exception {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/jobinfo/message");
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    // --------------------------------------------------------
    // 주소 조합 & 저장 시작
    // --------------------------------------------------------
    String jobLocal = jobVO.getJcity() + " " + jobVO.getJgu() + " " + jobVO.getJdong();
    jobVO.setJobLocal(jobLocal);
    // --------------------------------------------------------
    // 주소 조합 & 저장 종료
    // --------------------------------------------------------
    
    // *********************** 날짜 계산 시작 **********************
    String jobStart = jobVO.getJobStart();
    String jobEnd = jobVO.getJobEnd();
    
    String jobCont = jobVO.getJobCont();
    jobCont = Tool.convertChar(jobCont);
    jobVO.setJobCont(jobCont);
    
    long time = System.currentTimeMillis();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String today = df.format(new Date(time));
    
    Date date_e = df.parse(jobEnd);
    Date date_s = df.parse(jobStart);
    
    String endDate = new SimpleDateFormat("yyyy-MM-dd").format(date_e);
    String startDate = new SimpleDateFormat("yyyy-MM-dd").format(date_s);
    
    long now = (df.parse(today)).getTime();
    long end = (df.parse(endDate)).getTime();
    long start = (df.parse(startDate)).getTime();
    
    long diff_e = end - now;
    long jobRemain_e = diff_e / (24 * 60 * 60 * 1000);
    
    long diff_s = start - now;
    long jobRemain_s = diff_s / (24 * 60 * 60 * 1000);
    
    jobVO.setJobRemain_e(jobRemain_e);
    jobVO.setJobRemain_s(jobRemain_s);
    // *********************** 날짜 계산 종료 **********************
    
    String root = request.getContextPath();
    
    if (jobProc.update(jobVO) == 1) {
      mav.setViewName("redirect:/nonuser/jobinfo/read.do?jobNo=" + jobVO.getJobNo());
    } 
    
    return mav;
  }
  
  @ResponseBody
  @RequestMapping(value = "/admin/jobinfo/update_thumb.do", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
  public String update_thumb(int jobNo) throws Exception {
    // System.out.println("--> update() GET executed");
    /*ModelAndView mav = new ModelAndView();
    mav.setViewName("/contest/update_thumb");*/ // webapp/contest/update.jsp

    JSONObject obj = new JSONObject();
    
    JobVO jobVO = jobProc.read(jobNo);
    /*mav.addObject("contestVO", contestVO);*/
    
    obj.put("jobNo", jobVO.getJobNo());
    obj.put("jobFile1", jobVO.getJobFile1());
    obj.put("jobSize1", jobVO.getJobSize1());
    obj.put("jobThumb", jobVO.getJobThumb());
    
    return obj.toJSONString();
  } 
  
  @RequestMapping(value = "/admin/jobinfo/update_thumb.do", method = RequestMethod.POST)
  public ModelAndView update_thumb(HttpServletRequest request, JobVO jobVO) throws Exception {
    ModelAndView mav = new ModelAndView();
    // ============================== 파일 전송 코드 시작 ====================================
    String upDir = Tool.getRealPath(request, "/study/admin/jobinfo/storage");
    MultipartFile file1MF = jobVO.getFile1MF();
    
    //System.out.println("jobNo ---> " + jobVO.getJobNo());
    //System.out.println("file1MF ---> " + file1MF);
    
    String jobFile1 = "";
    long jobSize1 = file1MF.getSize();
    String jobThumb = "";
    
    JobVO jobVO_old = jobProc.read(jobVO.getJobNo());
    
    if (jobSize1 > 0) {
      Tool.deleteFile(upDir, jobVO_old.getJobFile1());
      Tool.deleteFile(upDir, jobVO_old.getJobThumb());
      
      jobFile1 = Upload.saveFileSpring(file1MF, upDir);
      
      if (Tool.isImage(jobFile1)) {
        jobThumb = Tool.preview(upDir, jobFile1, 120, 80);
      }
    } else {
      jobFile1 = jobVO.getJobFile1();
      jobSize1 = jobVO.getJobSize1();
      jobThumb = jobVO.getJobThumb();
    }
    
    jobVO.setJobFile1(jobFile1);
    jobVO.setJobSize1(jobSize1);
    jobVO.setJobThumb(jobThumb);
    
    // ============================== 파일 전송 코드 종료 ====================================
    
    if (jobProc.update_thumb(jobVO) == 1) {
      mav.setViewName("redirect:/study/admin/jobinfo/update.do?jobNo=" + jobVO.getJobNo());
      //System.out.println("파일이 바뀌었습니다.");
      /*links.add("<button type='button' onclick=\"location.href='./update.do?conNo=" + contestVO.getConNo() + "&cateno=" + contestVO.getCateno() +"'\">업데이트 계속</button>");*/
    } 
    return mav;
  }
  
  @ResponseBody
  @RequestMapping(value = "/admin/jobinfo/update_file.do", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
  public String update_file(int jobNo) throws Exception {
    // System.out.println("--> update() GET executed");
    /*ModelAndView mav = new ModelAndView();
    mav.setViewName("/contest/update_thumb");*/ // webapp/contest/update.jsp

    JSONObject obj = new JSONObject();
    
    JobVO jobVO = jobProc.read(jobNo);
    /*mav.addObject("contestVO", contestVO);*/
      
    obj.put("jobNo", jobVO.getJobNo());
    obj.put("jobFile2", jobVO.getJobFile2());
    obj.put("jobSize2", jobVO.getJobSize2());
    obj.put("jobFstor2", jobVO.getJobFstor2());
    
    return obj.toJSONString();
  } 
  
  @RequestMapping(value = "/admin/jobinfo/update_file.do", method = RequestMethod.POST)
  public ModelAndView update_file(HttpServletRequest request, JobVO jobVO) throws Exception {
    ModelAndView mav = new ModelAndView();
    
    // ============================== 파일 전송 코드 시작 ====================================
    String upDir = Tool.getRealPath(request, "/study/admin/jobinfo/storage");
    MultipartFile file2MF = jobVO.getFile2MF();
    
    String jobFile2 = "";
    long jobSize2 = file2MF.getSize();
    String jobFstor2 = "";
    
    JobVO jobVO_old = jobProc.read(jobVO.getJobNo());
    
    if (jobSize2 > 0) {
      Tool.deleteFile(upDir, jobVO_old.getJobFile2());
      Tool.deleteFile(upDir, jobVO_old.getJobFstor2());
      
      jobFile2 = Upload.saveFileSpring(file2MF, upDir);
      
      if (Tool.isImage(jobFile2)) {
        jobFstor2 = Tool.preview(upDir, jobFile2, 120, 80);
      }
    } else {
      jobFile2 = jobVO.getJobFile2();
      jobSize2 = jobVO.getJobSize2();
      jobFstor2 = jobVO.getJobFstor2();
    }
    
    jobVO.setJobFile2(jobFile2);
    jobVO.setJobSize2(jobSize2);
    jobVO.setJobFstor2(jobFstor2);
    
    // ============================== 파일 전송 코드 종료 ====================================
    
    if (jobProc.update_file(jobVO) == 1) {
      mav.setViewName("redirect:/study/admin/jobinfo/update.do?jobNo=" + jobVO.getJobNo());
      //System.out.println("파일이 바뀌었습니다.");
    }

    return mav;
    
  }
  
  @ResponseBody
  @RequestMapping(value="/admin/jobinfo/delete.do", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
  public String delete(int jobNo) throws Exception {
    JSONObject obj = new JSONObject();
    
    JobVO jobVO = jobProc.read(jobNo);
    
    obj.put("jobNo", jobVO.getJobNo());
    
    return obj.toJSONString();
  }
  
  @RequestMapping(value = "/admin/jobinfo/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(HttpServletRequest request, JobVO jobVO) throws Exception {
    ModelAndView mav = new ModelAndView();
    
    // -----------------------------------파일전송 코드 시작-------------------------------------------------
    String upDir = Tool.getRealPath(request, "/study/admin/jobinfo/storage");
    
    // 기존 글 정보 로딩
    JobVO jobVO_old = jobProc.read(jobVO.getJobNo());
    
    Tool.deleteFile(upDir, jobVO_old.getJobFile1());
    Tool.deleteFile(upDir, jobVO_old.getJobThumb());
    Tool.deleteFile(upDir, jobVO_old.getJobFile2());
    Tool.deleteFile(upDir, jobVO_old.getJobFstor2());
    // -----------------------------------파일전송 코드 종료-------------------------------------------------    
    
    if (jobProc.delete(jobVO.getJobNo()) == 1) {
      mav.setViewName("redirect:/nonuser/jobinfo/list_all_jobinfo.do");
    } 
    
    return mav;
  }
  
}
