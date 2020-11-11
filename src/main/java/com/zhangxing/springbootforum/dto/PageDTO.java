package com.zhangxing.springbootforum.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/11 14:23
 */
@Data
public class PageDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirst;
    private boolean showNext;
    private boolean showEnd;
    private Integer page;
    private Integer totalPage;

    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        this.page = page;

        pages.add(page);
        //封装pages
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }


        //是否展示上一页
        showPrevious = page != 1;
        //是否展示下一页
        showNext = page != totalPage;

        //是否展示第一页
        showFirst = !pages.contains(1);

        //是否展示最后一页
        showEnd = !pages.contains(totalPage);

    }
}
