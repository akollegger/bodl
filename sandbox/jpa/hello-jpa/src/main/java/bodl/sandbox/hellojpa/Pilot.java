package bodl.sandbox.hellojpa;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@Table(name = "PILOT")
public class Pilot {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String name;
    
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private GregorianCalendar lastUpdate;
    
    private int points = 0;
    
    public Pilot() {;}
        
    public Pilot(String name,int points) {
        this.name=name;
        addPoints(points);
    }
        
    public int getPoints() {
        return points;
    }
    
    public void addPoints(int points) {
        this.points+=points;
        setLastUpdate(new GregorianCalendar());
    }
    
    public String getName() {
        return name;
    }
    
    public String toString() {
        return name+"/"+points + " (" + getLastUpdate() + ")";
    }

    public GregorianCalendar getLastUpdate() {
        return lastUpdate;
    }
    
    public void setLastUpdate(GregorianCalendar lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
}
