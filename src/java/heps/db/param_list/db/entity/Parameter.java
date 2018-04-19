/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.db.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lvhuihui
 */
@Entity
@Table(name = "parameter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parameter.findAll", query = "SELECT p FROM Parameter p")
    , @NamedQuery(name = "Parameter.findById", query = "SELECT p FROM Parameter p WHERE p.id = :id")
    , @NamedQuery(name = "Parameter.findByName", query = "SELECT p FROM Parameter p WHERE p.name = :name")
    , @NamedQuery(name = "Parameter.findByDefinition", query = "SELECT p FROM Parameter p WHERE p.definition = :definition")
    , @NamedQuery(name = "Parameter.findByDatemodified", query = "SELECT p FROM Parameter p WHERE p.datemodified = :datemodified")})
public class Parameter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @Size(max = 500)
    @Column(name = "Definition")
    private String definition;
    @Column(name = "Date_modified")
    @Temporal(TemporalType.DATE)
    private Date datemodified;
    @OneToMany(mappedBy = "parameterid")
    private List<Data> dataList;
    @JoinColumn(name = "Reference_id", referencedColumnName = "Id")
    @ManyToOne
    private Reference referenceid;
    @JoinColumn(name = "Unit_id", referencedColumnName = "Id")
    @ManyToOne
    private Unit unitid;

    public Parameter() {
    }

    public Parameter(Integer id) {
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

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Date getDatemodified() {
        return datemodified;
    }

    public void setDatemodified(Date datemodified) {
        this.datemodified = datemodified;
    }

    @XmlTransient
    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public Reference getReferenceid() {
        return referenceid;
    }

    public void setReferenceid(Reference referenceid) {
        this.referenceid = referenceid;
    }

    public Unit getUnitid() {
        return unitid;
    }

    public void setUnitid(Unit unitid) {
        this.unitid = unitid;
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
        if (!(object instanceof Parameter)) {
            return false;
        }
        Parameter other = (Parameter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "heps.db.param_list.db.entity.Parameter[ id=" + id + " ]";
    }
    
}
