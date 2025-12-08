package characters;

import global.Event;

public class CharacterState {
    protected boolean stateStatus;
    protected Event stateReason;

    public CharacterState(boolean stateStatus) {
        this.stateStatus = stateStatus;
        this.stateReason = null;
    }

    public CharacterState(){
        this.stateStatus=false;
        this.stateReason=null;
    }

    public void setState(boolean stateStatus, Event stateReason){
        this.stateStatus=stateStatus;
        this.stateReason=stateReason;
    }
    public Event getStateReason(){
        return stateReason;
    }

    public boolean getStateStatus() {
        return stateStatus;
    }

}
