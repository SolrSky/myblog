package com.mtrhz.controller.kaptcha;

import com.google.code.kaptcha.Producer;
import com.mtrhz.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * @author Solrsky
 * @date 2019/1/8
 */
@Controller
public class KaptchaController {

    private static final Logger logger = LoggerFactory.getLogger(KaptchaController.class);

    @Autowired
    private Producer kaptchatProducer;

    @RequestMapping(value = "/kaptcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        // 生成验证码
        String text = kaptchatProducer.createText();
        // 保存验证码
        HttpSession session = request.getSession();
        session.setAttribute(Constant.KAPTCHA_KEY, text);
        //向客户端写出
        BufferedImage bi = kaptchatProducer.createImage(text);
        ServletOutputStream out = response.getOutputStream();
        try {
            ImageIO.write(bi, "jpg", out);
            out.flush();
        }finally {
            out.close();
        }

    }
}
