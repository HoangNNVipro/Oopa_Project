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

public class Blockchain extends BaiViet {
    public Blockchain(String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            //Link bài
            this.setLink(url);

            //Nguồn bài viết
            this.setNguon("https://blockchain.news");

            //Tiêu đề
            String td= doc.select("h1").text();
            this.setTieuDe(td);

            //Chuyên mục
            this.setChuyenMuc("News");

            //Tác giả
            String tg = doc.select("h4 > a").text();
            this.setTacGia(tg);

            //Thời gian
            String tGian = doc.select("ul.list-inline.list-unstyled li.list-inline-item.d-lg-block.my-lg-2").text();
            this.setThoigian(tGian);

            //Tags
            Elements tagsElement = doc.select("a.btn.btn-sm.btn-primary-soft");
            String tags1 = "";
            for (Element tag : tagsElement) {
                String tagsText = tag.text();
                tags1 = tags1 + tagsText + "   ";
            }
            this.setTags(tags1);


            //Nội dung bài viết
            String noidung;

            String noidung1 = doc.select("p.lead").text();
            noidung = noidung1 + " ";

            Elements noidung2 = doc.select("p > span");
            for (Element nd2 : noidung2) {
                String nd2Text = nd2.text();
                noidung = noidung + nd2Text + " ";
            }
            this.setNoidung(noidung);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}