package com.example.demo.dtos;

import lombok.Data;

import java.util.List;

@Data
public class BookResponseDto<T> {
    private Long offset;
    private Integer size;
    private Integer page;
    private Integer totalPage;
    private Long total;
    private List<T> data;

}
