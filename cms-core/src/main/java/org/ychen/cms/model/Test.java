package org.ychen.cms.model;

import java.math.BigDecimal;

/**
 * Created by cy on 17/1/11.
 */
public class Test {
    public BigDecimal result(){
        if (true) {
            BigDecimal balanceWhenAccepted = new BigDecimal(5);
            BigDecimal programAmount       = null;

            if ((null != balanceWhenAccepted) && (null != programAmount)) {
                return balanceWhenAccepted.subtract(programAmount);
            }
        }
        return BigDecimal.ZERO;
    }
}
