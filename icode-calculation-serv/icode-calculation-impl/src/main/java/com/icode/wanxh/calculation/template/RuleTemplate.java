package com.icode.wanxh.calculation.template;

import com.icode.wanxh.calculation.api.beans.ShoppingCart;

public interface RuleTemplate {

    // 计算优惠券
    ShoppingCart calculate(ShoppingCart settlement);

}
