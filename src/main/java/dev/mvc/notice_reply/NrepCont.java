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
   * ��� ���
   * @param session
   * @param nrepVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/notice_reply/nrep_create.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String nrep_create(HttpSession session, NrepVO nrepVO) {
    // System.out.println("--> nrep_create() POST executed");

    JSONObject obj = new JSONObject();

    // ID ���� ������ �ִ� ���� memberno �� ��� ��Ͻ� �Ѱ���
    int memberno = (Integer) session.getAttribute("memberno");

    // ������ ��ȣ
    int noticeno = nrepVO.getNoticeno();

    nrepVO.setMemberno(memberno);

    // �ۼ��ڶ��� memberno �� ���� memid �ҷ��� ����
    MemberVO MemberVO = memberProc.mem_read(memberno);

    String memid = MemberVO.getMemid();

    nrepVO.setNrepname(memid);

    int count = nrepProc.nrep_create(nrepVO);

    obj.put("count", count);
    obj.put("noticeno", noticeno);

    return obj.toJSONString();
  }
  
  
  /**
   * ��� ����Ʈ ����¡
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

    // ���͵� �׷캰 ����� �о���� ���� read�� ���͵�׷��ȣ ��������
    hashMap.put("noticeno", noticeno);
    hashMap.put("nowPage", nowPage);

    List<NrepVO> list = nrepProc.nrep_list(hashMap);

    // ���͵�׷캰 ��� ���� ��������
    int search_count = nrepProc.search_count(noticeno);

    // ����¡ó���� String append ��������
    String paging = nrepProc.paging(search_count, nowPage);

    // ����Ʈ�� json �迭�� ����
    JSONArray reply = JSONArray.fromObject(list);

    obj.put("reply", reply); // ���͵�׷캰 ��۸���Ʈ ����
    obj.put("search_count", search_count); // ���͵�׷캰 ��� ����
    obj.put("paging", paging); // ����¡ ó��
    obj.put("nowPage", nowPage);

    // System.out.println(obj);

    return obj.toString();

  }

  
  /**
   * ��� �ϳ� ����
   * @param nrepVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/notice_reply/nrep_read.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String nrep_read(NrepVO nrepVO) {
    System.out.println("--> nrep_read() GET executed");

    JSONObject obj = new JSONObject();
    HashMap hashMap = new HashMap();

    // ����� �о���� ���� ���� ��
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
   * �����ۺ� ��� ����
   * @param session
   * @param nrepVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/notice_reply/nrep_update.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String nrep_update(HttpSession session, NrepVO nrepVO) {
    // System.out.println("--> nrep_update() POST executed");
    JSONObject obj = new JSONObject();

    
    // ID ���� ������ �ִ� ���� memberno �� ��� ��Ͻ� �Ѱ���
    //int memberno = (Integer) session.getAttribute("memberno");

    // ��ۼ����� ���� ����������
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
   * �����ۺ� ��� ����
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
   * ���������� �� ȸ���� �´��� �˻�
   * @param session
   * @param request
   * @param nrepVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/notice_reply/nrep_ck_memberno.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String nrep_ck_memberno(HttpSession session, HttpServletRequest request, NrepVO nrepVO) {
    System.out.println("--> nrep_ck_memberno() GET executed");

    // session�� ���� id�� memberno���� ��������.
    int memberno = (Integer) session.getAttribute("memberno");

    int noticeno = nrepVO.getNoticeno();
    int nrepno = nrepVO.getNrepno();

    // ������ �Ϸ��� �ϴ��� ������ �Ϸ��� �ϴ� �˻�
    // 1 = ���� , 2 = ����
    String str = request.getParameter("str");

    HashMap hashMap = new HashMap();

    // System.out.println("memberno:" + memberno);
    // System.out.println("stdlist_no:" + stdlist_no);

    hashMap.put("memberno", memberno);
    hashMap.put("noticeno", noticeno);
    hashMap.put("nrepno", nrepno);

    JSONObject obj = new JSONObject();

    // count ���� 1�̸� �ۼ��ڿ� ���� �����Ϸ��� ȸ���� ��ġ
    // 0�̸� ����, ���� �Ұ�.
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
