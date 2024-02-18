package com.example.blogapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta que modela un error de la API")
public class ApiError {
    @Schema(description = "Mensaje que indica el origen del error")
    private String message;
    public ApiError(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
