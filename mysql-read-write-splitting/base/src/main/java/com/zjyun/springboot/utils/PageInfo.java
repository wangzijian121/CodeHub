package com.zjyun.springboot.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Data
public class PageInfo<T> {

    /**
     * totalList
     */
    private List<T> totalList = Collections.emptyList();
    /**
     * total
     */
    private Integer total = 0;
    /**
     * total Page
     */
    @Setter
    private Integer totalPage;
    /**
     * page size
     */
    private Integer pageSize = 20;
    /**
     * current page
     */
    private Integer currentPage = 0;
    /**
     * pageNo
     */
    private Integer pageNo;

    public PageInfo() {

    }

    public PageInfo(Integer currentPage, Integer pageSize) {
        if (currentPage == null) {
            currentPage = 1;
        }
        this.pageNo = (currentPage - 1) * pageSize;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public static <T> PageInfo<T> of(IPage<T> iPage) {
        PageInfo<T> pageInfo = new PageInfo<>((int) iPage.getCurrent(), (int) iPage.getSize());
        pageInfo.setTotalList(iPage.getRecords());
        pageInfo.setTotal((int) iPage.getTotal());
        return pageInfo;
    }

    public static <T> PageInfo<T> of(Integer currentPage, Integer pageSize) {
        return new PageInfo<>(currentPage, pageSize);
    }

    public Integer getTotalPage() {
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        this.totalPage =
                (this.total % this.pageSize) == 0
                        ? ((this.total / this.pageSize) == 0 ? 1 : (this.total / this.pageSize))
                        : (this.total / this.pageSize + 1);
        return this.totalPage;
    }
}
