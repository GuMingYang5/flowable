package com.gu.demo.util.cipher.secret;


import com.gu.demo.util.cipher.AesEncryptUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @description 修改请求的参数
 * @author gumingyang
 **/
public class CipherServletRequestWrapper extends HttpServletRequestWrapper {
    private byte[] requestParamsData;
    private Charset charSet;

    public CipherServletRequestWrapper(HttpServletRequest request) throws Exception{
        super(request);
        //获取携带的参数并解密
        String paramData = getRequestPostStr(request);
        requestParamsData = AesEncryptUtils.decryptStringContent(paramData);
    }

    @Override
    public ServletInputStream getInputStream(){
        //在调用getInputStream函数时，创建新的流，包含原先数据流中的信息，然后返回   [解密]
        return new CipherServletRequestWrapper.RequestServletInputStream(new ByteArrayInputStream(requestParamsData));
    }

    public String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        String charSetStr = request.getCharacterEncoding();
        if (charSetStr == null) {
            charSetStr = "UTF-8";
        }
        charSet = Charset.forName(charSetStr);

        return StreamUtils.copyToString(request.getInputStream(), charSet);
    }


    /**
     *  重写输入流
     */
    protected class RequestServletInputStream extends ServletInputStream{
        private InputStream inputStream;

        public RequestServletInputStream(InputStream inputStream){
            this.inputStream = inputStream;
        }

        @Override
        public int read() throws IOException {
            return inputStream.read();
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }
    }
}
