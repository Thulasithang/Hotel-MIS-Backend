package com.WHotels.HotelMIS.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@NoArgsConstructor
public class MonthlyRevenue {
    private BigDecimal january;
    private BigDecimal february;
    private BigDecimal march;
    private BigDecimal april;
    private BigDecimal may;
    private BigDecimal june;
    private BigDecimal july;
    private BigDecimal august;
    private BigDecimal september;
    private BigDecimal october;
    private BigDecimal november;
    private BigDecimal december;


    public void setJanuary(BigDecimal january) {
        if (january == null) {
            this.january = BigDecimal.ZERO;
        }
        else {
            this.january = january;
        }
    }

    public void setFebruary(BigDecimal february) {
        if (february == null) {
            this.february = BigDecimal.ZERO;
        }
        else {
            this.february = february;
        }


    }

    public void setMarch(BigDecimal march) {
        if (march == null) {
            this.march = BigDecimal.ZERO;
        }
        else {
            this.march = march;
        }
    }

    public void setApril(BigDecimal april) {
        if (april == null) {
            this.april = BigDecimal.ZERO;
        }
        else {
            this.april = april;
        }
    }

    public void setMay(BigDecimal may) {
        if (may == null) {
            this.may = BigDecimal.ZERO;
        }
        else {
            this.may = may;
        }
    }

    public void setJune(BigDecimal june) {
        if (june == null) {
            this.june = BigDecimal.ZERO;
        }
        else {
            this.june = june;
        }
    }

    public void setJuly(BigDecimal july) {
        if (july == null) {
            this.july = BigDecimal.ZERO;
        }
        else {
            this.july = july;
        }
    }

    public void setAugust(BigDecimal august) {
        if (august == null) {
            this.august = BigDecimal.ZERO;
        }
        else {
            this.august = august;
        }
    }

    public void setSeptember(BigDecimal september) {
        if (september == null) {
            this.september = BigDecimal.ZERO;
        }
        else {
            this.september = september;
        }
    }

    public void setOctober(BigDecimal october) {
        if  (october == null) {
            this.october = BigDecimal.ZERO;
        }
        else {
            this.october = october;
        }
    }

    public void setNovember(BigDecimal november) {
        if (november == null) {
            this.november = BigDecimal.ZERO;
        }
        else {
            this.november = november;
        }
    }

    public void setDecember(BigDecimal december) {
        if (december == null) {
            this.december = BigDecimal.ZERO;
        }
        else {
            this.december = december;
        }
    }



}
