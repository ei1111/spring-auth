package com.spring_auth.api.entity;

import com.spring_auth.api.request.ApiResponse;
import com.spring_auth.app.entity.App;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "api")
public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apiId;

    @JoinColumn(name = "appId")
    @ManyToOne(fetch = FetchType.LAZY)
    private App app;

    @Column(length = 45)
    private String method;

    @Column(length = 45)
    private String path;

    public ApiResponse toResponse() {
        return ApiResponse.builder()
                .apiId(this.apiId)
                .method(this.method)
                .path(this.path)
                .build();
    }
}
