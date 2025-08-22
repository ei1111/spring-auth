package com.spring_auth.app.request;

import com.spring_auth.app.entity.App;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppRequest {

    @Schema(name = "appName", description = "기능명", example = "meetingroom")
    private String appName;


    public App toAppEntity() {
        return App.builder()
                .systemName(appName)
                .build();
    }
}
