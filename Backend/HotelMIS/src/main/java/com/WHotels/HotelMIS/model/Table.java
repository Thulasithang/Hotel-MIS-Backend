package com.WHotels.HotelMIS.model;
import jakarta.persistence.*;

@Entity
@jakarta.persistence.Table(name="restaurant_table")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="table_id")
    private long id;

    @Column(name="occupied")
    private boolean occupied;
    @Column(name="waiter_requested")
    private boolean waiterRequested;
    @Transient
    private boolean reserved=false;

    public Table(long id) {
        this.id = id;
    }

    public boolean isReserved() {
        return reserved;
    }

    public Table() {
    }

    public Table(boolean occupied, boolean waiterRequested) {
        this.occupied = occupied;
        this.waiterRequested = waiterRequested;
        //this.reserved = reserved;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isWaiterRequested() {
        return waiterRequested;
    }

    public void setWaiterRequested(boolean waiterRequested) {
        this.waiterRequested = waiterRequested;
    }

    public void setReserved(boolean reserved){
        this.reserved=reserved;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", occupied=" + occupied +
                ", waiterRequested=" + waiterRequested +
                ", reserved=" + reserved +
                '}';
    }
}

