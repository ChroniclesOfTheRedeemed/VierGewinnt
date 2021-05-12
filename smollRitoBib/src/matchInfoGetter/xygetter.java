/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matchInfoGetter;

import matchInfoGetter.infogetter;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.Participant;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */

public class xygetter implements infogetter{

    final Map<Integer, String> championIdToNameMapping;
    final Map<String, Integer> playedChampions;
    Map<String, data> winsLosses;
    final Summoner y;
    Match matchExample;

    public xygetter(Summoner x) {
        y = x;
        championIdToNameMapping = new HashMap<>();
        for (final Champion champion : Champions.withRegion(Region.EUROPE_WEST).get()) {
            championIdToNameMapping.put(champion.getId(), champion.getName());
        }
        playedChampions = new HashMap<>();
        winsLosses = new HashMap<>();
    }

    @Override
    public int getAmountofMatches() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSummonerName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class data {

        Integer losses = 0;
        Integer wins = 0;
    }
    int countet = 0;

    @Override
    public void getInfoFromMatch(Match match) {
        System.out.println("" + countet++);
        matchExample = match;
        final Integer championId = match.getParticipants().find(y).getChampion().getId();
        
        boolean won = match.getParticipants().find(y).getTeam().isWinner();
        
        final String championName = championIdToNameMapping.get(championId);
        Integer count = playedChampions.get(championName);
        if (count == null) {
            count = 0;
            playedChampions.put(championName, count);
        }
        playedChampions.put(championName, playedChampions.get(championName) + 1);
        
        
        data ccount = winsLosses.get(championName);
        if (ccount == null) {
            ccount =  new data();
            winsLosses.put(championName, ccount);
        }
        else if(won){
            ccount.wins++;
        } else {
            ccount.losses++;
        }
        playedChampions.put(championName, playedChampions.get(championName) + 1);
        
    }
    
    @Override
    public void getConclusion(){
        
        // Print the top ten aggregated champion results
        final List<Map.Entry<String, data>> entries = new ArrayList<>(winsLosses.entrySet());
        entries.sort((final Map.Entry<String, data> e0, final Map.Entry<String, data> e1) -> Integer.compare(e1.getValue().wins + e1.getValue().losses, e0.getValue().wins + e1.getValue().losses));

        for(int i = 0; i < 10 && i < entries.size(); i++) {
            final String championName = entries.get(i).getKey();
            final int count = entries.get(i).getValue().wins;
            System.out.println(championName + " " + count + " wins " + entries.get(i).getValue().losses + " losses");
        }

        System.out.println("\n");

        final Match match = matchExample;
        System.out.println("Match ID: " + match.getId());

        final Participant participant = match.getParticipants().find(y);
        System.out.println("\nSince the match was created from a matchref, we only know one participant:");
        System.out.println(participant.getSummoner().getName() + " played " + participant.getChampion().getName());

        System.out.println("\nNow pull the full match data by iterating over all the participants:");
        for(final Participant p : match.getParticipants()) {
            System.out.println(p.getSummoner().getName() + " played " + p.getChampion().getName());
        }

        System.out.println("\nIterate over all the participants again and note that the data is not repulled:");
        for(final Participant p : match.getParticipants()) {
            System.out.println(p.getSummoner().getName() + " played " + p.getChampion().getName());
        }

        System.out.println("\nBlue team won? " + match.getBlueTeam().isWinner());
        System.out.println("Red team won? " + match.getRedTeam().isWinner());
        System.out.println("Participants on blue team:");
        for(final Participant p : match.getBlueTeam().getParticipants()) {
            System.out.println(p.getSummoner().getName());
        }
    }

}