/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testsheets.ori;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.spectator.CurrentMatch;
import com.merakianalytics.orianna.types.core.spectator.FeaturedMatch;
import com.merakianalytics.orianna.types.core.spectator.FeaturedMatches;
import com.merakianalytics.orianna.types.core.spectator.Player;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import statics.statics;


public class GetCurrentMatches {
    public static void main(final String[] args) {
        Orianna.setRiotAPIKey(statics.RGBK);
        final FeaturedMatches featured = FeaturedMatches.withRegion(Region.NORTH_AMERICA).get();
        for(final FeaturedMatch game : featured) {
            System.out.println(game.getRegion() + " " + game.getId());
        }

        final FeaturedMatch game = featured.get(0);
        final String aSummonerName = game.getParticipants().get(0).getSummoner().getName();
        final Summoner summoner = Summoner.named(aSummonerName).withRegion(game.getRegion()).get();
        final CurrentMatch currentGame = summoner.getCurrentMatch();
        System.out.println(currentGame.getMap());

        for(final Player player : currentGame.getParticipants()) {
            System.out.println(player.getSummoner().getName());
        }
    }
}