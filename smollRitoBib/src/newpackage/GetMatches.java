/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newpackage;


import matchInfoGetter.xgetter;
import matchInfoGetter.infogetter;
import testsheets.ori.*;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.Participant;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import matchInfoGetter.playTimeAccumulator;
import statics.statics;

public class GetMatches {
    public static void main(final String[] args) {
        Orianna.setRiotAPIKey(statics.RGBK);
        GetMatches n = new GetMatches();
        n.doTheThing(new playTimeAccumulator("Frostbite", 0));
    }
    
    
    public void doTheThing(infogetter inf){
        // Notice how this object never requires a call to the summoner endpoint because we provide all the needed data!
        final Summoner summoner = Summoner.named(inf.getSummonerName()).withRegion(Region.EUROPE_WEST).get();

        // A MatchHistory is a lazy list, meaning it's elements only get loaded as-needed.

        final MatchHistory matchHistory = MatchHistory.forSummoner(summoner).get();
        // MatchHistory match_history = MatchHistory.forSummoner(summoner).withQueues([Queue.RANKED_SOLO_5x5]).withSeasons([Season.SEASON_7]).get();

        // Load the entire match history by iterating over all its elements so that we know how long it is.
        // Unfortunately since we are iterating over the match history and accessing the summoner's champion for each match,
        // we need to know what version of the static data the champion should have. To avoid pulling many different
        // static data versions, we will instead create a {champion_id -> champion_name} mapping and just access the champion's
        // ID from the match data (which it provides directly).
        final Map<Integer, String> championIdToNameMapping = new HashMap<>();
        for(final Champion champion : Champions.withRegion(Region.EUROPE_WEST).get()) {
            championIdToNameMapping.put(champion.getId(), champion.getName());
        }
        final Map<String, Integer> playedChampions = new HashMap<>();
        
        System.out.println("start");
        int counter = 0;
        for(final Match match : matchHistory) {
            if(counter++ > inf.getAmountofMatches()) break;
            inf.getInfoFromMatch(match);
        }
       // System.out.println("Length of match history: " + matchHistory.size());

        inf.getConclusion();
    }

}
