package com.abcfitness.ignite.entity;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String className;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
    @Column(nullable = false)
    private LocalTime startTime;
    @Column(nullable = false)
    private Integer capacity;
    @Column(nullable = false)
    private Integer durationInMinutes;
    @OneToMany(mappedBy = "clubClass", cascade = CascadeType.ALL)
    private List<Booking> bookings;

}
