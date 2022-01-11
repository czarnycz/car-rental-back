package com.example.projektpraktyczny.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUserRole {
    // USER, ADMIN

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<ApplicationUser> users;
}