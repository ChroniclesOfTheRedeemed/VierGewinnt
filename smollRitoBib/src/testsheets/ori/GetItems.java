/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testsheets.ori;


import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.staticdata.Items;
import statics.statics;

public class GetItems {
    public static void main(final String[] args) {
        Orianna.setRiotAPIKey(statics.RGBK);
        final Items items = Items.withRegion(Region.NORTH_AMERICA).get();
        for(final Item item : items) {
            System.out.println(item.getName() + " " + item.getId());
        }

        final Item dagger = Item.named("Dagger").withRegion(Region.NORTH_AMERICA).get();
        System.out.println(dagger.getName() + " " + dagger.getId());
    }
}