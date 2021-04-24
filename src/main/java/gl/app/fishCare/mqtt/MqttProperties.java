package gl.app.fishCare.mqtt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {


	private String username;

	private String password;

	private Boolean cleanSession = false;

	private String[] serverURIs;

	private Boolean async = true;

	private int completionTimeout = 20000;

	private int keepAliveInterval = 30;

	private String clientId = "mqttPubClient";

	private int defaultQos = 1;
}
