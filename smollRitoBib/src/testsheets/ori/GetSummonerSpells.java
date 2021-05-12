/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testsheets.ori;


import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpells;
import statics.statics;

public class GetSummonerSpells {
    public static void main(final String[] args) {
        Orianna.setRiotAPIKey(statics.RGBK);
        final SummonerSpells summonerSpells = SummonerSpells.withRegion(Region.NORTH_AMERICA).get();
        for(final SummonerSpell summonerSpell : summonerSpells) {
            System.out.println(summonerSpell.getName() + " " + summonerSpell.getId());
        }
    }
}