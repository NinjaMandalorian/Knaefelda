package knaefelda.task.combat;

import knaefelda.dungeon.Dungeon;
import knaefelda.dungeon.DungeonRaid;
import knaefelda.dungeon.DungeonRaid.RaidStatus;
import knaefelda.party.AdventurerParty;
import knaefelda.task.AbstractTask;

public class DungeonRaidTask extends AbstractTask {

    @SuppressWarnings("unused")
    private AdventurerParty party;
    private DungeonRaid raid;

    public DungeonRaidTask(AdventurerParty party, Dungeon dungeon) {
        super(dungeon.getName() + "-" + party.getName(), party, "A dungeon raiding task.");
        raid = dungeon.raid(party);
    }

    @Override
    public void step() {
        // For now, DungeonRaid is stepped internally by the dungeon.
        // This may be improved by moving the stepping into task in future.
        if (raid.getStatus() == RaidStatus.COMPLETED) {
            completed = true;
        }
    }
    
}
