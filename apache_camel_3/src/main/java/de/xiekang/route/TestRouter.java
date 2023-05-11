package de.xiekang.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.async.SdkAsyncHttpClient;
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient;
import software.amazon.awssdk.http.nio.netty.ProxyConfiguration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.Bucket;

import java.util.List;

public class TestRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:c:/temp/input")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        AwsBasicCredentials credentials = AwsBasicCredentials.create("access", "secret");
                        SdkAsyncHttpClient httpClient = NettyNioAsyncHttpClient.builder()
                                .proxyConfiguration(ProxyConfiguration.builder()
                                        .host("host")
                                        .port(8080)
                                        .build())
                                .build();

                        S3AsyncClient client = S3AsyncClient.builder()
                                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                                .httpClient(httpClient)
                                .region(Region.EU_CENTRAL_1)
                                .build();

                        List<Bucket> buckets = client.listBuckets().get().buckets();
                        for (Bucket bucket: buckets) {
                            System.err.println(bucket.name());
                        }
                    }
                })
                .to("file:c:/temp/output");
    }
}