/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javarest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 * @author e.garcia.san.martin
 */
public class JavaREST {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    }
    
    public static String httpGet(String urlStr) throws IOException {
      URL url = new URL(urlStr);
      HttpURLConnection conn =
          (HttpURLConnection) url.openConnection();

      if (conn.getResponseCode() != 200) {
        throw new IOException(conn.getResponseMessage());
      }

      // Buffer the result into a string
      BufferedReader rd = new BufferedReader(
          new InputStreamReader(conn.getInputStream()));
      StringBuilder sb = new StringBuilder();
      String line;
      while ((line = rd.readLine()) != null) {
        sb.append(line);
      }
      rd.close();

      conn.disconnect();
      return sb.toString();
    }
        
    public static String httpPost(String urlStr, String[] paramName,    String[] paramVal) throws Exception {
      URL url = new URL(urlStr);
      HttpURLConnection conn =
          (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setDoOutput(true);
      conn.setDoInput(true);
      conn.setUseCaches(false);
      conn.setAllowUserInteraction(false);
      conn.setRequestProperty("Content-Type",
          "application/x-www-form-urlencoded");

      // Create the form content
      OutputStream out = conn.getOutputStream();
      Writer writer = new OutputStreamWriter(out, "UTF-8");
      for (int i = 0; i < paramName.length; i++) {
        writer.write(paramName[i]);
        writer.write("=");
        writer.write(URLEncoder.encode(paramVal[i], "UTF-8"));
        writer.write("&");
      }
      writer.close();
      out.close();

      if (conn.getResponseCode() != 200) {
        throw new IOException(conn.getResponseMessage());
      }

      // Buffer the result into a string
      BufferedReader rd = new BufferedReader(
          new InputStreamReader(conn.getInputStream()));
      StringBuilder sb = new StringBuilder();
      String line;
      while ((line = rd.readLine()) != null) {
        sb.append(line);
      }
      rd.close();

      conn.disconnect();
      return sb.toString();
    }
}
