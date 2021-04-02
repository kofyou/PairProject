package com.yrc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService { //邮件任务


    @Autowired
    private MailService mailService;

    @Resource
    private TemplateEngine templateEngine;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

//    /**
//     * 简单文本邮件
//     * @param to 接收者邮件
//     * @param subject 邮件主题
//     * @param contnet 邮件内容
//     */
    public void sendSimpleMail(String to, String subject, String contnet){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(contnet);
        message.setFrom(from);

        mailSender.send(message);
    }

//    /**
//     * HTML 文本邮件
//     * @param to 接收者邮件
//     * @param subject 邮件主题
//     * @param contnet HTML内容
//     * @throws MessagingException
//     */
    public void sendHtmlMail(String to, String subject, String contnet) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(contnet, true);
        helper.setFrom(from);

        mailSender.send(message);
    }

//    /**
//     * 附件邮件
//     * @param to 接收者邮件
//     * @param subject 邮件主题
//     * @param contnet HTML内容
//     * @param filePath 附件路径
//     * @throws MessagingException
//     */
    public void sendAttachmentsMail(String to, String subject, String contnet,
                                    String filePath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(contnet, true);
        helper.setFrom(from);

        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = file.getFilename();
        helper.addAttachment(fileName, file);

        mailSender.send(message);
    }

//    /**
//     * 图片邮件
//     * @param to 接收者邮件
//     * @param subject 邮件主题
//     * @param contnet HTML内容
//     * @param rscPath 图片路径
//     * @param rscId 图片ID
//     * @throws MessagingException
//     */
    public void sendInlinkResourceMail(String to, String subject, String contnet,
                                       String rscPath, String rscId) {
        logger.info("发送静态邮件开始: {},{},{},{},{}", to, subject, contnet, rscPath, rscId);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        try {

            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(contnet, true);
            helper.setFrom(from);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);
            mailSender.send(message);
            logger.info("发送静态邮件成功!");

        } catch (MessagingException e) {
            logger.info("发送静态邮件失败: ", e);
        }

    }

    public void TemplateMail(String email) throws MessagingException {//发送激活邮件
        Context context = new Context();
        context.setVariable("id",email);
        String emailContent = templateEngine.process("emailTeplate", context);
        mailService.sendHtmlMail(email,"这是一封HTML模板邮件",emailContent);
    }

    public void TemplateMail2(String email) throws MessagingException {//发送激活邮件
        Context context = new Context();
        context.setVariable("id",email);
        String emailContent = templateEngine.process("emailTeplate2", context);
        mailService.sendHtmlMail(email,"这是一封HTML模板邮件",emailContent);
    }
}
