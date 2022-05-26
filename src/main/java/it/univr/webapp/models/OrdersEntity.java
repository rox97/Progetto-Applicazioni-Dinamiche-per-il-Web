package it.univr.webapp.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "public", catalog = "Orders")
public class OrdersEntity {
    private int ordNum;
    private BigDecimal ordAmount;
    private BigDecimal advanceAmount;
    private Date ordDate;
    private String ordDescription;

    @Id
    @Column(name = "ORD_NUM")
    public int getOrdNum() {
        return ordNum;
    }

    public void setOrdNum(int ordNum) {
        this.ordNum = ordNum;
    }

    @Basic
    @Column(name = "ORD_AMOUNT")
    public BigDecimal getOrdAmount() {
        return ordAmount;
    }

    public void setOrdAmount(BigDecimal ordAmount) {
        this.ordAmount = ordAmount;
    }

    @Basic
    @Column(name = "ADVANCE_AMOUNT")
    public BigDecimal getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(BigDecimal advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    @Basic
    @Column(name = "ORD_DATE")
    public Date getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(Date ordDate) {
        this.ordDate = ordDate;
    }

    @Basic
    @Column(name = "ORD_DESCRIPTION")
    public String getOrdDescription() {
        return ordDescription;
    }

    public void setOrdDescription(String ordDescription) {
        this.ordDescription = ordDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return ordNum == that.ordNum && Objects.equals(ordAmount, that.ordAmount) && Objects.equals(advanceAmount, that.advanceAmount) && Objects.equals(ordDate, that.ordDate) && Objects.equals(ordDescription, that.ordDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordNum, ordAmount, advanceAmount, ordDate, ordDescription);
    }
}
