/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingame;

import OtherStuff.VillagerActivities;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import resources.GatherableResource;
import resources.Resource;
import resources.ResourceUnit;
import simplebuildaoo.Event;
import simplebuildaoo.gameclasses.InGameOverview;
import simplebuildaoo.gameclasses.TechTreeSheet;
import simplebuildaoo.gameclasses.UnitStuff.allunits.Villager;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public class VillagerManager {

    public TechTreeSheet CTS;
    public InGameOverview IGO;
    
    public ArrayList<Villager> allVills = new ArrayList<>();

    public ArrayList<Villager> collectingVillagers = new ArrayList<>();
    public ArrayList<Villager> freeVils = new ArrayList();
    public ArrayList<Villager> buildingVils = new ArrayList();
    public ArrayList<Villager> walkingVills = new ArrayList();

    public ArrayList<Villager> getAllVillagersFromTask(VillagerActivities task) {
        ArrayList<Villager> result = new ArrayList<>();
        switch (task) {
            case BUILDER:
                result = buildingVils;
                break;
            case COLLECTOR:
                result = collectingVillagers;
                break;
            case FARMER:
            case FORAGER:
            case GOLDMINER:
            case HUNTER:
            case SHEPHERDER:
            case STONEMINER:
            case WOODCHOPPER:
                result = collectingVillagers;
                break;
            case IDLING:
                result = freeVils;
                break;
            case WALKING:
                result = walkingVills;
            default:
            //error
        }
        return result;
    }

    public void toResource(VillagerActivities from, VillagerActivities to, ResourceUnit source, int amount) {
        ArrayList<Villager> fromVils = aquireVillagers(from, amount);
        ArrayList<Villager> toVils = aquireVillagers(to, amount);

        int count = amount;

        for (int i = 0; i < amount && !fromVils.isEmpty(); i++) {
            Villager nes = fromVils.remove(0);
            nes.task = to;
            //System.out.println("Villager confverted from " + from.name() + " to " + to.name() + " at " + this.currentResources.time);
            if (from.equals(VillagerActivities.COLLECTOR)) {
                collectingVillagers.remove(nes);
                count--;
            }
            if (to.equals(VillagerActivities.COLLECTOR)) {
                collectingVillagers.add(nes);
                source.gatheres.add(nes);

            }
            nes.currentlyCollecting = source.meGather;
            toVils.add(nes);
        }
        if (count > 0) {
            //System.err.println("Couldnt assign everyone to " + source.toString() + " at " + this.currentResources.time);
        }
    }

    public boolean reassignTaskonVillagers(VillagerActivities from, VillagerActivities to, int count) {
        for (int i = 0; i < allVills.size() && count > 0; i++) {
            // to be optimized - extract Villager from other Units
            Villager vill = allVills.get(i);
            if (vill.task.equals(from)) {
                count--;
                vill.task = to;
                boolean a = getAllVillagersFromTask(from).remove(vill);
                getAllVillagersFromTask(to).add(vill);
                //System.out.println("Villager confverted from " + a + from.name() + " to " + to.name() + " at " + this.currentResources.time);
                //vill.currentlyCollecting = to;
            }
        }
        return count <= 0;
    }

    public void toThing(ResourceUnit unit, VillagerActivities from, int amount) {
        //Reassign all villager duties
        //reorganize Villager Arrays
        //recalculate resource speed

        if (unit.currentHoldingResource.total() > 0) {

            unit.updateHoldingOnResource(CTS);
            //updateHoldingOnResourceUnit(unit);

            toResource(from, VillagerActivities.COLLECTOR, unit, amount);

            double resources = unit.currentHoldingResource.total();
            double gatherSpeed = unit.gatheres.size() * this.CTS.getCollectionSpeedByResouceType(unit.meGather).total();
            double depletionSpeed = unit.depletionSpeed.total() / unit.depletionSpeed.time;
            int timeUntilDepletion = (int) (resources / (gatherSpeed + depletionSpeed));

            Consumer resourceDepletedEvent = (stuuf) -> {
                //free em up
                assert (unit.currentHoldingResource.total() < 1);
                unit.currentHoldingResource = new Resource();
                unit.gatheres.forEach((t) -> {
                    t.task = VillagerActivities.IDLING;
                    t.currentlyCollecting = GatherableResource.NONE;
                    this.collectingVillagers.remove(t);
                    this.freeVils.add(t);
                });
                unit.gatheres.clear();
                IGO.resman.calculateResourcesPerSecond(allVills, CTS);

            };

            if (unit.lastlyUpdated == -1) {
                unit.depletionEvent = new Event((int) (IGO.resman.currentResources.time + timeUntilDepletion), resourceDepletedEvent);
                IGO.events.add(unit.depletionEvent);
            } else {
                unit.depletionEvent.gameTime = (int) (IGO.resman.currentResources.time + timeUntilDepletion);
                unit.depletionEvent.method = resourceDepletedEvent;
                //this.updateEvent(unit.depletionEvent, resourceDepletedEvent);
            }
            unit.lastlyUpdated = IGO.resman.currentResources.time;
            IGO.resman.calculateResourcesPerSecond(allVills, CTS);

        }
    }


// to be optimized - extract Villager from other Units, IT IS DONE :)

    private ArrayList<Villager> aquireVillagers(VillagerActivities resource, int amount) {
        ArrayList<Villager> result = new ArrayList<>();
        for (int i = 0; result.size() < amount && i < allVills.size(); i++) {
            Villager vill = allVills.get(i);
            if (vill.task.equals(resource)) {
                result.add(vill);
            }
        }
        
        //Check me out someday!!
        List<Villager> resulte = allVills.stream()
                .filter(vil -> vil.task.equals(resource))
                .collect(Collectors.toList());
        return result;
    }
}