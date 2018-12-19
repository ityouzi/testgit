package cn.wx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.util.xml.XStreamTransformer;

/**
 * <pre>
 * Demo Servlet
 * 如果使用的是SpringMVC，也是类似这样的写法~
 * 
 * 注意：WxConfig请调用getInstance()
 * 因为对于全局是唯一的。采用单例模式。
 * </pre>
 * 
 * @author antgan
 * @date 2016/12/15
 *
 */
//@WebServlet("/wx")
public class DemoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // 实例化 统一业务API入口
    private IService iService = new WxService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 验证服务器的有效性
        PrintWriter out = response.getWriter();
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        
        System.err.println("***"+signature);
        System.err.println("***"+timestamp);
        System.err.println("***"+nonce);
        System.err.println("***"+echostr);
        
        if (iService.checkSignature(signature, timestamp, nonce, echostr)) {
            out.print(echostr);
        }
    }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
      //消息来源可靠性验证
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");       // 随机数
        String echostr = request.getParameter("echostr");
        
        System.err.println("***"+signature);
        System.err.println("***"+timestamp);
        System.err.println("***"+nonce);
        System.err.println("***"+echostr);
        
        // 返回消息给微信服务器
        PrintWriter out = response.getWriter();

        // 创建一个路由器
        WxMessageRouter router = new WxMessageRouter(iService);
        try {
            // 微信服务器推送过来的是XML格式。
            WxXmlMessage wx = XStreamTransformer.fromXml(WxXmlMessage.class, request.getInputStream());
            System.out.println("消息：\n " + wx.toString());
            // 添加规则；这里的规则是所有消息都交给DemoMatcher处理，交给DemoInterceptor处理，交给DemoHandler处理
            // 注意！！每一个规则，必须由end()或者next()结束。不然不会生效。
            // end()是指消息进入该规则后不再进入其他规则。 而next()是指消息进入了一个规则后，如果满足其他规则也能进入，处理。
//            router.rule().matcher(new DemoMatcher()).interceptor(new DemoInterceptor()).handler(new DemoHandler())
//                    .end();
            // 把消息传递给路由器进行处理
        	
            WxXmlOutMessage xmlOutMsg = router.route(wx);
            if (xmlOutMsg != null){
                out.print(xmlOutMsg.toXml());// 因为是明文，所以不用加密，直接返回给用户。
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
    

    
}
    	