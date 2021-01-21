package gameMaps;

import agents.Agent;
import java.util.ArrayList;

/**
 *
 * @author mostafanabil198
 */
public class Territory {
    private Agent owner;
    private int armiesCount;
    private int territoryNumber;
    
    public Territory(int territoryNumber){
        this.territoryNumber = territoryNumber;
        armiesCount = 0;
    }

    public Agent getOwner() {
        return owner;
    }

    public void setOwner(Agent owner) {
        this.owner = owner;
    }

    public int getArmiesCount() {
        return armiesCount;
    }

    public void setArmiesCount(int armiesCount) {
        this.armiesCount = armiesCount;
    }

    public int getTerritoryNumber() {
        return territoryNumber;
    }

    public void setTerritoryNumber(int territoryNumber) {
        this.territoryNumber = territoryNumber;
    }
    
}
