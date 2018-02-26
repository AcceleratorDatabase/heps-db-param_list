/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lvhuihui
 */
@Entity
@Table(name = "re_system_subsystem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReSystemSubsystem.findAll", query = "SELECT r FROM ReSystemSubsystem r")
    , @NamedQuery(name = "ReSystemSubsystem.findById", query = "SELECT r FROM ReSystemSubsystem r WHERE r.id = :id")})
public class ReSystemSubsystem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @JoinColumn(name = "subsystem_id", referencedColumnName = "Id")
    @ManyToOne
    private Subsystem subsystemId;
    @JoinColumn(name = "system_id", referencedColumnName = "Id")
    @ManyToOne
    private System systemId;

    public ReSystemSubsystem() {
    }

    public ReSystemSubsystem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Subsystem getSubsystemId() {
        return subsystemId;
    }

    public void setSubsystemId(Subsystem subsystemId) {
        this.subsystemId = subsystemId;
    }

    public System getSystemId() {
        return systemId;
    }

    public void setSystemId(System systemId) {
        this.systemId = systemId;
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
        if (!(object instanceof ReSystemSubsystem)) {
            return false;
        }
        ReSystemSubsystem other = (ReSystemSubsystem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "heps.db.param_list.entity.ReSystemSubsystem[ id=" + id + " ]";
    }
    
}
