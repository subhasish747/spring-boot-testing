package com.sn.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.PrimaryKey;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "employees"
)
public class Employee {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;
    @Column( name = "first_name" , nullable = false)
    private String firstName;
    @Column( name = "last_name" , nullable = false)
    private String lastName;
    @Column( nullable = false)
    private String email;
}


