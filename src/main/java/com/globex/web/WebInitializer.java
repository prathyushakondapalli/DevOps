package com.globex.web;

import com.globex.web.config.SpringConfig;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.lightstep.opentelemetry.launcher.OpenTelemetryConfiguration;



public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    public static void main(String[] args) throws Exception {
        OpenTelemetryConfiguration.newBuilder()
                .setServiceName("GlobexWeb")
                .setAccessToken("+3ebvEvYjVREOk3aWr0oMgAyrpEU1YQ6ROuFGAOEMcxkVMdRwrPKm9No+8pnvaDHUjPLtYjLcDy0AhnkmEJMMD4MvLB8LJnNMcULosBX")
                .install();

        Tracer tracer = GlobalOpenTelemetry
                .getTracer("instrumentation-library-name", "1.0.0");
        Span span = tracer.spanBuilder("start example").startSpan();
        span.end();
    }

}
