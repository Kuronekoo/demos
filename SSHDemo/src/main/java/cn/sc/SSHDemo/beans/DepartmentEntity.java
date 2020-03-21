package cn.sc.SSHDemo.beans;

import javax.persistence.*;

@Entity
@Table(name = "department", schema = "demo")
public class DepartmentEntity {
    private int id;
    private String departmentName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "department_name", nullable = true, length = 10)
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentEntity that = (DepartmentEntity) o;

        if (id != that.id) return false;
        if (departmentName != null ? !departmentName.equals(that.departmentName) : that.departmentName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
