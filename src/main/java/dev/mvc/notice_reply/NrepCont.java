package dev.mvc.notice_reply;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.studylist_reply.ReplyVO;
import net.sf.json.JSONArray;

@Controller
public class NrepCont {
  @Autowired
  @Qualifier("dev.mvc.notice_reply.NrepProc")
  private NrepProcInter nrepProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  public NrepCont() {
  }

  /**
   * 댓글 등록
   * @param session
   * @param nrepVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/notice_reply/nrep_create.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String nrep_create(HttpSession session, NrepVO nrepVO) {
    // System.out.println("--> nrep_create() POST executed");

    JSONObject obj = new JSONObject();

    // ID 별로 가지고 있는 고유 memberno 를 댓글 등록시 넘겨줌
    int memberno = (Integer) session.getAttribute("memberno");

    // 공지글 번호
    int noticeno = nrepVO.getNoticeno();

    nrepVO.setMemberno(memberno);

    // 작성자란에 memberno 에 대한 memid 불러서 저장
    MemberVO MemberVO = memberProc.mem_read(memberno);

    String memid = MemberVO.getMemid();

    nrepVO.setNrepname(memid);

    int count = nrepProc.nrep_create(nrepVO);

    obj.put("count", count);
    obj.put("noticeno", noticeno);

    return obj.toJSONString();
  }
  
  
  /**
   * 댓글 리스트 페이징
   * @param nrepVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/nonuser/notice_reply/nrep_list.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String nrep_list(NrepVO nrepVO) {
    // System.out.println("--> nrep_list() executed");

    JSONObject obj = new JSONObject();

    int noticeno = nrepVO.getNoticeno();
    int nowPage = nrepVO.getNowPage();

    HashMap hashMap = new HashMap();

    // 스터디 그룹별 댓글을 읽어오기 위한 read의 스터디그룹번호 가져오기
    hashMap.put("noticeno", noticeno);
    hashMap.put("nowPage", nowPage);

    List<NrepVO> list = nrepProc.nrep_list(hashMap);

    // 스터디그룹별 댓글 갯수 가져오기
    int search_count = nrepProc.search_count(noticeno);

    // 페이징처리된 String append 가져오기
    String paging = nrepProc.paging(search_count, nowPage);

    // 리스트를 json 배열에 저장
    JSONArray reply = JSONArray.fromObject(list);

    obj.put("reply", reply); // 스터디그룹별 댓글리스트 저장
    obj.put("search_count", search_count); // 스터디그룹별 댓글 개수
    obj.put("paging", paging); // 페이징 처리
    obj.put("nowPage", nowPage);

    // System.out.println(obj);

    return obj.toString();

  }

  
  /**
   * 댓글 하나 리드
   * @param nrepVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/notice_reply/nrep_read.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String nrep_read(NrepVO nrepVO) {
    System.out.println("--> nrep_read() GET executed");

    JSONObject obj = new JSONObject();
    HashMap hashMap = new HashMap();

    // 댓글을 읽어오기 위한 변수 값
    int noticeno = nrepVO.getNoticeno();
    int nrepno = nrepVO.getNrepno();

    hashMap.put("noticeno", noticeno);
    hashMap.put("nrepno", nrepno);

    nrepVO = nrepProc.nrep_read(hashMap);

    String nrepcont = nrepVO.getNrepcont();
    String nrepname = nrepVO.getNrepname();
    int memberno = nrepVO.getMemberno();

    obj.put("nrepcont", nrepcont);
    obj.put("nrepname", nrepname);
    obj.put("noticeno", noticeno);
    obj.put("memberno", memberno);
    obj.put("nrepno", nrepno);
    
    System.out.println("nrepcont" + nrepcont);

    return obj.toJSONString();
  }
  
  
  /**
   * 공지글별 댓글 수정
   * @param session
   * @param nrepVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/notice_reply/nrep_update.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String nrep_update(HttpSession session, NrepVO nrepVO) {
    // System.out.println("--> nrep_update() POST executed");
    JSONObject obj = new JSONObject();

    
    // ID 별로 가지고 있는 고유 memberno 를 댓글 등록시 넘겨줌
    //int memberno = (Integer) session.getAttribute("memberno");

    // 댓글수정에 대한 페이지유지
    int nowPage = nrepVO.getNowPage();

    //nrepVO.setMemberno(memberno);

    int count = nrepProc.nrep_update(nrepVO);

    // System.out.println("count :" + count);
    // System.out.println("nowPage :" + nowPage);

    int noticeno = nrepVO.getNoticeno();

    obj.put("count", count);
    obj.put("noticeno", noticeno);
    obj.put("nowPage", nowPage);

    /* System.out.println(obj); */

    return obj.toJSONString();
  }
  
  
  /**
   * 공지글별 댓글 삭제
   * @param nrepVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/notice_reply/nrep_delete.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String nrep_delete(NrepVO nrepVO) {
    // System.out.println("--> nrep_delete() get executed");

    JSONObject obj = new JSONObject();

    int noticeno = nrepVO.getNoticeno();
    int nrepno = nrepVO.getNrepno();

    HashMap hashMap = new HashMap();

    hashMap.put("noticeno", noticeno);
    hashMap.put("nrepno", nrepno);

    int count = nrepProc.nrep_delete(hashMap);

    obj.put("count", count);
    obj.put("noticeno", noticeno);

    return obj.toString();
  }
  
  
  /**
   * 수정삭제시 쓴 회원이 맞는지 검사
   * @param session
   * @param request
   * @param nrepVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/notice_reply/nrep_ck_memberno.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String nrep_ck_memberno(HttpSession session, HttpServletRequest request, NrepVO nrepVO) {
    System.out.println("--> nrep_ck_memberno() GET executed");

    // session을 통해 id의 memberno값을 가져오기.
    int memberno = (Integer) session.getAttribute("memberno");

    int noticeno = nrepVO.getNoticeno();
    int nrepno = nrepVO.getNrepno();

    // 수정을 하려고 하는지 삭제를 하려고 하는 검사
    // 1 = 수정 , 2 = 삭제
    String str = request.getParameter("str");

    HashMap hashMap = new HashMap();

    // System.out.println("memberno:" + memberno);
    // System.out.println("stdlist_no:" + stdlist_no);

    hashMap.put("memberno", memberno);
    hashMap.put("noticeno", noticeno);
    hashMap.put("nrepno", nrepno);

    JSONObject obj = new JSONObject();

    // count 값이 1이면 작성자와 삭제 수정하려는 회원과 일치
    // 0이면 수정, 삭제 불가.
    int count = nrepProc.nrep_ck_memberno(hashMap);
    

    System.out.println("count:" + count);
    System.out.println("str:" + str);

    obj.put("count", count);
    obj.put("noticeno", noticeno);
    obj.put("nrepno", nrepno);
    obj.put("str", str);

    return obj.toString();
  }

}
