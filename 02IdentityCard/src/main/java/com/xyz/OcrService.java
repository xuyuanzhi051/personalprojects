package com.xyz;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aliyun.ocr.Client;
import com.aliyun.ocr.models.Config;
import com.aliyun.ocr.models.RecognizeIdentityCardAdvanceRequest;
import com.aliyun.ocr.models.RecognizeIdentityCardResponse;
import com.aliyun.teautil.models.RuntimeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class OcrService {

    private Client orcClient;
    private RuntimeOptions runtimeOptions;

    @Value("${aliapi.accessKeyId}")
    private String accessKeyId;
    @Value("${aliapi.accessKeySecret}")
    private String accessKeySecret;

    @PostConstruct
    private void init() throws Exception {
        Config config = new Config();
        config.endpointType = "access_key";
        config.regionId = "cn-shanghai";
        config.accessKeyId = accessKeyId;
        config.accessKeySecret = accessKeySecret;
        config.endpoint = "ocr.cn-shanghai.aliyuncs.com";

        orcClient = new Client(config);
        runtimeOptions = new RuntimeOptions();
    }


    public Map<String, String> RecognizeIdCard(String fielpath, String side) throws Exception {
        RecognizeIdentityCardAdvanceRequest request = new RecognizeIdentityCardAdvanceRequest();
        request.imageURLObject = Files.newInputStream(Paths.get(fielpath));
        request.side = side;
        RecognizeIdentityCardResponse response = orcClient.recognizeIdentityCardAdvance(request, runtimeOptions);
        return "face".equals(side) ?
                JSON.parseObject(JSON.toJSONString(response.data.frontResult), new TypeReference<Map<String, String>>(){})
                : JSON.parseObject(JSON.toJSONString(response.data.backResult), new TypeReference<Map<String, String>>(){});
    }
}