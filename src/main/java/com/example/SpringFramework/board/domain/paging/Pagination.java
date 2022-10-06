package com.example.SpringFramework.board.domain.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination extends Criteria {
    // 전체 페이지 개수
    private int totalPageCount;

    // 현재 페이지 기준 첫번째 노출 페이지
    private int startPage;

    // 현재 페이지 기준 마지막 노출 페이지
    private int endPage;

    // 이전 페이지 존재 여부
    private boolean hasPreviousPage;

    // 다음 페이지 존재 여부
    private boolean hasNextPage;

    public Pagination() {}

    public Pagination(Criteria criteria, int totalCount) {
        calculation(criteria, totalCount);

        // 현재 페이지 핸들링
        if(criteria.getCurrentPage() < 1) {
            criteria.setCurrentPage(1);
        } else if(criteria.getCurrentPage() > totalPageCount) {
            criteria.setCurrentPage(totalPageCount);
        }
    }

    // 페이지네이션에 필요한 데이터 가공
    @Override
    protected void calculation(Criteria criteria, int TotalCount) {
        // 전체 페이지 수
        totalPageCount= (int)Math.ceil(totalPageCount / criteria.getLimit());

        // 종료 페이지 수
        endPage = (int)Math.ceil(criteria.getCurrentPage() / criteria.getPageSize()) * criteria.getPageSize();
        endPage = (endPage > totalPageCount) ? totalPageCount : endPage;

        startPage = endPage - (criteria.getPageSize() - 1);

        // 이전 페이지 존재 여부
        hasPreviousPage = (startPage != 1) ? true : false;

        // 다음 페이지 존재 여부
        hasNextPage = (endPage * criteria.getLimit() < totalPageCount) ? true : false;
    }
}

