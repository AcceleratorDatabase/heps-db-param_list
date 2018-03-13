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
@Table(name = "reference")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reference.findAll", query = "SELECT r FROM Reference r")
    , @NamedQuery(name = "Reference.findById", query = "SELECT r FROM Reference r WHERE r.id = :id")
    , @NamedQuery(name = "Reference.findByTitle", query = "SELECT r FROM Reference r WHERE r.title = :title")
    , @NamedQuery(name = "Reference.findByAuthor", query = "SELECT r FROM Reference r WHERE r.author = :author")
    , @NamedQuery(name = "Reference.findByPublication", query = "SELECT r FROM Reference r WHERE r.publication = :publication")
    , @NamedQuery(name = "Reference.findByUrl", query = "SELECT r FROM Reference r WHERE r.url = :url")
    , @NamedQuery(name = "Reference.findByKeywords", query = "SELECT r FROM Reference r WHERE r.keywords = :keywords")})
public class Reference implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "Title")
    private String title;
    @Size(max = 45)
    @Column(name = "Author")
    private String author;
    @Size(max = 45)
    @Column(name = "Publication")
    private String publication;
    @Size(max = 45)
    @Column(name = "URL")
    private String url;
    @Size(max = 45)
    @Column(name = "Keywords")
    private String keywords;
    @OneToMany(mappedBy = "referenceid")
    private List<Parameter> parameterList;

    public Reference() {
    }

    public Reference(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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
        if (!(object instanceof Reference)) {
            return false;
        }
        Reference other = (Reference) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "heps.db.param_list.entity.Reference[ id=" + id + " ]";
    }
    
}
