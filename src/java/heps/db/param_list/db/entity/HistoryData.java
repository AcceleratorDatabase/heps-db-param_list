/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.db.entity;

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
@Table(name = "history_data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoryData.findAll", query = "SELECT h FROM HistoryData h")
    , @NamedQuery(name = "HistoryData.findById", query = "SELECT h FROM HistoryData h WHERE h.id = :id")
    , @NamedQuery(name = "HistoryData.findByValue", query = "SELECT h FROM HistoryData h WHERE h.value = :value")
    , @NamedQuery(name = "HistoryData.findByDateModified", query = "SELECT h FROM HistoryData h WHERE h.dateModified = :dateModified")})
public class HistoryData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "value")
    private String value;
    @Column(name = "date_modified")
    @Temporal(TemporalType.DATE)
    private Date dateModified;
    @JoinColumn(name = "data_id", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Data dataId;

    public HistoryData() {
    }

    public HistoryData(Integer id) {
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

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Data getDataId() {
        return dataId;
    }

    public void setDataId(Data dataId) {
        this.dataId = dataId;
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
        if (!(object instanceof HistoryData)) {
            return false;
        }
        HistoryData other = (HistoryData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "heps.db.param_list.entity.HistoryData[ id=" + id + " ]";
    }
    
}
