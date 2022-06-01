package it.univr.webapp.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
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
    private CustomerEntity customerByCustCode;
    private AgentsEntity agentsByAgentCode;

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
    public LocalDate getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(LocalDate ordDate) {
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

    @ManyToOne
    @JoinColumn(name = "CUST_CODE", referencedColumnName = "CUST_CODE", nullable = false)
    public CustomerEntity getCustomerByCustCode() {
        return customerByCustCode;
    }

    public void setCustomerByCustCode(CustomerEntity customerByCustCode) {
        this.customerByCustCode = customerByCustCode;
    }

    @ManyToOne
    @JoinColumn(name = "AGENT_CODE", referencedColumnName = "AGENT_CODE", nullable = false)
    public AgentsEntity getAgentsByAgentCode() {
        return agentsByAgentCode;
    }

    public void setAgentsByAgentCode(AgentsEntity agentsByAgentCode) {
        this.agentsByAgentCode = agentsByAgentCode;
    }
}
