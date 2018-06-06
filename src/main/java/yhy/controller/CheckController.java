package yhy.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class CheckController {
    private static Logger logger = Logger.getLogger(CheckController.class);

    private int width = 90;//验证码宽度
    private int height = 40;//验证码高度
    private int codeCount = 4;//验证码个数
    private int lineCount = 19;//干扰线个数

    char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    /**
     * 具体获取验证码的方法
     * @param time  time为时戳,这样的话可以避免浏览器缓存验证码
     * @throws IOException
     */
    @RequestMapping(value = "/code/{time}",method = RequestMethod.GET)
    public void getCode(@PathVariable("time") String time, HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
//定义随机数
        Random r = new Random();
        StringBuilder builderCode = new StringBuilder();
//        定义画布
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
//        得到画笔
        Graphics g = bufferedImage.getGraphics();
//        设置颜色花边框
        g.setColor(Color.BLACK);
        g.drawRect(10,10,width,height);
//        设置颜色填充内部
        g.setColor(Color.white);
        g.fillRect(1,1,width-2,height-2);
//        设置干扰线的颜色
        g.setColor(Color.green);
        for (int i =0;i < lineCount;i++) {
            g.drawLine(r.nextInt(width),r.nextInt(width),r.nextInt(width),r.nextInt(width));
        }
//        设置验证码和字体的颜色
        g.setColor(Color.red);
        g.setFont(new Font("宋体",Font.BOLD|Font.ITALIC,15));
        for (int i = 0;i<codeCount;i++) {
            char c = codeSequence[r.nextInt(codeSequence.length)];
            builderCode.append(c);
            g.drawString(c+"",15*(i+1),15);
        }
//        输出到屏幕
        ServletOutputStream out =response.getOutputStream();
        ImageIO.write(bufferedImage,"png",out);
//      保存在session
        HttpSession session = request.getSession();
        session.setAttribute("codeValidate",builderCode.toString());
//        禁止缓存图片
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        //8.关闭sos
        out.close();
    }
}
