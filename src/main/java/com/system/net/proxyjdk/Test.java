package com.system.net.proxyjdk;

import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        BusinessProcessorImpl bpimpl = new BusinessProcessorImpl();
        BusinessProcessorHandler handler = new BusinessProcessorHandler(bpimpl);
        BusinessProcessor bp = (BusinessProcessor) Proxy.newProxyInstance(bpimpl.getClass().getClassLoader(), bpimpl.getClass().getInterfaces(), handler);
        bp.processBusiness();
    }
}