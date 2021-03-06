package cn.yiidii.pigeon.rbac.api.enumeration;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * @author: YiiDii Wang
 * @create: 2021-03-17 21:38
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Sex", description = "性别枚举")
public enum Sex {

    /**
     * 未知
     */
    N(0, "未知"),
    /**
     * 男
     */
    M(1, "男"),
    /**
     * 女
     */
    W(2, "女"),
    ;

    @EnumValue
    private int code;
    private String desc;

}
