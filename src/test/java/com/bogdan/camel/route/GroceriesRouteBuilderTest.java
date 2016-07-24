package com.bogdan.camel.route;

import com.bogdan.camel.model.Fruit;
import com.bogdan.camel.model.Vegetable;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;


@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {GroceriesRouteBuilderTest.ContextConfig.class},
        loader = CamelSpringDelegatingTestContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GroceriesRouteBuilderTest {
    @Autowired
    @Qualifier(GroceriesRouteBuilder.FRUIT_PROCESSOR)
    MockEndpoint fruitProcessor;

    @Autowired
    @Qualifier(GroceriesRouteBuilder.VEGETABLE_PROCESSOR)
    MockEndpoint vegetableProcessor;

    @Autowired
    CamelContext camelContext;

    @Configuration
    public static class ContextConfig extends SingleRouteCamelConfiguration {
        @Override
        public RouteBuilder route() {
            return new GroceriesRouteBuilder();
        }

        @Bean
        @Qualifier(GroceriesRouteBuilder.FRUIT_PROCESSOR)
        MockEndpoint fruitProcessor() {
            return new MockEndpoint();
        }

        @Bean
        @Qualifier(GroceriesRouteBuilder.VEGETABLE_PROCESSOR)
        MockEndpoint vegetableProcessor() {
            return new MockEndpoint();
        }
    }

    @Test
    public void test_fruit_is_routed_to_the_fruits_processor_via_groceries_queue() throws InterruptedException {
        //given
        Fruit apple = new Fruit("apple", "England");
        ProducerTemplate producer = camelContext.createProducerTemplate();

        //when
        producer.sendBody(GroceriesRouteBuilder.GROCERIES_QUEUE, apple);

        //then
        fruitProcessor.expectedBodiesReceived(apple);
        fruitProcessor.assertIsSatisfied();

        vegetableProcessor.expectedMessageCount(0);
        vegetableProcessor.assertIsSatisfied();
    }

    @Test
    public void test_vegetable_is_routed_to_the_fruits_processor_via_groceries_queue() throws InterruptedException {
        //given
        Vegetable potato = new Vegetable("potato", "Scotland");
        ProducerTemplate producer = camelContext.createProducerTemplate();

        //when
        producer.sendBody(GroceriesRouteBuilder.GROCERIES_QUEUE, potato);

        //then
        vegetableProcessor.expectedBodiesReceived(potato);
        vegetableProcessor.assertIsSatisfied();

        fruitProcessor.expectedMessageCount(0);
        fruitProcessor.assertIsSatisfied();
    }
}