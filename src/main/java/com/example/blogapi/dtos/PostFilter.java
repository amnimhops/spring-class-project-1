package com.example.blogapi.dtos;

import com.example.blogapi.entities.Post;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Valores usados para filtrar las publicaciones")
public class PostFilter {
    @Schema(description = "Filtra por el correo electrónico del creador de la publicación")
    private String author;
    @Schema(description = "Filtra los posts en los que el título o la descripción coincida con este valor")
    private String containsText;

    public boolean matches(Post post) {
        if(author!= null && post.getAuthorEmail() != author) return false;
        if(
            containsText != null &&
            !post.getBody().contains(containsText) &&
            !post.getTitle().contains(containsText)
        ){
            return false;
        }

        return true;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContainsText() {
        return containsText;
    }

    public void setContainsText(String containsText) {
        this.containsText = containsText;
    }
}
