package cn.sc.SSHDemo.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee", schema = "demo")
public class EmployeeEntity {
    private int id;
    private String name;
    private String email;
    private Date birth;
    private Date createTime;
    private DepartmentEntity departmentEntity;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 20)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "birth", nullable = true)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne
    @JoinColumn(name = "department_id")
    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public void setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEntity that = (EmployeeEntity) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (!email.equals(that.email)) return false;
        if (!birth.equals(that.birth)) return false;
        if (!createTime.equals(that.createTime)) return false;
        return departmentEntity.equals(that.departmentEntity);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + birth.hashCode();
        result = 31 * result + createTime.hashCode();
        result = 31 * result + departmentEntity.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", createTime=" + createTime +
                ", departmentEntity.id=" + departmentEntity +
                '}';
    }
}
