package com.spring_auth.app.entity;

import com.spring_auth.api.entity.Api;
import com.spring_auth.app.request.AppResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Entity(name = "app")
public class App {
    @Id
    @Comment("시스템 pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appId;

    @Column(length = 45)
    @Comment("시스템 이름")
    private String systemName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "app")
    private List<Api>  apis = new ArrayList<>();

    @Builder
    public App(String systemName) {
        this.systemName = systemName;
    }

    public AppResponse toResponse() {
        return AppResponse.builder()
                .appId(this.appId)
                .systemName(this.systemName)
                .apis(apis.stream()
                        .map(Api::toResponse)
                        .toList()
                )
                .build();
    }

}
