package dev.mvc.studylist_reply;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.recruit.RecruitVO;

@Controller
public class ReplyCont {

  @Autowired
  @Qualifier("dev.mvc.studylist_reply.ReplyProc")
  private ReplyProcInter replyProc = null;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  public ReplyCont() {
    // System.out.println("-->ReplyCont created");
  }

  /**
   * ���͵� �׷캰 ��� �ۼ�
   * 
   * @param replyVO
   * @return String
   */
  @ResponseBody
  @RequestMapping(value = "/user/reply/create.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String create(HttpSession session, ReplyVO replyVO) {
    // System.out.println("--> create() POST executed");

    JSONObject obj = new JSONObject();

    // ID ���� ������ �ִ� ���� memberno �� ��� ��Ͻ� �Ѱ���
    int memberno = (Integer) session.getAttribute("memberno");

    // ���͵� �׷� ��ȣ
    int stdlist_no = replyVO.getStdlist_no();

    replyVO.setMemberno(memberno);

    // �ۼ��ڶ��� memberno �� ���� membetid �ҷ��� ����
    MemberVO MemberVO = memberProc.mem_read(memberno);

    String memid = MemberVO.getMemid();

    replyVO.setStd_repname(memid);

    int count = replyProc.create(replyVO);

    obj.put("count", count);
    obj.put("memberno", memberno);
    obj.put("stdlist_no", stdlist_no);

    // System.out.println(obj);

    return obj.toJSONString();

  }

  /**
   * ���͵𸮽�Ʈ�� ����� �ϳ��� ����� �о�´�.
   * 
   * @param replyVO
   * @return String
   */
  @ResponseBody
  @RequestMapping(value = "/user/reply/read.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String read(ReplyVO replyVO) {
    // System.out.println("--> read() GET executed");

    JSONObject obj = new JSONObject();

    HashMap hashmap = new HashMap();

    // ����� �о���� ���� ���� ��
    int stdlist_no = replyVO.getStdlist_no();
    int std_repno = replyVO.getStd_repno();

    hashmap.put("stdlist_no", stdlist_no);
    hashmap.put("std_repno", std_repno);

    ReplyVO read = replyProc.read(hashmap);

    String std_repcont = read.getStd_repcont();
    String std_repname = read.getStd_repname();
    int memberno = read.getMemberno();

    // System.out.println("std_repcont :"+std_repcont);
    // System.out.println("std_repname :"+std_repname);

    obj.put("std_repcont", std_repcont);
    obj.put("std_repname", std_repname);
    obj.put("stdlist_no", stdlist_no);
    obj.put("memberno", memberno);
    obj.put("std_repno", std_repno);

    return obj.toJSONString();

  }

  /**
   * ���͵� �׷캰 ��� ����
   * 
   * @param replyVO
   * @return String
   */
  @ResponseBody
  @RequestMapping(value = "/user/reply/update.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String update(HttpSession session, ReplyVO replyVO) {
    // System.out.println("--> update() POST executed");

    JSONObject obj = new JSONObject();

    // ID ���� ������ �ִ� ���� memberno �� ��� ��Ͻ� �Ѱ���
    int memberno = (Integer) session.getAttribute("memberno");

    // ��ۼ����� ���� ����������
    int nowPage = replyVO.getNowPage();

    replyVO.setMemberno(memberno);

    int count = replyProc.update(replyVO);

    // System.out.println("count :" + count);
    // System.out.println("nowPage :" + nowPage);

    int stdlist_no = replyVO.getStdlist_no();

    obj.put("count", count);
    obj.put("stdlist_no", stdlist_no);
    obj.put("nowPage", nowPage);

    /* System.out.println(obj); */

    return obj.toJSONString();

  }

  /**
   * ���͵� �׷캰 ��� ����
   * 
   * @param replyVO
   * @return String
   */
  @ResponseBody
  @RequestMapping(value = "/user/reply/delete.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String delete(ReplyVO replyVO) {
    // System.out.println("--> delete() get executed");

    JSONObject obj = new JSONObject();

    int stdlist_no = replyVO.getStdlist_no();
    int std_repno = replyVO.getStd_repno();

    HashMap hashmap = new HashMap();

    hashmap.put("stdlist_no", stdlist_no);
    hashmap.put("std_repno", std_repno);

    int count = replyProc.delete(hashmap);

    obj.put("count", count);
    obj.put("stdlist_no", stdlist_no);

    return obj.toString();
  }

  /**
   * ����� �����ϰų� �����Ϸ��� �� �� �ۼ��ڰ� �´��� �˻�.
   * 
   * @param ReplyVO
   * @return String
   */
  @ResponseBody
  @RequestMapping(value = "/user/reply/check_memberno.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String check_memberno(HttpSession session, HttpServletRequest request, ReplyVO replyVO) {

    // System.out.println("--> check_memberno() GET executed");

    // session�� ���� id�� memberno���� ��������.
    int memberno = (Integer) session.getAttribute("memberno");

    int stdlist_no = replyVO.getStdlist_no();
    int std_repno = replyVO.getStd_repno();

    // ������ �Ϸ��� �ϴ��� ������ �Ϸ��� �ϴ� �˻�
    // 1 = ���� , 2 = ����
    String str = request.getParameter("str");

    HashMap hashmap = new HashMap();

    // System.out.println("memberno:" + memberno);
    // System.out.println("stdlist_no:" + stdlist_no);

    hashmap.put("memberno", memberno);
    hashmap.put("stdlist_no", stdlist_no);
    hashmap.put("std_repno", std_repno);

    JSONObject obj = new JSONObject();

    // count ���� 1�̸� �ۼ��ڿ� ���� �����Ϸ��� ȸ���� ��ġ
    // 0�̸� ����, ���� �Ұ�.
    int count = replyProc.check_memberno(hashmap);

    // System.out.println("count:" + count);

    obj.put("count", count);
    obj.put("stdlist_no", stdlist_no);
    obj.put("std_repno", std_repno);
    obj.put("str", str);

    return obj.toString();
  }

}
