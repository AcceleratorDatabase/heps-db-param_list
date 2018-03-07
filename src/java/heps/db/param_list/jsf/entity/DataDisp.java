/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.jsf.entity;

import heps.db.param_list.entity.Data;
import heps.db.param_list.entity.HistoryData;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Lvhuihui
 */
@Entity
public class DataDisp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String system;
    private String subsystem;
    private String device;
    private String parameterName;
    private String attibute;
    private String unit;
    private String value;
    @Temporal(TemporalType.DATE)
    private Date changeDate;
    private String definition;
    private String referenceTitle;
    private String referenceAuthor;
    private String referencePublication;
    private String referenceURL;
    private String keyword;
    
    private String team;

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
    private Data data;       
    
      public Data getData() {
        return data;
    }

    public void setData( Data data) {
        this.data = data;
    }


    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getSubsystem() {
        return subsystem;
    }

    public void setSubsystem(String subsystem) {
        this.subsystem = subsystem;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getAttibute() {
        return attibute;
    }

    public void setAttibute(String attibute) {
        this.attibute = attibute;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getReferenceTitle() {
        return referenceTitle;
    }

    public void setReferenceTitle(String referenceTitle) {
        this.referenceTitle = referenceTitle;
    }

    public String getReferenceAuthor() {
        return referenceAuthor;
    }

    public void setReferenceAuthor(String referenceAuthor) {
        this.referenceAuthor = referenceAuthor;
    }

    public String getReferencePublication() {
        return referencePublication;
    }

    public void setReferencePublication(String referencePublication) {
        this.referencePublication = referencePublication;
    }

    public String getReferenceURL() {
        return referenceURL;
    }

    public void setReferenceURL(String referenceURL) {
        this.referenceURL = referenceURL;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
   
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataDisp)) {
            return false;
        }
        DataDisp other = (DataDisp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "heps.db.param_list.jsf.ejb.DataDisp[ id=" + id + " ]";
    }
    
}
