package com.example.blogapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos para la edición de la publicación")
public class EditPostInput {
    @Schema(description="Nuevo título del post")
    private String title;
    @Schema(description="Nuevo cuerpo de la publicación")
    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
