package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB_USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbtractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String passWord;

    @Column(name = "token")
    private String token;

    @Column(name = "expiryTokenDate")
    private Date expiryTokenDate;

    public Date getExpiryTokenDate() {
        return expiryTokenDate;
    }

    @ManyToMany
    @JoinTable(     name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "user_id",nullable = false) },
            inverseJoinColumns = {@JoinColumn(name = "role_id",nullable = false) }
    )
    private List<Role> roles =new ArrayList<>();

    public void setExpiryTokenDate(Date expiryTokenDate) {
        this.expiryTokenDate = expiryTokenDate;
    }
    public void setExpiryTokenDate(int  minutes) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        this.expiryTokenDate = now.getTime();
    }
    public boolean isExpired() {
        return new Date().after(this.expiryTokenDate);
    }
}
