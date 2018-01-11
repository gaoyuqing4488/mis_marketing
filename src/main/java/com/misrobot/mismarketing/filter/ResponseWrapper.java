package com.misrobot.mismarketing.filter;

import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by GYQ on 2017/7/26.
 */
public class ResponseWrapper extends HttpServletResponseWrapper {
    ByteArrayOutputStream bos;
    PrintWriter writer;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        bos = new ByteArrayOutputStream();
        writer = new PrintWriter(bos);
    }

    public String getBody() {
        try {
            writer.flush();
            return bos.toString("UTF-8");
        } catch (Exception e) {

        }
        return "";
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return writer;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        bos.close();
        writer.close();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {
            @Override
            public void write(int b) throws IOException {
                bos.write(b);
            }
        };
    }

    public void close() {
        writer.close();
    }
}
