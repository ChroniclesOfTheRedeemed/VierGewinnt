/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matchInfoGetter;

import matchInfoGetter.infogetter;
import com.merakianalytics.orianna.types.core.match.Match;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */


public class xgetter implements infogetter{

    @Override
    public void getInfoFromMatch(Match match) {
        match.getTimeline().forEach((f) -> {
            f.forEach((d) -> {
                System.out.println("" + d.toJSON());
            });
        });
    }

    @Override
    public void getConclusion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getAmountofMatches() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSummonerName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}