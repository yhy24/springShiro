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

/**
 * 生产随机验证码
 */
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

 // sn  sn时戳,可以避免浏览器缓存验证码

    @RequestMapping(value = "/rand/{sn}",method = RequestMethod.GET)
    public void getCode(@PathVariable("sn") String sn, HttpServletRequest request,
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
    public String test() {
        return null;
    }
    /*public static String markImageByIconType(String codeType, String provCode, String imgStr, Integer degree ,String picExt, int width, int height) {
        String  imgString=null;
        String iconPath = null;
        try {
            Image srcImg = ImageIO.read(IOUtils.toInputStream(imgStr, "ISO8859-1"));
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // 1、得到画笔对象
            Graphics2D g = buffImg.createGraphics();

            // 2、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
            // 3、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree),(double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
            }
            //获取水印路径
            iconPath = BsStaticDataUtil.getCodeValue(codeType, provCode,"JVM");
            if(iconPath==null||iconPath.trim().length()==0){
                iconPath = BsStaticDataUtil.getCodeValue(codeType, "COMMON","JVM");
            }
            // 4、水印图片的路径 水印图片一般为gif或者png的，这样可设置透明度
            ImageIcon imgIcon = new ImageIcon(iconPath);

            // 5、得到Image对象。
            Image img = imgIcon.getImage();

            //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));

            // 6、水印图片的位置
            g.drawImage(img, width, height, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            // 7、释放资源
            g.dispose();

            //8、将BufferedImage对象buffImg转换为字符串
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(buffImg, picExt ,imOut);
            InputStream is= new ByteArrayInputStream(bs.toByteArray());
            imgString = IOUtils.toString(is, "ISO8859-1");
            if (null != is) {
                is.close();
            }
            if (null != imOut) {
                imOut.close();
            }

            if (null != bs) {
                bs.close();
            }
        } catch (IOException e) {

            log.error("",e);
        } catch (Exception e) {

            log.error("",e);
        }
        return imgString;
    }*/

}
