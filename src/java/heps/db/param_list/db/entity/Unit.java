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
@Table(name = "unit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unit.findAll", query = "SELECT u FROM Unit u")
    , @NamedQuery(name = "Unit.findById", query = "SELECT u FROM Unit u WHERE u.id = :id")
    , @NamedQuery(name = "Unit.findByName", query = "SELECT u FROM Unit u WHERE u.name = :name")})
public class Unit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
   // @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @OneToMany(mappedBy = "unitid")
    private List<Parameter> parameterList;

    public Unit() {
    }

    public Unit(Integer id) {
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
    public List<Parameter> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<Parameter> parameterList) {
        this.parameterList = parameterList;
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
        if (!(object instanceof Unit)) {
            return false;
        }
        Unit other = (Unit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "heps.db.param_list.db.entity.Unit[ id=" + id + " ]";
    }
    
}
