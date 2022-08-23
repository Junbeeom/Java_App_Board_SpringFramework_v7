package com.example.SpringFramework.board.domain.common;

import com.example.SpringFramework.board.domain.board.paging.Criteria;
import com.example.SpringFramework.board.domain.board.paging.PaginationInfo;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommonDTO extends Criteria {
    /** 페이징 정보 */
    private PaginationInfo paginationInfo;

    /** 삭제 여부 */
    private String is_deleted;

    /** 등록일 */
    private String created_ts;

    /** 수정일 */
    private String updated_ts;

    /** 삭제일 */
    private String deleted_ts;


}
