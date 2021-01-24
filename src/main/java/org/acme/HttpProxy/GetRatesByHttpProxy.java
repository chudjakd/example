package org.acme.HttpProxy;

import org.acme.dao.europerates.Envelope;
import org.acme.dao.skuskaxml.Root;
import org.apache.commons.io.IOUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


@ApplicationScoped
public class GetRatesByHttpProxy {

    private String httpPort="127.0.0.1";
    private String httpHost="8080";


    public void createHttpProxy(){

        try {
//            System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "false");
//            System.setProperty("jdk.http.auth.proxying.disabledSchemes", "false");
            URL weburl = new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
//            Proxy webProxy
//                    = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 3000));
            HttpURLConnection webProxyConnection
                    = (HttpURLConnection) weburl.openConnection();
            //webProxyConnection.setUseCaches(false);
            webProxyConnection.setRequestMethod("GET");
            webProxyConnection.connect();
            //System.out.println(webProxyConnection.getInputStream().readAllBytes());
            StringWriter writer= new StringWriter();
            InputStream inputStream= webProxyConnection.getInputStream();
            IOUtils.copy(inputStream,writer, StandardCharsets.UTF_8);
            String result= writer.toString();
            System.out.println(result);

            try{
                JAXBContext jaxbContext= JAXBContext.newInstance(Envelope.class);
                JAXBContext jaxbContextSkuska= JAXBContext.newInstance(Root.class);

                File file= new File("C:/Users/skuska.txt");
                File file2= new File("C:/Documents/skuska3.txt");

                Unmarshaller jaxbUnmarshaller= jaxbContext.createUnmarshaller();
                Unmarshaller jaxbUnmarshallerSkuska= jaxbContextSkuska.createUnmarshaller();

                Root root= (Root) jaxbUnmarshallerSkuska.unmarshal(file);
                System.out.println(root);

                Envelope envelope= (Envelope) jaxbUnmarshaller.unmarshal(weburl);
                System.out.println(envelope.getSubject());

            } catch (JAXBException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            System.out.println("Problem: "+ e.getMessage());
        } catch (IOException e) {
            System.out.println("Problem IOException : "+e.getMessage());

        }
    }

}
