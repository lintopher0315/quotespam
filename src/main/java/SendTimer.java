import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class SendTimer extends TimerTask {

    String list;

    public SendTimer(String list) {
        this.list = list;
    }

    public void run() {
        try {
            String q;
            Document doc = Jsoup.connect("http://quotes.toscrape.com/random").get();
            Elements quotes = doc.getElementsByClass("text");
            Elements author = doc.getElementsByClass("author");
            q = "<font size=5><b>\"" + quotes.get(0).text().substring(1, quotes.get(0).text().length() - 1) + "\"" + "<br/>-" + author.get(0).text() + "</b></font><br/><br/>"
                    + "<font size=1>Hello World! This bot serves to email recipients random inspirational quotes every twenty minutes.<br/>Stay happy and stay in school! :]<br/> Created entirely by Christopher James Lin.</font>";

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Main mail = new Main();
            mail.sendMail("Quote of the 1/3 Hour | " + formatter.format(new Date()), q, "mr.botmcbotstein@gmail.com", list);
            System.out.println("Email sent successfully");
        }
        catch (Exception e) {
            System.out.println("Email failed to send");
            e.printStackTrace();
        }
    }
}