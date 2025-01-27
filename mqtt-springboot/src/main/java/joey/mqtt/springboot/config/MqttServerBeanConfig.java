package joey.mqtt.springboot.config;

import joey.mqtt.broker.constant.BusinessConstants;
import joey.mqtt.broker.MqttServer;
import joey.mqtt.broker.config.Config;
import joey.mqtt.broker.util.ConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author Joey
 * @date 2019/9/9
 */
@Configuration
public class MqttServerBeanConfig {
    @Resource
    private MqttConfig mqttConfig;

    @Bean
    public MqttServer mqttServer() throws Exception {
        //读取配置文件 优先级：命令行启动配置>jar包配置文件
        Config config = ConfigUtils.loadFromSystemProps(BusinessConstants.MQTT_CONFIG, new Config(mqttConfig.getServerConfig(), mqttConfig.getNettyConfig(), mqttConfig.getCustomConfig()));
        return new MqttServer(config);
    }
}
