package dev.mvc.contest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Remain {
  
  public static void main(String[] args) throws Exception {
    // TODO Auto-generated method stub
    ContestVO contestVO = new ContestVO();
    long time = System.currentTimeMillis();
    SimpleDateFormat dateform = new SimpleDateFormat("yyyy-MM-dd");
    String today = dateform.format(new Date(time));
    System.out.println("today ---> " + today);

    String conEnd = contestVO.getConEnd();
    conEnd = "2017-12-18";
    System.out.println("conEnd ---> " + conEnd);
    
    String conStart = contestVO.getConStart();
    conStart = "2018-01-18";
    System.out.println("conStart ---> " + conStart);
    
    Date date_e = dateform.parse(conEnd);
    System.out.println("date_e ---> " + date_e);
    
    Date date_s = dateform.parse(conStart);
    System.out.println("date_s ---> " + date_s);
    
    String endDate = new SimpleDateFormat("yyyy-MM-dd").format(date_e);
    System.out.println("endDate ---> " + endDate);
    
    String startDate = new SimpleDateFormat("yyyy-MM-dd").format(date_s);
    System.out.println("startDate ---> " + startDate);
    
    /*String endDay = dateform.format(conEnd);*/
    
    long now = (dateform.parse(today)).getTime();
    long end = (dateform.parse(endDate)).getTime();
    long start = (dateform.parse(startDate)).getTime();
    
    System.out.println("now ---> " + now);
    System.out.println("end ---> " + end);
    System.out.println("start ---> " + start);

    long diff_e = end - now;
    long conRemain_e = diff_e / (24 * 60 * 60 * 1000);
    
    long diff_s = start - now;
    long conRemain_s = diff_s / (24 * 60 * 60 * 1000);
    
    System.out.println("conRemain_e ---> " + conRemain_e);
    System.out.println("conRemain_s ---> " + conRemain_s);
    
    /*return conRemain_e;*/
  }

}
