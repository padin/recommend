package com.wyizd.recommend.utils;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.github.jaiimageio.impl.plugins.clib.InputStreamAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * This sample demonstrates how to create an empty folder under
 * specfied bucket to Aliyun OSS using the OSS SDK for Java.
 */
@Component
public class CreateFolderSample {

//    private static String endpoint = "oss-cn-hangzhou-internal.aliyuncs.com";
    @Value("${app.endpoint")
    private static String endpoint = "";
    @Value("${app.accessKeyId")
    private static String accessKeyId = "";
    @Value("${app.accessKeySecret")
    private static String accessKeySecret = "";
    @Value("${app.bucketName")
    private static String bucketName = "";
    @Value("${app.keySuffixWithSlash")
    private static final String keySuffixWithSlash = "";

    public static void upload(InputStream is,String fileName) throws IOException {
        /*
         * Constructs a client instance with your account for accessing OSS
         */
        OSS client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            /*
             * Create an empty folder without request body, note that the key must be
             * suffixed with a slash
             */
            client.putObject(bucketName, keySuffixWithSlash+fileName, is);
            System.out.println("Creating an empty folder " + keySuffixWithSlash+fileName + "\n");

            /*
             * Verify whether the size of the empty folder is zero
             */
            OSSObject object = client.getObject(bucketName, keySuffixWithSlash);
            System.out.println("Size of the empty folder '" + object.getKey() + "' is " +
                    object.getObjectMetadata().getContentLength());
            object.getObjectContent().close();
            is.close();

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorMessage());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            client.shutdown();
        }
    }
}