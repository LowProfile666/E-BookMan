package com.zsm.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private int id; // 图书ID（主键，自增）
    private String isbn; // ISBN编号（国际标准图书编号，唯一标识每本书）
    private String category; // 图书分类（如文学、科技、教育等）
    private String name; // 图书名称
    private String publisher; // 出版社名称
    private double price; // 图书售价
    private String remarks; // 图书备注信息（补充说明或描述）
    private String createTime; // 创建时间（记录图书数据的创建时间）
    private String updateTime; // 修改时间（记录图书数据的最后更新时间）
    private boolean isDeleted; // 逻辑删除标志（true-已删除，false-未删除）
}

