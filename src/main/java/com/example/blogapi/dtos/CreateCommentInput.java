package com.example.blogapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Valores de entrada para crear un comentario")
public class CreateCommentInput {
    @Schema(description="Email del autor del comentario")
    private String author;
    @Schema(description = "Contenido del comentario")
    private String text;
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
