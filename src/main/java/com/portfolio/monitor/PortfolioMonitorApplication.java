package com.portfolio.monitor;

import com.portfolio.monitor.publisher.PricePublisher;
import com.portfolio.monitor.publisher.PricePublisherTimerTask;
import com.portfolio.monitor.subscriber.PriceSubscriber;
import com.portfolio.monitor.subscriber.PriceSubscriberTimerTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PortfolioMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioMonitorApplication.class, args);
	}

	@Bean
	public PricePublisher pricePublisher(PricePublisherTimerTask pricePublisherTimerTask) {
		PricePublisher pricePublisher = new PricePublisher(pricePublisherTimerTask);
		pricePublisher.scheduledPublish();
		return pricePublisher;
	}

	@Bean
	public PriceSubscriber priceSubscriber(PriceSubscriberTimerTask priceSubscriberTimerTask) {
		PriceSubscriber priceSubscriber = new PriceSubscriber(priceSubscriberTimerTask);
		priceSubscriber.scheduledPoll();
		return priceSubscriber;
	}
}
