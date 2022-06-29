package com.ag.rocket.order;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderStep {

    private long orderId;
    private String desc;


    public static List<OrderStep> buildOrders(){
        List<OrderStep> orderList = new ArrayList<>();


        OrderStep orderStep1 = new OrderStep();
        orderStep1.setOrderId(1001l);
        orderStep1.setDesc("创建");
        orderList.add(orderStep1);

        OrderStep orderStep2 = new OrderStep();
        orderStep2.setOrderId(1002l);
        orderStep2.setDesc("创建");
        orderList.add(orderStep2);

        OrderStep orderStep3 = new OrderStep();
        orderStep3.setOrderId(1003l);
        orderStep3.setDesc("创建");
        orderList.add(orderStep3);

        OrderStep orderStep11 = new OrderStep();
        orderStep11.setOrderId(1001l);
        orderStep11.setDesc("付款");
        orderList.add(orderStep11);

        OrderStep orderStep22 = new OrderStep();
        orderStep22.setOrderId(1002l);
        orderStep22.setDesc("付款");
        orderList.add(orderStep22);

        OrderStep orderStep33 = new OrderStep();
        orderStep33.setOrderId(1003l);
        orderStep33.setDesc("付款");
        orderList.add(orderStep33);

        OrderStep orderStep111 = new OrderStep();
        orderStep111.setOrderId(1001l);
        orderStep111.setDesc("推送");
        orderList.add(orderStep111);

        OrderStep orderStep222 = new OrderStep();
        orderStep222.setOrderId(1002l);
        orderStep222.setDesc("推送");
        orderList.add(orderStep222);

        OrderStep orderStep333 = new OrderStep();
        orderStep333.setOrderId(1003l);
        orderStep333.setDesc("推送");
        orderList.add(orderStep333);


        return orderList;
    }

}
