package dev.mvc.sale;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.sale.SaleDAO")
public class SaleDAO implements SaleDAOInter {

  @Autowired
  private SqlSessionTemplate mybatis;
  
  public SaleDAO() {
    // System.out.println(" => SaleDAO created ");
  }
  
  /* 등록 */
  @Override
  public int create(SaleVO saleVO) {
    int count = mybatis.insert("sale.create", saleVO);
    return count;
  }

  /* 검색 + 목록 + 페이징 */
  @Override
  public List<SaleVO> list_search(HashMap hashMap) {
    List <SaleVO> list = mybatis.selectList("sale.list_search", hashMap);
    return list;
  }

  /* 검색된 레코드 갯수 산출 */
  @Override
  public int search_count(HashMap hashMap) {
    int count = mybatis.selectOne("sale.search_count", hashMap);
    return count;
  }

  /* 게시글 조회 */
  @Override
  public SaleVO read(int saleno) {
    SaleVO saleVO = mybatis.selectOne("sale.read", saleno);
    return saleVO;
  }

  /* 이전글 조회 */
  @Override
  public int read_pre(int saleno) {
    int count = mybatis.selectOne("sale.read_pre", saleno);
    return count;
  }

  /* 다음글 조회 */
  @Override
  public int read_post(int saleno) {
    int count = mybatis.selectOne("sale.read_post", saleno);
    return count;
  }

  /* 조회수 상승 */
  @Override
  public int increaseCnt(int saleno) {
    int count = mybatis.update("sale.increaseCnt", saleno);
    return count;
  }

  /* 수정 */
  @Override
  public int update(SaleVO saleVO) {
    int count = mybatis.update("sale.update", saleVO);
    return count;
  }

  /* 삭제 */
  @Override
  public int delete(int saleno) {
    int count = mybatis.delete("sale.delete", saleno);
    return count;
  }

  /* 아이디 검사 */
  @Override
  public int member_check(HashMap<String, Object> hashMap) {
    int count = mybatis.selectOne("sale.member_check", hashMap);
    return count;
  }

  /* 관리자 게시글 등록*/
  @Override
  public int create_admin(SaleVO saleVO) {
    return mybatis.insert("sale.create_admin", saleVO);
  }

}
