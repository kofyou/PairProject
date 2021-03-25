package com.pairproject.papercv.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pairproject.papercv.model.Paper;
import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//create table paper (
//    title varchar(512) primary key ,
//    paperAbstract varchar(2048),
//    url varchar(1024),
//    meeting varchar(16),
//    year varchar(16),
//    keyWord varchar(1024)
//    )

/**
 * 文件读取
 *
 * @date 2021/03/23
 */
public class FileUtil {
    private static final Charset ENCODING = StandardCharsets.UTF_8;
    private static final String CVPR_ROOT = "221801334&221801230/PaperCV/src/main/java/com/pairproject/papercv/data" +
        "/paper/CVPR/";
    private static final String ECCV_ROOT = "221801334&221801230/PaperCV/src/main/java/com/pairproject/papercv/data/paper/ECCV/";
    private static final String ICCV_ROOT = "221801334&221801230/PaperCV/src/main/java/com/pairproject/papercv/data" +
        "/paper/ICCV/";

    /**
     * 获取ECCV
     *
     * @return
     */
    public static List<Paper> readECCV() {
        File root = new File(ECCV_ROOT);
        File[] files = root.listFiles();
        List<Paper> papers = new ArrayList<>(4096);
        for (File file : files) {
            JSONObject jsonObject = JSON.parseObject(readMMAP(file));
            Paper paper = new Paper();
            paper.setTitle(jsonObject.getString("论文名称"));
            paper.setPaperAbstract(jsonObject.getString("摘要"));
            paper.setUrl(jsonObject.getString("原文链接"));
            paper.setMeeting("ECCV");
            paper.setYear(jsonObject.getString("会议和年份").split(" ")[1]);
            JSONArray keys = jsonObject.getJSONArray("关键词");
            StringBuilder sb = new StringBuilder();
            for (Object key : keys) {
                sb.append(key).append(",");
            }
            paper.setKeyWord(sb.toString());
            papers.add(paper);
        }
        return papers;
    }

    /**
     * 获取ICCV论文
     *
     * @return
     */
    public static List<Paper> readICCV() {
        File root = new File(ICCV_ROOT);
        File[] files = root.listFiles();
        List<Paper> papers = new ArrayList<>(4096);
        for (File file : files) {
            String ori = readMMAP(file);
            JSONObject jsonObject = JSON.parseObject(ori.substring(0, ori.length()-1));
            Paper paper = new Paper();
            paper.setTitle(jsonObject.getString("title"));
            paper.setPaperAbstract(jsonObject.getString("abstract"));
            paper.setUrl(jsonObject.getString("doiLink"));
            paper.setMeeting("ICCV");
            paper.setYear(jsonObject.getString("publicationYear"));
            JSONArray keys = jsonObject.getJSONArray("keywords").getJSONObject(0).getJSONArray("kwd");
            StringBuilder sb = new StringBuilder();
            for (Object key : keys) {
                sb.append(key).append(",");
            }
            paper.setKeyWord(sb.toString());
            papers.add(paper);
        }
        return papers;
    }

    /**
     * 获取CVPR论文
     *
     * @return
     */
    public static List<Paper> readCVPR() {
        File root = new File(CVPR_ROOT);
        File[] files = root.listFiles();
        List<Paper> papers = new ArrayList<>(7000);
        for (File file : files) {
            String ori = readMMAP(file);
            JSONObject jsonObject = JSON.parseObject(ori.substring(0, ori.length()-1));
            Paper paper = new Paper();
            paper.setTitle(jsonObject.getString("title"));
            paper.setPaperAbstract(jsonObject.getString("abstract"));
            paper.setUrl(jsonObject.getString("doiLink"));
            paper.setMeeting("CVPR");
            paper.setYear(jsonObject.getString("publicationYear"));
            JSONArray keys = jsonObject.getJSONArray("keywords").getJSONObject(0).getJSONArray("kwd");
            StringBuilder sb = new StringBuilder();
            for (Object key : keys) {
                sb.append(key).append(",");
            }
            paper.setKeyWord(sb.toString());
            papers.add(paper);
        }
        return papers;
    }


    /**
     * 文件读入，使用mmap
     *
     * @param file 输入文件
     * @return 文件内容，如果空则为""
     */
    public static String readMMAP(File file){
        RandomAccessFile raf = null;
        MappedByteBuffer mbb = null;
        try {
            raf = new RandomAccessFile(file, "r");
            mbb = raf.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            if (mbb != null){
                return ENCODING.decode(mbb).toString();
            } else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (mbb != null){
                Cleaner var1 = ((DirectBuffer)mbb).cleaner();
                if (var1 != null) {
                    var1.clean();
                }
            }
            if (raf != null){
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}
