package dev.mvc.job;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.job.JobDAO")
public class JobDAO implements JobDAOInter {
  
  @Autowired
  private SqlSessionTemplate mybatis;
  
  public JobDAO() {
    
  }

  @Override
  public int create(JobVO jobVO) {
    int count = mybatis.insert("job.create", jobVO);
    
    return count;
  }

  @Override
  public List<JobVO> list_all_jobinfo(HashMap hashMap) {
    return mybatis.selectList("job.list_all_jobinfo", hashMap);
  }
  
  @Override
  public List<JobVO> list_by_count() {
    return mybatis.selectList("job.list_by_count");
  }

  @Override
  public JobVO read(int jobNo) {
    JobVO jobVO = mybatis.selectOne("job.read", jobNo);
    
    return jobVO;
  }

  @Override
  public int max() {
    return mybatis.selectOne("job.max");
  }

  @Override
  public int mini() {
    return mybatis.selectOne("job.mini");
  }

  @Override
  public int increaseCnt(int jobNo) {
    return mybatis.update("job.increaseCnt", jobNo);
  }

  @Override
  public int search_count(HashMap hashMap) {
    return mybatis.selectOne("job.search_count", hashMap);
  }

  @Override
  public int update(JobVO jobVO) {
    return mybatis.update("job.update", jobVO);
  }

  @Override
  public int update_thumb(JobVO jobVO) {
    return mybatis.update("job.update_thumb", jobVO);
  }

  @Override
  public int update_file(JobVO jobVO) {
    return mybatis.update("job.update_file", jobVO);
  }

  @Override
  public int delete(int jobNo) {
    return mybatis.delete("job.delete", jobNo);
  }
  
}
