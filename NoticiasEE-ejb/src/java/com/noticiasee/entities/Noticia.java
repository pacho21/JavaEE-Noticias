/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noticiasee.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "noticia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Noticia.findAll", query = "SELECT n FROM Noticia n")
    , @NamedQuery(name = "Noticia.findById", query = "SELECT n FROM Noticia n WHERE n.id = :id")
    , @NamedQuery(name = "Noticia.findByImgLink", query = "SELECT n FROM Noticia n WHERE n.imgLink = :imgLink")
    , @NamedQuery(name = "Noticia.findByTitle", query = "SELECT n FROM Noticia n WHERE n.title = :title")
    , @NamedQuery(name = "Noticia.findByBody", query = "SELECT n FROM Noticia n WHERE n.body = :body")
    , @NamedQuery(name = "Noticia.findByShortDesc", query = "SELECT n FROM Noticia n WHERE n.shortDesc = :shortDesc")
    , @NamedQuery(name = "Noticia.findByAuthor", query = "SELECT n FROM Noticia n WHERE n.author = :author")
    , @NamedQuery(name = "Noticia.findByPosted", query = "SELECT n FROM Noticia n WHERE n.posted = :posted")})
public class Noticia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "img_link")
    private String imgLink;
    @Size(max = 2147483647)
    @Column(name = "title")
    private String title;
    @Size(max = 2147483647)
    @Column(name = "body")
    private String body;
    @Size(max = 2147483647)
    @Column(name = "short_desc")
    private String shortDesc;
    @Size(max = 2147483647)
    @Column(name = "author")
    private String author;
    @Column(name = "posted")
    @Temporal(TemporalType.DATE)
    private Date posted;

    public Noticia() {
    }

    public Noticia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
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
        if (!(object instanceof Noticia)) {
            return false;
        }
        Noticia other = (Noticia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Noticia[ id=" + id + " ]";
    }
    
}
