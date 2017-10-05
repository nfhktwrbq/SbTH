package com.thmlf.th;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "users_tb", schema = "public", catalog = "sbth")
public class UsersTbEntity {

    private int userId;
    @NotNull
    @Size(min = 1, max = 40)
    private String firstName;
    @NotNull
    @Size(min = 1, max = 40)
    private String lastName;
    @NotNull
    private String gender;
    private boolean married;
    private String profile;
    private java.util.Date regDate;

    @Id
    @SequenceGenerator( name = "jpaSequence", sequenceName = "users_tb_user_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 40)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 40)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "gender", nullable = false, length = 40)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "married", nullable = false)
    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    @Basic
    @Column(name = "profile", nullable = false, length = 40)
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "reg_date", nullable = true)
    public java.util.Date getRegDate() {
        return regDate;
    }

    public void setRegDate(java.util.Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersTbEntity that = (UsersTbEntity) o;

        if (userId != that.userId) return false;
        if (married != that.married) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (profile != null ? !profile.equals(that.profile) : that.profile != null) return false;
        if (regDate != null ? !regDate.equals(that.regDate) : that.regDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (married ? 1 : 0);
        result = 31 * result + (profile != null ? profile.hashCode() : 0);
        result = 31 * result + (regDate != null ? regDate.hashCode() : 0);
        return result;
    }
}
