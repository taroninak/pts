//package com.pts;
//
//import com.pts.config.AppConfig;
//import com.pts.config.WebMvcConfig;
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.servlet.ServletContextHandler;
//import org.eclipse.jetty.servlet.ServletHolder;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
///**
// * Created by taronpetrosyan on 8/13/16.
// */
//public class WebApp {
//    public static void main(String[] args) {
//        final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
//        applicationContext.register(WebMvcConfig.class);
//
//        final ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(applicationContext));
//        final ServletContextHandler context = new ServletContextHandler();
//        context.setContextPath("/");
//        context.addServlet(servletHolder, "/*");
//
//        String webPort = System.getenv("PORT");
//        if (webPort == null || webPort.isEmpty()) {
//            webPort = "3000";
//        }
//
//        final Server server = new Server(Integer.valueOf(webPort));
//
//        server.setHandler(context);
//
//        try {
//            server.start();
//            server.join();
//        } catch (Exception ex) {
//
//        }
//
//    }
//}
