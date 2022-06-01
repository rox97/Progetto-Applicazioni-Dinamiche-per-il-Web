package it.univr.webapp.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "public", catalog = "orders")
public class OrdersEntity {
    private int ordNum;
    private BigDecimal ordAmount;
    private BigDecimal advanceAmount;
    private LocalDate ordDate;
    private String ordDescription;
    private CustomerEntity customer;
    private AgentsEntity agent;



    @Id
    @Column(name = "ord_num")
    public int getOrdNum() {
        return ordNum;
    }

    public void setOrdNum(int ordNum) {
        this.ordNum = ordNum;
    }

    @Basic
    @Column(name = "ord_amount")
    public BigDecimal getOrdAmount() {
        return ordAmount;
    }

    public void setOrdAmount(BigDecimal ordAmount) {
        this.ordAmount = ordAmount;
    }

    @Basic
    @Column(name = "advance_amount")
    public BigDecimal getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(BigDecimal advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    @Basic
    @Column(name = "ord_date")
    public LocalDate getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(LocalDate ordDate) {
        this.ordDate = ordDate;
    }

    @Basic
    @Column(name = "ord_description")
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

    @ManyToOne
    @JoinColumn(name = "cust_code", referencedColumnName = "cust_code", nullable = false)
    public CustomerEntity getCustomerByCustCode() {
        return customer;
    }

    public void setCustomerByCustCode(CustomerEntity customer) {
        this.customer = customer;
    }

    @ManyToOne
    @JoinColumn(name = "agent_code", referencedColumnName = "agent_code", nullable = false)
    public AgentsEntity getAgentsByAgentCode() {
        return agent;
    }

    public void setAgentsByAgentCode(AgentsEntity agent) {
        this.agent = agent;
    }
}
