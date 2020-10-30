package com.deadlockarena.backend;

import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.deadlockArena.backEnd")
public class DeadlockArenaBackEnd {

	private static ApplicationContext applicationContext;

	public static final Logger LOG = LoggerFactory.getLogger(DeadlockArenaBackEnd.class);

	public static void main(final String[] args) {
		final SpringApplicationBuilder sAB = new SpringApplicationBuilder(DeadlockArenaBackEnd.class);
		sAB.headless(false);
		sAB.run(args);
		// DeadlockArenaBackEnd.getAllBeans();
	}

	private static void getAllBeans() {
		final String[] allBeanNames = DeadlockArenaBackEnd.applicationContext.getBeanDefinitionNames();
		try {
			final FileWriter myWriter = new FileWriter("beans.txt");
			for (final String beanName : allBeanNames) {
				myWriter.write(beanName + "\n");
			}
			myWriter.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}
