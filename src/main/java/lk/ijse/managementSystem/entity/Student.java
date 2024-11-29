package lk.ijse.managementSystem.entity;

import javax.persistence.*;


@lombok.AllArgsConstructor
@lombok.Data
@lombok.NoArgsConstructor

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private String id;

    @Column(name = "student_name")
    private String name;

    @Column(name = "student_address")
    private String address;

    @Column(name = "student_email")
    private String email;

    @Column(name = "student_contact")
    private String contact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_name")
    private User user;



}