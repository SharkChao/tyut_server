package com.winning;  
  
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

  
  
@SpringBootApplication  
@EnableScheduling 
public class Application implements EmbeddedServletContainerCustomizer {  
	
	public static ClassPathXmlApplicationContext ctx = null;
    public static void main(String[] args) throws IOException {  
    	ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringApplication.run(Application.class, args);  
    }  
    
    
    //修改端口方法
    @Override  
    public void customize(ConfigurableEmbeddedServletContainer container) {  
        container.setPort(9090);
    }  
    
	@Bean
	public SocketIOServer socketIOServer() {
		Configuration config = new Configuration();
//		config.setHostname("172.16.72.129");
//		config.setHostname("172.16.30.125");
		config.setPort(9092);
		final SocketIOServer server = new SocketIOServer(config);
		return server;
	}

	@Bean
	public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
		return new SpringAnnotationScanner(socketServer);
	}
    
}