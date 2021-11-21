package zvuv.zavakh.orders.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Value("${marketToOrdersQueueName}")
    private String marketQueueName;

    @Value("${ordersToInventoryQueueName}")
    private String inventoryQueueName;

    @Value("${ordersToInventoryExchangeName}")
    private String inventoryExchange;

    @Value("${ordersToInventoryRoutingKey}")
    private String inventoryRoutingKey;

    @Bean
    public ConnectionFactory getConnectionFactory() {
        return new CachingConnectionFactory("localhost");
    }
    /*
    @Bean
    public Queue queue() {
        return new Queue(marketQueueName);
    }
    */
    @Bean
    public Queue inventoryQueue() {
        return new Queue(inventoryQueueName);
    }

    @Bean
    DirectExchange inventoryExchange() {
        return new DirectExchange(inventoryExchange);
    }

    @Bean
    Binding inventoryBinding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder
                .bind(queue)
                .to(directExchange)
                .with(inventoryRoutingKey);
    }

    @Bean
    public MessageConverter getMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(getConnectionFactory());
    }

    @Bean("ordersRabbitTemplate")
    public AmqpTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(getConnectionFactory());
        rabbitTemplate.setMessageConverter(getMessageConverter());

        return rabbitTemplate;
    }
}
