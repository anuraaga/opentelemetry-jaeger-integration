package com.example.address;

import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.exporter.jaeger.JaegerGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenTelemetryConfiguration {

	@Bean
	OpenTelemetry openTelemetry() {

		Resource serviceNameResource =
				Resource.create(Attributes.of(ResourceAttributes.SERVICE_NAME, "otel-jaeger-example"));

		// Export traces to Jaeger
		JaegerGrpcSpanExporter jaegerExporter =
				JaegerGrpcSpanExporter.builder()
				.setEndpoint("http://localhost:14250")
				.setTimeout(30, TimeUnit.SECONDS)
				.build();

		SdkTracerProvider tracerProvider =
				SdkTracerProvider.builder()
				.addSpanProcessor(SimpleSpanProcessor.create(jaegerExporter))
				.setResource(Resource.getDefault().merge(serviceNameResource))
				.build();

		return OpenTelemetrySdk.builder().setTracerProvider(tracerProvider).build();
	}

}
