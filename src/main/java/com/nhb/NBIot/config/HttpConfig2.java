package com.nhb.NBIot.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class HttpConfig2 {

	@Value("${https.port}")
	private Integer port;

	@Value("${https.ssl.key-store-password}")
	private String key_store_password;

	@Value("${https.ssl.key-store-type}")
	private String key_store_type;

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		tomcat.addAdditionalTomcatConnectors(createSslConnector()); // 添加https
		return tomcat;
	}

	private Connector createSslConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
		try {
			Resource resource = new ClassPathResource("cert/tomcat.keystore");
			connector.setScheme("https");
			connector.setSecure(true);
			connector.setPort(port);
			protocol.setSSLEnabled(true);
			protocol.setKeystoreFile(resource.getURL().toString());
			protocol.setKeystorePass(key_store_password);
			protocol.setKeystoreType(key_store_type);
			return connector;
		} catch (Exception ex) {
			throw new IllegalStateException(
					"can't access keystore: [" + "keystore" + "] or truststore: [" + "keystore" + "]", ex);
		}
	}
}
