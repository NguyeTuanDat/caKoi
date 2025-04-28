package ut.edu.koii.models;

import jakarta.persistence.*;

@Entity
@Table(name = "uth_permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Getters, Setters
}


