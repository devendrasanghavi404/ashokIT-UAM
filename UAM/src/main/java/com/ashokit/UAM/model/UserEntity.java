package com.ashokit.UAM.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passKey;

    @Column(nullable = false, unique = true)
    private long phoneNumber;

    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDate updatedAt;

//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDate.now();
//        updatedAt = LocalDate.now();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        updatedAt = LocalDate.now();
//    }

}
