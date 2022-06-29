package com.icode.wanxh.calculation.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.icode.wanxh.calculation.api.beans.ShoppingCart;
import com.icode.wanxh.calculation.api.beans.SimulationOrder;
import com.icode.wanxh.calculation.api.beans.SimulationResponse;
import com.icode.wanxh.calculation.service.CouponCalculationService;
import com.icode.wanxh.calculation.template.CouponTemplateFactory;
import com.icode.wanxh.calculation.template.RuleTemplate;
import com.icode.wanxh.template.api.beans.CouponInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CouponCalculationServiceImpl implements CouponCalculationService {

    @Autowired
    private CouponTemplateFactory couponProcessorFactory;

    /**
     * 优惠券结算
     * 通过Factory类决定使用哪个底层Rule，底层规则对上层透明
     * @param cart
     * @return
     */
    @Override
    public ShoppingCart calculateOrderPrice(ShoppingCart cart) {
        log.info("calculate order price: {}", JSON.toJSONString(cart));
        RuleTemplate ruleTemplate = couponProcessorFactory.getTemplate(cart);
        return ruleTemplate.calculate(cart);
    }

    // 对所有优惠券进行试算，看哪个最赚钱
    @Override
    public SimulationResponse simulateOrder(SimulationOrder order) {
        SimulationResponse response = new SimulationResponse();
        Long minOrderPrice = Long.MAX_VALUE;

        // 计算每一个优惠券的订单价格
        for (CouponInfo coupon : order.getCouponInfos()) {
            ShoppingCart cart = new ShoppingCart();
            cart.setProducts(order.getProducts());
            cart.setCouponInfos(Lists.newArrayList(coupon));
            cart = couponProcessorFactory.getTemplate(cart).calculate(cart);

            Long couponId = coupon.getId();
            Long orderPrice = cart.getCost();

            // 设置当前优惠券对应的订单价格
            response.getCouponToOrderPrice().put(couponId, orderPrice);

            // 比较订单价格，设置当前最优优惠券的ID
            if (minOrderPrice > orderPrice) {
                response.setBestCouponId(coupon.getId());
                minOrderPrice = orderPrice;
            }
        }
        return response;
    }
}
