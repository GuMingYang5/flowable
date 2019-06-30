package com.gu.demo.commons;


import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 * @description 修改响应的参数
 * @author gumingyang
 **/
public class CipherServletResponseWrapper  extends HttpServletResponseWrapper {
    private ByteArrayOutputStream buffer;
    private ServletOutputStream out;
    private PrintWriter writer;

    public CipherServletResponseWrapper(HttpServletResponse resp) throws IOException {
        super(resp);
        buffer = new ByteArrayOutputStream();
        out = new ResponseServletOutputStream(buffer);
        writer = new PrintWriter(new OutputStreamWriter(buffer,
                this.getCharacterEncoding()));
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        //在调用getInputStream函数时，创建新的流，包含原先数据流中的信息，然后返回
        return new CipherServletResponseWrapper.ResponseServletOutputStream(out);
    }

    @Override
    public PrintWriter getWriter() throws UnsupportedEncodingException {
        return writer;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
        if (writer != null) {
            writer.flush();
        }
    }

    @Override
    public void reset() {
        buffer.reset();
    }

    public byte[] getResponseData() throws IOException {
        flushBuffer();
        return buffer.toByteArray();
    }

    protected class ResponseServletOutputStream extends ServletOutputStream {
        private OutputStream outputStream;

        public ResponseServletOutputStream(OutputStream outputStream)
                throws IOException {
            this.outputStream = outputStream;
        }

        @Override
        public void write(int b) throws IOException {
            outputStream.write(b);
        }

        @Override
        public void write(byte[] b) throws IOException {
            outputStream.write(b, 0, b.length);
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }

        @Override
        public boolean isReady() {
            return false;
        }
    }
}
