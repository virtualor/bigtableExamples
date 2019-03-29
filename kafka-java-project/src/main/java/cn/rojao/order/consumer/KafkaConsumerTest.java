package cn.rojao.order.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;

/**
 * Date:2019/3/27
 * Author:jianxian mao
 * Describe:
 */
public class KafkaConsumerTest {


    @Test
    public void testConsumer(){
        Properties config = new Properties();
        // 设置kafka服务列表，多个用逗号分隔
        config.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "node-1:9092,node-2:9092");
        // 设置消费者分组id
        config.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
        // 设置客户端id
        config.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, "my-test-client");
        // 设置移量自动提交
        config.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        // 设置偏移量提交时间间隔
        config.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        // 设置序反列化消息 Key 的类
        config.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 设置序反列化消息 value 的类
        config.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());


        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(config);
        kafkaConsumer.subscribe(Arrays.asList("order"));

        while(true){
            ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                String value = record.value();
                long offset = record.offset();
                System.out.println("value = " + value + ", offset = " + offset);
            }
        }
    }
}
