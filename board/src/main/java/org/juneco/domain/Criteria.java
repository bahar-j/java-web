package org.juneco.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Setter
@Getter
public class Criteria {

  private int pageNum; // 현재 페이지
  private int amount; // 페이지당 글 갯수
  
  private String type;
  private String keyword;

  // pageNum, amount가 param으로 넘어오지 않은 경우 (default)
  public Criteria() {
    this(1, 10);
  }

  public Criteria(int pageNum, int amount) {
    this.pageNum = pageNum;
    this.amount = amount;
  }
  
  public String[] getTypeArr() {
    
    return type == null? new String[] {}: type.split("");
  }
}