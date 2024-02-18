package com.example.blogapi.dtos;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos necesarios para la creación de una publicación")
public class CreatePostInput {
    @Schema(description="Título de la publicación")
    private String title;
    @Schema(description="Texto del post")
    private String body;
    @Schema(description="Email del autor")
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

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
