package de.xiekang.apache_camel_JMS;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class WhitespotAggregationStrategy implements AggregationStrategy {
    /**
     * Aggregation Strategy used for whitespot
     * @param oldExchange: exchange got from queue with old start date and in working status
     * @param newExchange: exchange got from DI job with new start date and in working status
     * @return an exchange with new start date and not in working status
     */
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (newExchange == null) {
            // update old exchange status
            oldExchange.getIn().setHeader("type", "not in working!");
            return oldExchange;
        }
        newExchange.getIn().setHeader("type", "not in working");
        return newExchange;
    }
}
