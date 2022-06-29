package com.icode.wanxh.template.api.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 优惠券满减规则
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    // 折扣
    private Long quota;

    // 最低多少消费才能用
    private Long threshold;
}
