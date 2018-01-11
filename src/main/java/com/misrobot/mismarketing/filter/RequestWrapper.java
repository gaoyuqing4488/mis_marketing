package com.misrobot.mismarketing.filter;

import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by GYQ on 2017/7/26.
 */
public class RequestWrapper extends HttpServletRequestWrapper {
    private final static Logger log = LoggerFactory.getLogger(RequestWrapper.class);
    private final byte[] body;

    private static String getBody(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        String line = "";
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException ioe) {
            log.error(ioe.getMessage(), ioe);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                } finally {
                    inputStream = null;
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                } finally {
                    reader = null;
                }
            }
        }

        return sb.toString();
    }

    public String getBody() {
        try {
            return new String(body, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        body = getBody(request).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }
}
