package com.deadlockArena;

import java.awt.EventQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DeadlockArenaApplication implements CommandLineRunner {

	public static final Logger LOG = LoggerFactory.getLogger(DeadlockArenaApplication.class);

	private static final boolean RUN_APP = true;

	@Override
	public void run(String... arg) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if (RUN_APP) {
					Game g = new Game();
					g.executePhase1();
					g.executePhase2();
				}
			}
		});
	}

	public static void main(String [ ] args) {
		new SpringApplicationBuilder(DeadlockArenaApplication.class).headless(false).run(args);
	}

}
