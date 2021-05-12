/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.Match.Builder;
import java.util.Scanner;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public class mymain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("your Key");
        String key = in.next();
        System.out.println("gameID");
        long gameId = in.nextLong();
        Orianna.setRiotAPIKey(key);
        Orianna.setDefaultRegion(Region.EUROPE_WEST);

        while (gameId != 0) {
            Match w = Orianna.matchWithId(gameId).get();
            System.out.println(w.getCreationTime());

            System.out.println(w.getMode());
            System.out.println(w.getQueue());
            w.getParticipants().forEach((P) -> {
                System.out.print(P.getSummoner().getName());
                System.out.print(" \ton " + P.getChampion().getName());
                System.out.println("");

            });
            gameId = in.nextLong();
        }
    }
}
