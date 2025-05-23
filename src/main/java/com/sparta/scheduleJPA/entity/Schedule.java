package com.sparta.scheduleJPA.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="schedule")
@Getter
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "schedule_id")
    private Long id;

    @Setter
    @Column(nullable = false, length = 30)
    private String title;

    @Setter
    @Column(nullable = false, columnDefinition = "longtext", length = 1000)
    private String content;

//    @Column(nullable = false, name = "created_at")
//    private LocalDateTime createdAt;
//
//    @Column(nullable = false, name = "modified_at")
//    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 기본생성자
     */
    public Schedule(){}
    public Schedule(String title, String content, User user){
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
