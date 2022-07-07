package it.univr.webapp.models.orders;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agents", schema = "public", catalog = "orders")
public class AgentsEntity {
    private String agentCode;
    private String agentName;
    private String workingArea;
    private BigDecimal commission;
    private String phoneNo;
    private String country;

    public AgentsEntity(String agentCode, String agentName) {
        this.agentCode = agentCode;
        this.agentName = agentName;
    }

    @Id
    @Column(name = "agent_code")
    public String getAgentCode() {
        return agentCode;
    }
    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    @Basic
    @Column(name = "agent_name")
    public String getAgentName() {
        return agentName;
    }
    public void setAgentName(String agentName) {
        this.agentName = agentName;
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
    @Column(name = "commission")
    public BigDecimal getCommission() {
        return commission;
    }
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    @Basic
    @Column(name = "phone_no")
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Basic
    @Column(name = "country")
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
