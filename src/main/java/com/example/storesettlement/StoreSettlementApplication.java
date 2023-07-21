package com.example.storesettlement;

import org.apache.commons.logging.Log;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.NumberFormat;


@SpringBootApplication
@RequiredArgsConstructor
public class StoreSettlementApplication {

	private static Log logger = LogFactory.getLog(StoreSettlementApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StoreSettlementApplication.class, args);

		Runtime runtime = Runtime.getRuntime();
		final NumberFormat format = NumberFormat.getInstance();

		final long maxMemory = runtime.maxMemory();
		final long allocatedMemory = runtime.totalMemory();
		final long freeMemory = runtime.freeMemory();
		final long mb = 1024 * 1024;
		final String mega = "MB";

		logger.info("==========================Memory Info============================");
		logger.info("Free memory: " + format.format(freeMemory / mb) + mega);
		logger.info("Allocated memory: " + format.format(allocatedMemory / mb) + mega);
		logger.info("Max memory: " + format.format(maxMemory / mb) + mega);
		logger.info("Total Free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / mb) + mega);
		logger.info("=================================================================");
	}

}
