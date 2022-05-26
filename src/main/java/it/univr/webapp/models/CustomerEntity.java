package it.univr.webapp.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "customer", schema = "public", catalog = "Orders")
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

    @Id
    @Column(name = "CUST_CODE")
    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    @Basic
    @Column(name = "CUST_NAME")
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    @Basic
    @Column(name = "CUST_CITY")
    public String getCustCity() {
        return custCity;
    }

    public void setCustCity(String custCity) {
        this.custCity = custCity;
    }

    @Basic
    @Column(name = "WORKING_AREA")
    public String getWorkingArea() {
        return workingArea;
    }

    public void setWorkingArea(String workingArea) {
        this.workingArea = workingArea;
    }

    @Basic
    @Column(name = "CUST_COUNTRY")
    public String getCustCountry() {
        return custCountry;
    }

    public void setCustCountry(String custCountry) {
        this.custCountry = custCountry;
    }

    @Basic
    @Column(name = "GRADE")
    public BigInteger getGrade() {
        return grade;
    }

    public void setGrade(BigInteger grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "OPENING_AMT")
    public BigDecimal getOpeningAmt() {
        return openingAmt;
    }

    public void setOpeningAmt(BigDecimal openingAmt) {
        this.openingAmt = openingAmt;
    }

    @Basic
    @Column(name = "RECEIVE_AMT")
    public BigDecimal getReceiveAmt() {
        return receiveAmt;
    }

    public void setReceiveAmt(BigDecimal receiveAmt) {
        this.receiveAmt = receiveAmt;
    }

    @Basic
    @Column(name = "PAYMENT_AMT")
    public BigDecimal getPaymentAmt() {
        return paymentAmt;
    }

    public void setPaymentAmt(BigDecimal paymentAmt) {
        this.paymentAmt = paymentAmt;
    }

    @Basic
    @Column(name = "OUTSTANDING_AMT")
    public BigDecimal getOutstandingAmt() {
        return outstandingAmt;
    }

    public void setOutstandingAmt(BigDecimal outstandingAmt) {
        this.outstandingAmt = outstandingAmt;
    }

    @Basic
    @Column(name = "PHONE_NO")
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
}
