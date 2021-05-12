/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testsheets.ori;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.staticdata.ReforgedRune;
import com.merakianalytics.orianna.types.core.staticdata.ReforgedRunes;
import statics.statics;

public class GetRunes {
    public static void main(final String[] args) {
        Orianna.setRiotAPIKey(statics.RGBK);
        final ReforgedRunes runes = ReforgedRunes.withRegion(Region.NORTH_AMERICA).get();
        for(final ReforgedRune rune : runes) {
            System.out.println(rune.getName() + " " + rune.getId());
        }
    }
}