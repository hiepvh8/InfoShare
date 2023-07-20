package com.vuhoanghiep.InfoShare.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String keyword; // Từ khóa tìm kiếm
    private Long count; // Số lần xuất hiện
}
