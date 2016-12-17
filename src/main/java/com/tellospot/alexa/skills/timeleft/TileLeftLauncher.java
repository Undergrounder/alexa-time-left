package com.tellospot.alexa.skills.timeleft;

import com.amazon.speech.Sdk;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.servlet.SpeechletServlet;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;

/**
 * Created by Adrian Tello on 17.12.2016.
 */
public class TileLeftLauncher {

    private static final int PORT = 8888;
    private static final String HTTPS_SCHEME = "https";

    private TileLeftLauncher() {
    }

    public static void main(final String[] args) throws Exception {
        // Configure logging to output to the console with default level of INFO
        BasicConfigurator.configure();

        // Configure server and its associated servlets
        Server server = new Server();
        SslConnectionFactory sslConnectionFactory = new SslConnectionFactory();
        SslContextFactory sslContextFactory = sslConnectionFactory.getSslContextFactory();
        sslContextFactory.setKeyStorePath(System.getProperty("javax.net.ssl.keyStore"));
        sslContextFactory.setKeyStorePassword(System.getProperty("javax.net.ssl.keyStorePassword"));
        sslContextFactory.setIncludeCipherSuites(Sdk.SUPPORTED_CIPHER_SUITES);

        HttpConfiguration httpConf = new HttpConfiguration();
        httpConf.setSecurePort(PORT);
        httpConf.setSecureScheme(HTTPS_SCHEME);
        httpConf.addCustomizer(new SecureRequestCustomizer());
        HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(httpConf);

        ServerConnector serverConnector =
                new ServerConnector(server, sslConnectionFactory, httpConnectionFactory);
        serverConnector.setPort(PORT);

        Connector[] connectors = new Connector[1];
        connectors[0] = serverConnector;
        server.setConnectors(connectors);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        //context.addServlet(new ServletHolder(createServlet(new HelloWorldSpeechlet())), "/");
        server.start();
        server.join();
    }

    private static SpeechletServlet createServlet(final Speechlet speechlet) {
        SpeechletServlet servlet = new SpeechletServlet();
        servlet.setSpeechlet(speechlet);
        return servlet;
    }
}
