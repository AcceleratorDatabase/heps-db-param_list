/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lvhuihui
 */
@Entity
@Table(name = "data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Data.findAll", query = "SELECT d FROM Data d")
    , @NamedQuery(name = "Data.findById", query = "SELECT d FROM Data d WHERE d.id = :id")
    , @NamedQuery(name = "Data.findByValue", query = "SELECT d FROM Data d WHERE d.value = :value")
    , @NamedQuery(name = "Data.findByDatemodified", query = "SELECT d FROM Data d WHERE d.datemodified = :datemodified")
    , @NamedQuery(name = "Data.findByComment", query = "SELECT d FROM Data d WHERE d.comment = :comment")
    , @NamedQuery(name = "Data.findByStatus", query = "SELECT d FROM Data d WHERE d.status = :status")})
public class Data implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "Value")
    private String value;
    @Column(name = "Date_modified")
    @Temporal(TemporalType.DATE)
    private Date datemodified;
    @Size(max = 80)
    @Column(name = "Comment")
    private String comment;
    @Size(max = 45)
    @Column(name = "Status")
    private String status;
    @JoinColumn(name = "Attribute_id", referencedColumnName = "Id")
    @ManyToOne
    private Attribute attributeid;
    @JoinColumn(name = "device_id", referencedColumnName = "Id")
    @ManyToOne
    private Device deviceId;
    @JoinColumn(name = "Parameter_id", referencedColumnName = "Id")
    @ManyToOne
    private Parameter parameterid;
    @JoinColumn(name = "Subsystem_id", referencedColumnName = "Id")
    @ManyToOne
    private Subsystem subsystemid;
    @JoinColumn(name = "System_id", referencedColumnName = "Id")
    @ManyToOne
    private System systemid;
    @JoinColumn(name = "Team_id", referencedColumnName = "Id")
    @ManyToOne
    private Team teamid;
    @JoinColumn(name = "Version_id", referencedColumnName = "Id")
    @ManyToOne
    private Version versionid;

    public Data() {
    }

    public Data(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDatemodified() {
        return datemodified;
    }

    public void setDatemodified(Date datemodified) {
        this.datemodified = datemodified;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Attribute getAttributeid() {
        return attributeid;
    }

    public void setAttributeid(Attribute attributeid) {
        this.attributeid = attributeid;
    }

    public Device getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Device deviceId) {
        this.deviceId = deviceId;
    }

    public Parameter getParameterid() {
        return parameterid;
    }

    public void setParameterid(Parameter parameterid) {
        this.parameterid = parameterid;
    }

    public Subsystem getSubsystemid() {
        return subsystemid;
    }

    public void setSubsystemid(Subsystem subsystemid) {
        this.subsystemid = subsystemid;
    }

    public System getSystemid() {
        return systemid;
    }

    public void setSystemid(System systemid) {
        this.systemid = systemid;
    }

    public Team getTeamid() {
        return teamid;
    }

    public void setTeamid(Team teamid) {
        this.teamid = teamid;
    }

    public Version getVersionid() {
        return versionid;
    }

    public void setVersionid(Version versionid) {
        this.versionid = versionid;
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
        if (!(object instanceof Data)) {
            return false;
        }
        Data other = (Data) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "heps.db.param_list.entity.Data[ id=" + id + " ]";
    }
    
}
