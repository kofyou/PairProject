package com.company.servlet;

import com.alibaba.fastjson.JSONObject;
import com.company.bean.Paper;
import com.company.util.AutoNewsCrawler;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(value="/download",asyncSupported = true)
public class CrawlerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqString = req.getReader().readLine();
        JSONObject reqJson = JSONObject.parseObject(reqString);
        String s = null;
        String meeting = (String) reqJson.get("meeting");
        String pwd = (String) reqJson.get("pwd");
        int count = (int) reqJson.get("count");
        int year = (int) reqJson.get("year");

        if(pwd.equals("123456"))
        try {
            s = JSONObject.toJSONString(AutoNewsCrawler.run(year, meeting, count));
            String filename = "paper.txt";
            resp.setHeader("content-disposition", "attachment;filename=" + filename);
            resp.setContentType("application/octet-stream");
            FileOutputStream out1 = new FileOutputStream(this.getServletContext().getRealPath("static/temp/paper.txt"));
            out1.write(s.getBytes());
            FileInputStream in = new FileInputStream(this.getServletContext().getRealPath("static/temp/paper.txt"));

            int len = 0;
            byte buffer[] = new byte[1024];
            OutputStream out = resp.getOutputStream();
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
                //System.out.println(len);
            }
            in.close();
        } catch (Exception e) {
            System.out.println("end...");
            e.printStackTrace();
        }
    }
}
