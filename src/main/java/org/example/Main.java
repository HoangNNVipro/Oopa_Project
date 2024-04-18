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




public class Main {

    public static int check(String url) {
        try {
            FileReader fileReader = new FileReader("Stored_Link.json");
            JsonParser jsonParser = new JsonParser();
            JsonArray dataArray = (JsonArray) jsonParser.parse(fileReader);
            int Size = dataArray.size();
            int xn = 0;
            for (int i = 0; i < Size; i++) {
                String s = dataArray.get(i).toString();
                if (s.indexOf(url) >= 0) {
                    xn = 1;
                }
            }

            if (xn == 1) {
                return 1;
            } else {
                JsonObject newObject = new JsonObject();
                newObject.addProperty("Link", url);
                dataArray.add(newObject);
                int Sizemoi = dataArray.size();

                FileWriter fw = new FileWriter("Stored_Link.json");
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write("[");
                bw.newLine();
                for (int i = 0; i < Sizemoi - 1; i++) {
                    String o = dataArray.get(i).toString();
                    bw.write(o);
                    bw.write(",");
                    bw.newLine();
                }
                String o = dataArray.get(Sizemoi - 1).toString();
                bw.write(o);
                bw.newLine();
                bw.write("]");
                bw.close();
                fw.close();
                return 0;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public static void main(String[] args) {

        String url = "https://blockchain.news/news/samsung-secures-64-billion-in-us-government-grants-for-chip-manufacturing-expansion-in-texas";

        //Dùng hàm check kiểm tra xem url đã có trong Stored_Link chưa. Đã có thì trả về 1. Chưa có thì cập nhật url vào Stored_Link và trả về 0
        if ((check(url))==1) System.out.println("Thông tin bài viết đã có trong cơ sở dữ liệu từ trước");
        else
            //xác định url có trong nguồn nào
            if (url.indexOf("blockchain") >= 0){

                //Tạo đối tượng từ nguồn xác định được
                Blockchain t = new Blockchain(url);

                //Lưu thông tin đối tượng vào Stored_File
                t.saveToStoredFile();

                //Thông bào trạng thái
                System.out.println("Cập nhật thông tin bài viết thành công");

            } else if (url.indexOf("forbes") >= 0){
                Forbes t = new Forbes(url);
                t.saveToStoredFile();
                System.out.println("Cập nhật thông tin bài viết thành công");
            }
    }
}