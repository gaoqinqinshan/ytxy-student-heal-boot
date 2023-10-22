
import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootTest
class StudentHealthManageBootApplicationTests {

    //    @Test
//    void contextLoads() {
//    }
//    public static void main(String[] args) throws IOException {
//        OkHttpApi api = new OkHttpApi();
////        String run = api.run(" https://www.mxnzp.com/api/food_heat/type/list?app_id=cwqt6ejiikoato6q&app_secret=9czU2LhlSKxNFR7WJ0JUmGLVmCksRumT");
//        String run = api.run("  https://www.mxnzp.com/api/food_heat/food/search?keyword=芒果&page=1&app_id=cwqt6ejiikoato6q&app_secret=9czU2LhlSKxNFR7WJ0JUmGLVmCksRumT");
//        System.out.printf(run);
//        JSONObject jsonObject = JSONObject.parseObject(run);
//        System.out.println(jsonObject);
//
//    }
//    @Test
//    public ChatResponse chat(String q){
//        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";//这里看官方文档
//        String ApiKey = "sk-51aef6c9b69a4f1695f34c64d77d3966";//这里换成你自己的ApiKey
//
//        ChatRequest chatRequest = new ChatRequest(q);
//        String json = JSONUtil.toJsonStr(chatRequest);
//        //System.out.println(json);//正式发送给api前,查看请求的主要数据情况
//        String result = HttpRequest.post(url)
//                .header("Authorization","Bearer "+ ApiKey)
//                .header("Content-Type","application/json")
//                .body(json)
//                .execute().body();
//        System.out.println(result);
//        return JSONUtil.toBean(result, ChatResponse.class);
//    }
//    MailAccount account = new MailAccount();
//        account.setHost("smtp.qq.com");//邮件服务器的SMTP地址,网易邮箱为smtp.163.com
//        account.setPort(587);//邮件服务器的SMTP端口,QQ邮箱为465或587，网易邮箱为25
//        account.setAuth(true);
//        account.setFrom("xxxxxxxxxx@qq.com");//设置发送人邮箱
//        account.setUser("xxxxxxxxxx");//发送人用户名
//        account.setPass("xxxxxxxxxxxxxxxx");//密码或者授权码
//        account.isSslEnable();//部分邮箱需要开启SSL
//    /**
//     使用SSL加密方式发送邮件 在使用QQ或Gmail邮箱时，需要强制开启SSL支持
//     **/
//
//        MailUtil.send(account, CollUtil.newArrayList("xxxxxxxxxx@qq.com"), //接收人邮箱
//            "测试主题", "TEST", false);

//    MailAccount account = new MailAccount();
//     account.setHost("smtp.qq.com");
//     account.setPort(587);
//     account.setAuth(true);
//account.setFrom(email);
//account.setUser(email);
//account.setPass(admin.qqEmailPass);
//MailUtil.send(account,email,"三战后台登录短信码",code,false);
//

}

