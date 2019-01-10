/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
This class is directly imported from the BDD.
It is used to represent a user.
*/




package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gaga-
 */
@Entity
@Table(name = "credentials")
@XmlRootElement
//Description of all the queries that we will use on the table credentials, when we will have to use it, we can directly call them by their name
@NamedQueries({
    @NamedQuery(name = "Credentials.findAll", query = "SELECT c FROM Credentials c")
    , @NamedQuery(name = "Credentials.findByIDcredentials", query = "SELECT c FROM Credentials c WHERE c.iDcredentials = :iDcredentials")
    , @NamedQuery(name = "Credentials.findByLogin", query = "SELECT c FROM Credentials c WHERE c.login = :login")
    , @NamedQuery(name = "Credentials.findByPwd", query = "SELECT c FROM Credentials c WHERE c.pwd = :pwd")})
public class Credentials implements Serializable 
{
    //Credential Bean, table created according to the variables of the table in the BDD
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_credentials")
    private Integer iDcredentials;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Login")
    private String login;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Pwd")
    private String pwd;
    

    public Credentials() {
    }

    public Integer getIDcredentials() {
        return iDcredentials;
    }

    public void setIDcredentials(Integer iDcredentials) {
        this.iDcredentials = iDcredentials;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean equals(Credentials u) 
    {
        return this.login.equals(u.login) && this.pwd.equals(u.pwd);
    } 
}
