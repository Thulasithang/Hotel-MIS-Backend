package com.WHotels.HotelMIS.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
public class Reservation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "reservation_id")
    private int reservationId;
    @Basic
    @Column(name = "table_id")
    private Integer tableId;
    @Basic
    @Column(name = "customer_name")
    private String customerName;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "start_time")
    private Time startTime;
    @Basic
    @Column(name = "end_time")
    private Time endTime;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (reservationId != that.reservationId) return false;
        if (tableId != null ? !tableId.equals(that.tableId) : that.tableId != null) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reservationId;
        result = 31 * result + (tableId != null ? tableId.hashCode() : 0);
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }
}
