package com.deadlockArena;

import java.awt.EventQueue;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DeadlockArenaBackEnd implements CommandLineRunner {

	private static ApplicationContext applicationContext;

	public static final Logger LOG = LoggerFactory.getLogger(DeadlockArenaBackEnd.class);

	@Override
	public void run(String... arg) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

			}
		});
	}

	public static void main(String[] args) {
		SpringApplicationBuilder sAB = new SpringApplicationBuilder(DeadlockArenaBackEnd.class);
		sAB.headless(false);
		sAB.run(args);

		applicationContext = sAB.context();
		getAllBeans();
	}

	private static void getAllBeans() {
		String[] allBeanNames = applicationContext.getBeanDefinitionNames();
		try {
			FileWriter myWriter = new FileWriter("beans.txt");
			for (String beanName : allBeanNames) {
				myWriter.write(beanName + "\n");
			}
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
