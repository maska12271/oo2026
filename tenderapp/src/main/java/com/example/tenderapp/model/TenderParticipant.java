package com.example.tenderapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tender_participants")
public class TenderParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String manufacturerName;

    private Double offeredPrice;

    @Column(length = 2000)
    private String notes;

    private Boolean winner = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tender_id", nullable = false)
    @JsonIgnore
    private Tender tender;
}