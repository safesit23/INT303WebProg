/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jatawatsafe
 */
@Entity
@Table(name = "TASK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t")
    , @NamedQuery(name = "Task.findByTaskid", query = "SELECT t FROM Task t WHERE t.taskid = :taskid")
    , @NamedQuery(name = "Task.findByCreatedate", query = "SELECT t FROM Task t WHERE t.createdate = :createdate")
    , @NamedQuery(name = "Task.findByTask", query = "SELECT t FROM Task t WHERE t.task = :task")
    , @NamedQuery(name = "Task.findByStatus", query = "SELECT t FROM Task t WHERE t.status = :status")})
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TASKID")
    private Integer taskid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATEDATE")
    @Temporal(TemporalType.DATE)
    private Date createdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TASK")
    private String task;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STATUS")
    private String status;
    @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
    @ManyToOne(optional = false)
    private Account username;

    public Task() {
    }

    public Task(Integer taskid) {
        this.taskid = taskid;
    }

    public Task(Integer taskid, Date createdate, String task, String status) {
        this.taskid = taskid;
        this.createdate = createdate;
        this.task = task;
        this.status = status;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account getUsername() {
        return username;
    }

    public void setUsername(Account username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskid != null ? taskid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.taskid == null && other.taskid != null) || (this.taskid != null && !this.taskid.equals(other.taskid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Task[ taskid=" + taskid + " ]";
    }
    
}
