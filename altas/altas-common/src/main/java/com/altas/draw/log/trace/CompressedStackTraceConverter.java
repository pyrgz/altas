package com.altas.draw.log.trace;

import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.IThrowableProxy;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

import java.util.Locale;
import java.util.UUID;

public class CompressedStackTraceConverter extends ThrowableProxyConverter {
    public CompressedStackTraceConverter() {
    }
 
    @Override
    protected String throwableProxyToString(IThrowableProxy tp) {
        String original = super.throwableProxyToString(tp);
        String traceId = MDC.get("traceId");
        if (StringUtils.isEmpty(traceId)) {
            traceId =  UUID.randomUUID().toString().toUpperCase();
        }
 
        traceId = "\t[ably-stack]\t[" + traceId + "]";
        String osName = System.getProperties().get("os.name").toString().toLowerCase(Locale.ROOT);
        return osName.startsWith("win") ? original.replaceAll("\r\n\t", traceId + "\r\n\t") : original.replaceAll("\n\t", traceId + "\n\t");
    }
}