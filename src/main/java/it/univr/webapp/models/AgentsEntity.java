package it.univr.webapp.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "agents", schema = "public", catalog = "orders")
public class AgentsEntity {
    private String agentCode;
    private String agentName;
    private String workingArea;
    private BigDecimal commission;
    private String phoneNo;
    private String country;

    @Id
    @Column(name = "AGENT_CODE")
    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Basic
    @Column(name = "AGENT_NAME")
    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
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
    @Column(name = "COMMISSION")
    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    @Basic
    @Column(name = "PHONE_NO")
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Basic
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgentsEntity that = (AgentsEntity) o;
        return Objects.equals(agentCode, that.agentCode) && Objects.equals(agentName, that.agentName) && Objects.equals(workingArea, that.workingArea) && Objects.equals(commission, that.commission) && Objects.equals(phoneNo, that.phoneNo) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agentCode, agentName, workingArea, commission, phoneNo, country);
    }
}
