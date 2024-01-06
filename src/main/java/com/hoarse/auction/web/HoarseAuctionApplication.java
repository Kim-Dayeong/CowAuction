package com.hoarse.auction.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //basetime
public class HoarseAuctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoarseAuctionApplication.class, args);
	}

}
