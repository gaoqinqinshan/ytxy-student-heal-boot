
import org.springframework.boot.test.context.SpringBootTest;

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
}
