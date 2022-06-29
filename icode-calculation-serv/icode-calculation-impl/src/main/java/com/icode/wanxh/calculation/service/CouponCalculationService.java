package com.icode.wanxh.calculation.service;

import com.icode.wanxh.calculation.api.beans.ShoppingCart;
import com.icode.wanxh.calculation.api.beans.SimulationOrder;
import com.icode.wanxh.calculation.api.beans.SimulationResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface CouponCalculationService {

    ShoppingCart calculateOrderPrice(@RequestBody ShoppingCart cart);

    SimulationResponse simulateOrder(@RequestBody SimulationOrder simulationOrder);
}
