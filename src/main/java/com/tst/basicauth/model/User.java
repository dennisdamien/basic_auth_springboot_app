package com.tst.basicauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name="tblUser")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name="approvedStatus")
    private Boolean approvedStatus=Boolean.FALSE;

    @Column(name="phnNo")
    private String phnNo;


}
