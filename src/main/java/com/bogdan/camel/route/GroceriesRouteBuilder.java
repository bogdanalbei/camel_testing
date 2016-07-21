package com.bogdan.camel.route;

import com.bogdan.camel.model.Fruit;
import com.bogdan.camel.model.Vegetable;
import org.apache.camel.builder.RouteBuilder;

public class GroceriesRouteBuilder extends RouteBuilder {
    public static final String VEGETABLES_QUEUE = "seda:vegetables";
    public static final String FRUITS_QUEUE = "seda:fruits";
    public static final String GROCERIES_QUEUE = "seda:groceries";
    public static final String FRUIT_PROCESSOR = "fruitProcessor";
    public static final String VEGETABLE_PROCESSOR = "vegetableProcessor";

    public void configure() {
        from(VEGETABLES_QUEUE)
                .to(FRUIT_PROCESSOR);

        from(FRUITS_QUEUE)
                .to(VEGETABLE_PROCESSOR);

        from(GROCERIES_QUEUE)
                .choice()
                    //fruits go to the fruits queue
                    .when(body().isInstanceOf(Fruit.class))
                        .to(FRUITS_QUEUE)
                    //vegetables go to the vegetables queue
                .when(body().isInstanceOf(Vegetable.class))
                        .to(VEGETABLES_QUEUE)
                .endChoice();
    }

}