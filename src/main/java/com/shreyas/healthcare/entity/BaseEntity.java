package com.shreyas.healthcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createedAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate(){
        createedAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreateedAt() {
        return createedAt;
    }

    public void setCreateedAt(LocalDateTime createedAt) {
        this.createedAt = createedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
