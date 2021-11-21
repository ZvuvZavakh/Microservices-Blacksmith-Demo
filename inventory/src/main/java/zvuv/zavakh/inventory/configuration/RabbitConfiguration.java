package zvuv.zavakh.inventory.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Value("${inventoryToOrdersQueueName}")
    private String inventoryQueue;

    @Value("${ordersExchangeName}")
    private String ordersExchange;

    @Value("${inventoryToOrdersRoutingKey}")
    private String inventoryRoutingKey;

    @Value("${completedOrdersQueueName}")
    private String completedOrdersQueueName;

    @Value("${completedOrdersRoutingKey}")
    private String completedOrdersRoutingKey;

    @Value("${inventoryToBlacksmithQueueName}")
    private String blacksmithQueue;

    @Value("${blacksmithExchangeName}")
    private String blacksmithExchange;

    @Value("${inventoryToBlacksmithRoutingKey}")
    private String blacksmithRoutingKey;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean("inventoryQueue")
    public Queue inventoryQueue() {
        return new Queue(inventoryQueue);
    }

    @Bean("completedOrdersQueue")
    public Queue completedOrdersQueue() {
        return new Queue(completedOrdersQueueName);
    }

    @Bean("blacksmithQueue")
    public Queue blacksmithQueue() {
        return new Queue(blacksmithQueue);
    }

    @Bean("ordersExchange")
    public DirectExchange inventoryExchange() {
        return new DirectExchange(ordersExchange);
    }

    @Bean("blacksmithExchange")
    public DirectExchange blacksmithExchange() {
        return new DirectExchange(blacksmithExchange);
    }

    @Bean
    public Binding inventoryBinding(@Qualifier("inventoryQueue") Queue queue,
                                    @Qualifier("ordersExchange") DirectExchange directExchange) {
        return BindingBuilder
                .bind(queue)
                .to(directExchange)
                .with(inventoryRoutingKey);
    }

    @Bean
    public Binding completedOrdersBinding(@Qualifier("completedOrdersQueue") Queue queue,
                                          @Qualifier("ordersExchange") DirectExchange directExchange) {
        return BindingBuilder
                .bind(queue)
                .to(directExchange)
                .with(completedOrdersRoutingKey);
    }

    @Bean
    public Binding blacksmithBinding(@Qualifier("blacksmithQueue") Queue queue,
                                     @Qualifier("blacksmithExchange") DirectExchange directExchange) {
        return BindingBuilder
                .bind(queue)
                .to(directExchange)
                .with(blacksmithRoutingKey);
    }

    @Bean("inventoryRabbitTemplate")
    public AmqpTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(messageConverter());

        return rabbitTemplate;
    }
}
