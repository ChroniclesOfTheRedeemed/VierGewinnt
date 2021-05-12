/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discordsimpletokencounter;

import storage.old.stuffWriter;
import static discordsimpletokencounter.DiscordChatExporterConstants.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Uses DiscordChatExporter as basis for text interpretation
 *
 * @author Mike
 */
public class DiscordSimpleTokenCounter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        // TODO code application logic here
        stuffWriter d = new stuffWriter();
        DiscordSimpleTokenCounter s = new DiscordSimpleTokenCounter("uwu", "C:\\Users\\Mike\\Desktop\\omegalul\\here\\Ahri Mains - general-discussion [197789017700433930] (after 2019-11-01).txt");
        s.run();
    }

    private final String token; //is required to be lowercase in this case.
    private final genericReader f;

    public DiscordSimpleTokenCounter(String token, String dcTextFilePath) throws FileNotFoundException {
        this.token = token;
        f = new genericReader(dcTextFilePath);
    }

    public void run() throws FileNotFoundException, IOException, ParseException {
        //iterateFileForMessages
        while (f.isReady()) {
            confirmMessage(f.nextLine());
        }
        brushWeek();
        brush();
        new LineChart_AWT(brushWeek());
        
    }
    SimpleDateFormat ads = new SimpleDateFormat("yyyy-MM-dd");
    
    private ArrayList<data> brush() throws ParseException{
        ArrayList<data> newList = new ArrayList<>();
        int uwuPerIntervall = 0;
        String timeIntervallStart = ads.format(dataList.get(0).date);
        String newTime;
        for(data f : dataList){
            newTime = ads.format(f.date);
            if(newTime.equals(timeIntervallStart)){
                uwuPerIntervall += f.count;
            } else {
                newList.add(new data("", uwuPerIntervall, ads.parse(timeIntervallStart)));
                timeIntervallStart = newTime;
                uwuPerIntervall = f.count;
            }
        }
        
        
        int totalOwos = 0;
        
        for(data f : newList){
            System.out.println(ads.format(f.date) + " " + f.count);
            totalOwos += f.count;
        }
        System.out.println("totalUwus:" +  totalOwos);
        return newList;
    }

    SimpleDateFormat adsu = new SimpleDateFormat("w");
    private ArrayList<data> brushWeek() throws ParseException {
        ArrayList<data> newList = new ArrayList<>();
        int totalUwUTime = 0;
        String thisTime = adsu.format(dataList.get(0).date);
        String newTime;
        for (data f : dataList) {
            newTime = adsu.format(f.date);
            if (newTime.equals(thisTime)) {
                totalUwUTime += f.count;
            } else {
                newList.add(new data("", totalUwUTime, adsu.parse(thisTime)));
                thisTime = newTime;
                totalUwUTime = f.count;
            }
        }

        int totalOwos = 0;
        for (data f : newList) {
            totalOwos += f.count;
            System.out.println(adsu.format(f.date) + " " + f.count);
        }
        System.out.println("totalUwus:" +  totalOwos);
        return newList;
    }

    //global Variables deprecated??
    private String senderName = "";
    private Date dateOfSend = new Date();
    private int uwuCountPerse = 0;
private ArrayList<data> dataList = new ArrayList<>();
    
    private void storeStuff() {
        if (uwuCountPerse > 0) {
            dataList.add(new data(senderName, uwuCountPerse, dateOfSend));
            //System.out.println(senderName + " uwu'd " + uwuCountPerse + " times at " + dateOfSend.toString());
            for(String f : uwuMessages){
                //System.out.println(f);
            }
            uwuMessages.clear();
           // System.out.println("");
        }
    }

    ArrayList<String> uwuMessages = new ArrayList<>();
    private void updateGlobals(String senderName, Date dateOfSend) {
        this.dateOfSend = dateOfSend;
        this.senderName = senderName;
        uwuCountPerse = 0;
    }

    public void confirmMessage(String message) throws IOException {
        //tryToConfirmIfNewMessage
        try {
            if (message.startsWith("" + MessageStartIncludingDate)) {
                int start = (MessageStartIncludingDate + "").length();
                int end = message.indexOf(MessageEndDate);
                String eventualDate = message.substring(start, end);
                Date messageDate = UsedDateFormat.parse(eventualDate);
                start = (MessageStartIncludingDate + "").length() + eventualDate.length() + (MessageEndDate + "").length();
                end = message.length() - 1;
                String eventualName = message.substring(start, end);

                //save prior globals HERE !!!
                storeStuff();
                updateGlobals(eventualName, messageDate);
                return;
            }
        } catch (Exception ex) {
//            System.err.println(message);
//            Logger.getLogger(DiscordSimpleTokenCounter.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (message.startsWith(reactions)) {
            String reaction = f.nextLine();
            //System.out.println("-----Reactions: " + reaction);
            //do reactions
        } else if (message.startsWith(embed)) {
            //do embed
            String embed = f.nextLine();
            //System.out.println("-----Embed: " + embed);
        } else if (message.startsWith(attachments)) {
            //do attachments
            String attachment = f.nextLine();
            //System.out.println("-----Attachment: " + attachment);
            
        } else {
            //count tokens
            //exclude words containing, emotes, urls. (fuck urls tho)
            //only counts direct uwus, no ùwú, u w u, xuwux etc 
            //System.out.println("Message: " + message);
            Scanner scanner = new Scanner(message);
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();//lowercase sensitive
                //System.out.println("analyse " + word);
                while (true) {
                    if(word.startsWith("@") || word.startsWith("http")){
                        //System.out.println("don't care");
                        break;
                    }
                    int uwuPos = word.indexOf(token);

                    if (uwuPos == -1) {
                        break;
                    }
                    //System.out.println("uwu found");
                    //weise vor und nachfolger char zu ohne fehler (falls am ende bzw anfang steht)
                    int beforeCharPos = uwuPos - 1;
                    int afterCharPos = uwuPos + token.length();
                    char beforeChar;
                    char afterChar;
                    
                    if (beforeCharPos == -1) {
                        beforeChar = ' ';
                    } else {
                        beforeChar = word.charAt(beforeCharPos);
                    }

                    if (afterCharPos >= word.length()) {
                        afterChar = ' ';
                    } else {
                        afterChar = word.charAt(afterCharPos);
                    }
                    //done
                    //Now Check FOr UWUs:
                    //you know what to do I beruf in u uwu'
                    if (charWorks(afterChar) && charWorks(beforeChar)) {
                        //System.out.println("no uwu");
                    } else {
                        uwuMessages.add(message);
                        uwuCountPerse++;
                        //System.out.println("yes uwu");
                    }
                    word = word.substring(afterCharPos-1);
                }
            }
        }
    }

    private boolean charWorks(char d) {
        return (Character.isLetter(d) || d == ':' || d == '"');
    }
}

class genericReader {

    final FileReader reader;

    public genericReader(String dcTextFilePath) throws FileNotFoundException {
        reader = new FileReader(dcTextFilePath);
    }

    public boolean isReady() throws IOException {
        return reader.ready();
    }

    public String nextLine() throws IOException {
        String message = "";
        while (reader.ready()) {
            char newChar = (char) reader.read();
            //System.out.print(newChar +"");
            if (newChar == '\n') {
                break;
            } else {
                message += newChar;
            }
        }
        return message;
    }
}
//also ignore links 

class DiscordChatExporterConstants {

    static final SimpleDateFormat UsedDateFormat = new SimpleDateFormat("dd-MMM-yy h:m a", Locale.ENGLISH);

    static final char InputSeperator = '\n';
    static final char MessageStartIncludingDate = '[';
    static final char MessageEndDate = ']';
    static final char DateMessagerSenderSeperator = ' ';
    static final String reactions = "{Reactions}";
    static final String embed = "{Embed}";
    static final String attachments = "{Attachments}";
    //name of Sender is directly after date with space
    //message of sender starts 

}

