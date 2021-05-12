/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matchInfoGetter;

import com.merakianalytics.orianna.Orianna;
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

public class playTimeAccumulator implements infogetter{

    
    protected final int numOfMatches;
    protected final String summonerName;
    
    public playTimeAccumulator(String summoner, int numOfMatches) {
        this.summonerName = summoner;
        this.numOfMatches = numOfMatches;
    }

    @Override
    public int getAmountofMatches() {
        return numOfMatches;
    }

    @Override
    public String getSummonerName() {
        return summonerName;
    }
    int countet = 0;

    long secondsplayed = 0;
    @Override
    public void getInfoFromMatch(Match match) {
        System.out.println("" + countet++);
        
        secondsplayed += match.getDuration().getStandardSeconds();
        
    }
    
    @Override
    public void getConclusion(){
        System.out.println("palyed = " + secondsplayed);
        System.out.println("average = " + secondsplayed / countet);
    }
}