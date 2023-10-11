package org.example.untils;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.AppendObjectRequest;
import com.qcloud.cos.model.AppendObjectResult;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 腾讯云的相关配置
 */
@Slf4j
@Component
@PropertySource(value = "classpath:tengxunyun.properties")
public class TenxunUtils {

    @Value("${cos.accessKey}")
    private String accessKey;

    @Value("${cos.secretKey}")
    private String secretKey;

    @Value("${cos.regionName}")
    private String regionName;

    @Value("${cos.bucketName}")
    private String bucketName;

    @Value("${cos.keyName}")
    private String keyName;

    /**
     * 上次图片对接腾讯CDN
     *
     * @param fileDataFileName
     * @param request
     * @return
     */
    public String ContentCOS(MultipartFile fileDataFileName, HttpServletRequest request) {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(regionName));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // 获取文件类型
        String name = fileDataFileName.getOriginalFilename();
        String fileType = name.substring(name.lastIndexOf(".") + 1);

        //设置图片在 腾讯云cos中的文件名，按照当前是年月日分层
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String datePath = new DateTime().toString("yyyy/MM/dd");

        // bucket名需包含appid
        String key = keyName + "/" + datePath + "/" + uuid + "." + fileType;
        String url = null;
        try {
            // 处理文件路径
            String filePath = request.getSession().getServletContext().getRealPath("/") + fileDataFileName.getOriginalFilename();
            fileDataFileName.transferTo(new File(filePath));
            File localFile = new File(filePath);

            // 报错请求对象
            AppendObjectRequest appendObjectRequest = new AppendObjectRequest(bucketName, key, localFile);
            // 设置节点
            appendObjectRequest.setPosition(0L);
            AppendObjectResult appendObjectResult = cosclient.appendObject(appendObjectRequest);
            // 文件大小
            long nextAppendPosition = appendObjectResult.getNextAppendPosition();
            log.info("文件大小：{}", nextAppendPosition);

            // 获取返回对象
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
            COSObject cosObject = cosclient.getObject(getObjectRequest);
            url = cosObject.getObjectContent().getHttpRequest().getURI().toString();
            log.info("COS对象地址：{}", url);

        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            cosclient.shutdown();
        }
        return url;
    }
}
