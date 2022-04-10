package com.wyizd.recommend;

import com.google.zxing.WriterException;
import com.wyizd.recommend.utils.CreateFolderSample;
import com.wyizd.recommend.utils.ErCodeGenerator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class ErCodeTest {
    @Test
    public void test() throws IOException, WriterException {

        InputStream is = ErCodeGenerator.gen("http://www.baidu.com");

        FileOutputStream fos = new FileOutputStream("/Users/padin/b.jpeg");

        byte[] b = new byte[1024];

        int length;

        while( (length = is.read(b))>0){
            fos.write(b,0,length);
        }

        is.close();

        fos.close();
    }

    @Test
    public void testUpload() throws IOException, WriterException {

        InputStream is = ErCodeGenerator.gen("http://www.baidu.com");
        CreateFolderSample.upload(is,"1234567123456.jpeg");




    }
}
