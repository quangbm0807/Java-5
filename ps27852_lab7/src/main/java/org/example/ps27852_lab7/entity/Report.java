package org.example.ps27852_lab7.entity;

    import jakarta.persistence.Entity;
    import jakarta.persistence.Id;
    import lombok.*;

    import java.io.Serializable;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Report {
        @Id
        private Serializable group;
        private Double sum ;
        private Long count;
    }
