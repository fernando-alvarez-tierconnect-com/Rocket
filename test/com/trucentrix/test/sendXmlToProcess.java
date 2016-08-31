package com.trucentrix.test;

import java.io.*;
import java.net.*;
import java.net.Proxy.Type;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * Created by falvarez on 12/5/2014.
 */

public class sendXmlToProcess {
    public static String processXmlFiles(File file) {
        String httpUrl = "http://10.100.1.172/trinary_jedx_qa_3.9.2_5667/";
        String strFile = "";
        String res = "[ERROR]";
        HttpURLConnection connection = null;
        try {
//            Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("proxyserver", 8080));
//            http://localhost:8080/truedx/public/update.do?xmlData=fernando&submit=update
//            http://localhost:8080/truedx/public/update.do?actionMethod=update

            //works fine
//            10.100.1.186:8080/trinary_jedx_qa_3.9.2_5642/public/update.do?xmlData=fer&actionMethod=update

            //POST
            strFile = readFile(file);
            String urlParameters = "xmlData=" + URLEncoder.encode(strFile, "UTF-8") + "&actionMethod=update";
            String request = "http://10.100.1.172/trinary_jedx_qa_3.9.2_5667/public/update.do?actionMethod=update";
            URL url = new URL(request);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            connection.setUseCaches (false);

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
//            connection.disconnect();
//
//
//            strFile = readFile(file);
//            connection = (HttpURLConnection) new URL(httpUrl + "public/update.do?xmlData=" + URLEncoder.encode(strFile, "UTF-8")).openConnection();// + "&actionMethod=update").openConnection();
//            connection.setRequestMethod("GET");
            // connection.setConnectTimeout(1000);

            int code = connection.getResponseCode();
            if (code > 200 && code < 299) {
                res = "[SUCCESS]";
            } else {
                res = "[ERROR] code=" + code + " - httpUrl=" + httpUrl;
            }
        } catch (Exception e) {
            res = "[ERROR] httpUrl=" + httpUrl + " - error=" + e;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return res;
    }

    public static String getListFiles(String directoryName) {
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                processXmlFiles(file);
            }
        }
        return "";
    }

    public static String readFile(File file) throws IOException {
        return FileUtils.readFileToString(file);
    }

    private static String inputStreamResponseXmlToString(InputStream in) throws Exception {
        DocumentBuilderFactory factory = null;
        DocumentBuilder builder = null;
        Document doc = null;
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        doc = builder.parse(new InputSource(in));

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        transformer.transform(source, result);
        return sw.toString();
    }

    public static void main(String[] args) {
            getListFiles("C:\\xmlFiles");
    }

}
