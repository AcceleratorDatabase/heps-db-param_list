/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.db.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lvhuihui
 */
@Entity
@Table(name = "accelerator")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accelerator.findAll", query = "SELECT a FROM Accelerator a")
    , @NamedQuery(name = "Accelerator.findById", query = "SELECT a FROM Accelerator a WHERE a.id = :id")
    , @NamedQuery(name = "Accelerator.findByName", query = "SELECT a FROM Accelerator a WHERE a.name = :name")})
public class Accelerator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
   // @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @OneToMany(mappedBy = "accleratorId")
    private List<Version> versionList;

    public Accelerator() {
    }

    public Accelerator(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Version> getVersionList() {
        return versionList;
    }

    public void setVersionList(List<Version> versionList) {
        this.versionList = versionList;
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
        if (!(object instanceof Accelerator)) {
            return false;
        }
        Accelerator other = (Accelerator) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "heps.db.param_list.db.entity.Accelerator[ id=" + id + " ]";
    }
    
}
