package com.carlos.blog.controller.kaptcha;

import com.carlos.blog.annotation.MyLog;
import com.carlos.blog.constant.Constant;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * @author Solrsky
 * @date 2018/12/10
 */
@Api(value = "获取验证码", tags = "1.0.0")
@RestController
public class KaptchaController {

    private static final Logger logger = LoggerFactory.getLogger(KaptchaController.class);

    @Autowired
    private Producer captchaProducer;

    @ApiOperation(value = "获取验证码")
    @RequestMapping(value = "/kaptcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        // 生成验证码
        String text = captchaProducer.createText();
        // 保存验证码
        HttpSession session = request.getSession();
        session.setAttribute(Constant.KAPTCHA_KEY, text);
        //向客户端写出
        BufferedImage bi = captchaProducer.createImage(text);
        ServletOutputStream out = response.getOutputStream();
        try {
            ImageIO.write(bi, "jpg", out);
            out.flush();
        }finally {
            out.close();
        }

    }
}
