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
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.deadlockArena.backEnd")
public class DeadlockArenaBackEnd implements CommandLineRunner {

	private static ApplicationContext applicationContext;

	public static final Logger LOG = LoggerFactory.getLogger(DeadlockArenaBackEnd.class);

	@Override
	public void run(final String... arg) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

			}
		});
	}

	public static void main(final String[] args) {
		final SpringApplicationBuilder sAB = new SpringApplicationBuilder(DeadlockArenaBackEnd.class);
		sAB.headless(false);
		sAB.run(args);

		DeadlockArenaBackEnd.applicationContext = sAB.context();
		DeadlockArenaBackEnd.getAllBeans();
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
