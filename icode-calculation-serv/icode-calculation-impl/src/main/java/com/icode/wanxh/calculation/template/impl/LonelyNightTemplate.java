package com.icode.wanxh.calculation.template.impl;

import com.icode.wanxh.calculation.template.AbstractRuleTemplate;
import com.icode.wanxh.calculation.template.RuleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * 午夜10点到次日凌晨2点之间下单，优惠金额翻倍
 */
@Slf4j
@Component
public class LonelyNightTemplate extends AbstractRuleTemplate implements RuleTemplate {


    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota) {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (hourOfDay >= 23 || hourOfDay < 2) {
            quota *= 2;
        }
        // quota减的价格
        Long benefitAmount = shopTotalAmount < quota ? shopTotalAmount : quota;
        return orderTotalAmount - benefitAmount;
    }
}
