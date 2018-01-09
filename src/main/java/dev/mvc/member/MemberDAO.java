package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.member.MemberDAO")
public class MemberDAO implements MemberDAOInter {
  @Autowired
  private SqlSessionTemplate mybatis;

  public MemberDAO() {
    
  }

  @Override
  public int memberjoin(MemberVO memberVO) {
    return mybatis.insert("member.memberjoin", memberVO);
  }
  
  @Override
  public int check_id(String memid) {
    return mybatis.selectOne("member.check_id", memid);
  }

  @Override
  public int check_email(String mememail) {
    return mybatis.selectOne("member.check_email", mememail);
  }

  @Override
  public List<MemberVO> mem_list(HashMap hashMap) {
    return mybatis.selectList("member.mem_list", hashMap);
  }
  
  @Override
  public int search_count(HashMap hashMap) {
    return mybatis.selectOne("member.search_count", hashMap);
  }

  @Override
  public MemberVO mem_read(int memberno) {
    return mybatis.selectOne("member.mem_read", memberno);
  }

  @Override
  public MemberVO mem_read_id(String memid) {
    return mybatis.selectOne("member.mem_read_id", memid);
  }

  @Override
  public int mem_update(MemberVO memberVO) {
    return mybatis.update("member.mem_update", memberVO);
  }

  @Override
  public int login(Map map) {
    System.out.println(mybatis);
    return mybatis.selectOne("member.login", map);
  }

  @Override
  public String find_memid(HashMap hashMap) {
    return mybatis.selectOne("member.find_memid", hashMap);
  }

  @Override
  public String find_mempasswd(HashMap hashMap) {
    return mybatis.selectOne("member.find_mempasswd", hashMap);
  }

  @Override
  public int mempasswd_change(MemberVO memberVO) {
    return mybatis.update("member.mempasswd_change", memberVO);
  }

  @Override
  public int mem_delete(MemberVO memberVO) {
    return mybatis.update("member.mem_delete", memberVO);
  }


}
