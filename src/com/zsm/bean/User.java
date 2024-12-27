package com.zsm.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Author : ZSM
 * Time :  2024/12/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Integer id; // 用户ID (主键，自增)
    private String username; // 用户名，唯一
    private String password; // 加密后的密码
    private String email; // 邮箱地址，唯一
    private String phone; // 手机号码
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
