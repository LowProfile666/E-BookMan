package com.zsm.bean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用响应结果类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class R {
    private Integer code; // 状态码，例如 200 表示成功，400 或其他值表示失败
    private String msg;   // 响应消息，例如 "操作成功" 或 "操作失败"
    private Object data;  // 返回的数据内容

    /**
     * 快速生成成功响应（无消息，无数据）
     * @return 成功的 R 对象
     */
    public static R OK() {
        return R.builder()
                .code(200)
                .msg("操作成功")
                .build();
    }

    /**
     * 快速生成成功响应（带消息，无数据）
     * @param msg 自定义的成功消息
     * @return 成功的 R 对象
     */
    public static R OK(String msg) {
        return R.builder()
                .code(200)
                .msg(msg)
                .build();
    }

    /**
     * 快速生成成功响应（带消息和数据）
     * @param msg 成功消息
     * @param data 数据内容
     * @return 成功的 R 对象
     */
    public static R OK(String msg, Object data) {
        return R.builder()
                .code(200)
                .msg(msg)
                .data(data)
                .build();
    }

    /**
     * 快速生成失败响应（无消息，无数据）
     * @return 失败的 R 对象
     */
    public static R ERROR() {
        return R.builder()
                .code(500)
                .msg("操作失败")
                .build();
    }

    /**
     * 快速生成失败响应（带消息，无数据）
     * @param msg 自定义的失败消息
     * @return 失败的 R 对象
     */
    public static R ERROR(String msg) {
        return R.builder()
                .code(500)
                .msg(msg)
                .build();
    }

    /**
     * 快速生成失败响应（带消息和数据）
     * @param msg 失败消息
     * @param data 错误数据内容
     * @return 失败的 R 对象
     */
    public static R ERROR(String msg, Object data) {
        return R.builder()
                .code(500)
                .msg(msg)
                .data(data)
                .build();
    }

    /**
     * 自定义响应（灵活设置状态码、消息和数据）
     * @param code 状态码
     * @param msg 消息
     * @param data 数据
     * @return 自定义 R 对象
     */
    public static R response(Integer code, String msg, Object data) {
        return R.builder()
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }
}
