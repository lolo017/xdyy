package aisino.reportform.action.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
@Namespace("/base")
@Action
public class getVerifyAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private static final long serialVersionUID = 1L;

    private HttpServletRequest request;
    private HttpServletResponse response;

  @Override
public void setServletResponse(HttpServletResponse response) {
   // TODO Auto-generated method stub
     this.response = response;
  }

  @Override
public void setServletRequest(HttpServletRequest request) {
    // TODO Auto-generated method stub
       this.request = request;
     }

  public String doNotNeedSecurity_getVerifyImpage() throws IOException {

     response.setContentType("image/jpeg");
     response.setHeader("Pragma", "No-cache");
     response.setHeader("Cache-Control", "no-cache");
     response.setDateHeader("Expires", 0);
     HttpSession session = request.getSession();

     session.removeAttribute("verifyCode");
     int width = 70, height = 23;
     BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
       Graphics g = image.getGraphics();
          // 生成随机类
       Random random = new Random();
       // 设定背景色
      g.setColor(getRandColor(200, 250));
      g.fillRect(0, 0, width, height);
      // 设定字体
      g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
      // 画边框
      g.setColor(getRandColor(160, 200));
      g.drawRect(0, 0, width - 1, height - 1);
      // 随机产生100条干扰线
      g.setColor(getRandColor(160, 200));
      for (int i = 0; i < 100; i++) {
          int x = random.nextInt(width);
          int y = random.nextInt(height);
          int xl = random.nextInt(10);
          int yl = random.nextInt(10);
          g.drawLine(x, y, x + xl, y + yl);
      }
      // 取随机产生4位数字
      String sRand = "";
      for (int i = 0; i < 4; i++) {
          String rand = String.valueOf(random.nextInt(10));
          sRand += rand;
     
          g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));

          g.drawString(rand, 13 * i + 14, 20);
      }
      // 将认证码存入SESSION
      session.setAttribute("verifyCode", sRand);
      // 图象生效
      g.dispose();
      // 输出图象到页面
      ImageIO.write(image, "JPEG", response.getOutputStream());
      return null;
  }
  private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
      Random random = new Random();
      if (fc > 255)
          fc = 255;
      if (bc > 255)
          bc = 255;
      int r = fc + random.nextInt(bc - fc);
      int g = fc + random.nextInt(bc - fc);
      int b = fc + random.nextInt(bc - fc);
      return new Color(r, g, b);
  }
}
