package it.univr.webapp.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Table(name = "customer", schema = "public", catalog = "orders")
public class CustomerEntity {
    private String custCode;
    private String custName;
    private String custCity;
    private String workingArea;
    private String custCountry;
    private BigInteger grade;
    private BigDecimal openingAmt;
    private BigDecimal receiveAmt;
    private BigDecimal paymentAmt;
    private BigDecimal outstandingAmt;
    private String phoneNo;
    private AgentsEntity agent;

    @Id
    @Column(name = "cust_code")
    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    @Basic
    @Column(name = "cust_name")
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    @Basic
    @Column(name = "cust_city")
    public String getCustCity() {
        return custCity;
    }

    public void setCustCity(String custCity) {
        this.custCity = custCity;
    }

    @Basic
    @Column(name = "working_area")
    public String getWorkingArea() {
        return workingArea;
    }

    public void setWorkingArea(String workingArea) {
        this.workingArea = workingArea;
    }

    @Basic
    @Column(name = "cust_country")
    public String getCustCountry() {
        return custCountry;
    }

    public void setCustCountry(String custCountry) {
        this.custCountry = custCountry;
    }

    @Basic
    @Column(name = "grade")
    public BigInteger getGrade() {
        return grade;
    }

    public void setGrade(BigInteger grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "opening_amt")
    public BigDecimal getOpeningAmt() {
        return openingAmt;
    }

    public void setOpeningAmt(BigDecimal openingAmt) {
        this.openingAmt = openingAmt;
    }

    @Basic
    @Column(name = "receive_amt")
    public BigDecimal getReceiveAmt() {
        return receiveAmt;
    }

    public void setReceiveAmt(BigDecimal receiveAmt) {
        this.receiveAmt = receiveAmt;
    }

    @Basic
    @Column(name = "payment_amt")
    public BigDecimal getPaymentAmt() {
        return paymentAmt;
    }

    public void setPaymentAmt(BigDecimal paymentAmt) {
        this.paymentAmt = paymentAmt;
    }

    @Basic
    @Column(name = "outstanding_amt")
    public BigDecimal getOutstandingAmt() {
        return outstandingAmt;
    }

    public void setOutstandingAmt(BigDecimal outstandingAmt) {
        this.outstandingAmt = outstandingAmt;
    }

    @Basic
    @Column(name = "phone_no")
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(custCode, that.custCode) && Objects.equals(custName, that.custName) && Objects.equals(custCity, that.custCity) && Objects.equals(workingArea, that.workingArea) && Objects.equals(custCountry, that.custCountry) && Objects.equals(grade, that.grade) && Objects.equals(openingAmt, that.openingAmt) && Objects.equals(receiveAmt, that.receiveAmt) && Objects.equals(paymentAmt, that.paymentAmt) && Objects.equals(outstandingAmt, that.outstandingAmt) && Objects.equals(phoneNo, that.phoneNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(custCode, custName, custCity, workingArea, custCountry, grade, openingAmt, receiveAmt, paymentAmt, outstandingAmt, phoneNo);
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
