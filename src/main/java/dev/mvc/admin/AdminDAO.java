package dev.mvc.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.member.MemberVO;

@Repository("dev.mvc.admin.AdminDAO")
public class AdminDAO implements AdminDAOInter {
  
  @Autowired
  private SqlSessionTemplate mybatis;

  public AdminDAO() {
  }

  @Override
  public int check_master(String memauth) {
    return mybatis.selectOne("admin.check_master", memauth);
  }

  @Override
  public int admin_create(AdminVO adminVO) {
    return mybatis.insert("admin.admin_create", adminVO);
  }

  @Override
  public int check_admid(String admid) {
    return mybatis.selectOne("admin.check_admid", admid);
  }

  @Override
  public int check_admemail(String admemail) {
    return mybatis.selectOne("admin.check_admemail", admemail);
  }

  @Override
  public List<AdminVO> admin_list(HashMap hashMap) {
    return mybatis.selectList("admin.admin_list", hashMap);
  }
  
  @Override
  public int search_count(HashMap hashMap) {
    return mybatis.selectOne("admin.search_count", hashMap);
  }

  @Override
  public AdminVO admin_read(int adminno) {
    return mybatis.selectOne("admin.admin_read", adminno);
  }

  @Override
  public AdminVO admin_read_id(String admid) {
    return mybatis.selectOne("admin.admin_read_id", admid);
  }

  @Override
  public int admin_update(AdminVO adminVO) {
    return mybatis.update("admin.admin_update", adminVO);
  }

  @Override
  public int admin_login(HashMap hashMap) {
    return mybatis.selectOne("admin.admin_login", hashMap);
  }
  
  @Override
  public String find_admid(HashMap hashMap) {
    return mybatis.selectOne("admin.find_admid", hashMap);
  }

  @Override
  public String find_admpasswd(HashMap hashMap) {
    return mybatis.selectOne("admin.find_admpasswd", hashMap);
  }

  @Override
  public int admpasswd_change(AdminVO adminVO) {
    return mybatis.update("admin.admpasswd_change", adminVO);
  }

}
