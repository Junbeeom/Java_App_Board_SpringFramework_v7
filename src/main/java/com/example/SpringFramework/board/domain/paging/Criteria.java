package com.example.SpringFramework.board.domain.paging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
/**
 * 페이지네이션에서 필요한 값을 가지고 있는 클래스
 */
public abstract class Criteria {
    // 화면 하단에 출력할 페이지 사이즈
    private int pageSize;

    // 현재 페이지 번호
    private int currentPage;

    // limit
    private int limit;

    // offset
    private int offset;

    // 검색 타입
    private String searchType;

    // 검색값
    private String searchValue;

    public Criteria() {
        this.pageSize = 5;
        this.offset = (this.pageSize - 1) * limit;
    }

    // 페이지네이션에 필요한 데이터 가공 메소드
    protected abstract void calculation(Criteria criteria, int TotalCount);
}