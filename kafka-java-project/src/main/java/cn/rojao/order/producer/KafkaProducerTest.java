package cn.rojao.order.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.Properties;

/**
 * Date:2019/3/27
 * Author:jianxian mao
 * Describe:
 */
public class KafkaProducerTest {

    @Test
    public void testProducer() throws Exception {
        Properties config = new Properties();

        // 设置kafka服务列表，多个用逗号分隔
        config.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "node-1:9092,node-2:9092");
        // 设置序列化消息 Key 的类
        config.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 设置序列化消息 value 的类
        config.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(config);

        for (int i = 0; i < 50; i++) {
            ProducerRecord order = new ProducerRecord("order", "data-" + i);
            kafkaProducer.send(order);

            System.out.println("发送消息----" + i);

            Thread.sleep(10);
        }


         kafkaProducer.close();


    }
}
