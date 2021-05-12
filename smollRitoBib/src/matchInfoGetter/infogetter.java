/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matchInfoGetter;

import com.merakianalytics.orianna.types.core.match.Match;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */

public interface infogetter {

    public void getInfoFromMatch(Match match);
    
    public void getConclusion();

    public int getAmountofMatches();
    
    public String getSummonerName();
}


