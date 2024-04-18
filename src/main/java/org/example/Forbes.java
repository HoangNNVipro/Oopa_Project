package org.example;

//Cần import những thư viện này của Joup
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//Cần import những thư viện này của gson
import com.google.gson.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import com.google.gson.Gson;

public class Forbes extends BaiViet {
    public Forbes(String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            //Link bài
            this.setLink(url);

            //Nguồn bài viết
            this.setNguon("https://www.forbes.com");

            //Tiêu đề
            String td= doc.select("h1").text();
            this.setTieuDe(td);

            //Chuyên mục
            String cmuc = doc.select("a.y-rKzEDT").text();
            this.setChuyenMuc(cmuc);

            //Tác giả
            String tg = doc.select("a.contrib-link--name.remove-underline.author-name--tracking.not-premium-contrib-link--name").text();
            this.setTacGia(tg);

            //Thời gian
            Element timeElement = doc.select("time").first();
            String tGian = timeElement.text();
            this.setThoigian(tGian);

            //Tags
            this.setTags("Không có");


            //Nội dung bài viết
            String noidung = "";
            Elements noidungbv = doc.select("p");
            for (Element nd : noidungbv) {
                String ndText = nd.text();
                noidung = noidung + ndText + " ";
            }
            this.setNoidung(noidung);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
