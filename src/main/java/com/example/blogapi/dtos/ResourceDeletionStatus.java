package com.example.blogapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta al borrado de un recurso")
public class ResourceDeletionStatus {
    @Schema(description = "Nombre del recurso sobre el que se ejecuta la operación de borrado")
    private String resource;
    @Schema(description = "Identificador del recurso que se ha intentado eliminar")
    private String id;
    @Schema(description = "Flag que indica si el recurso se ha borrado o no")
    private boolean success;

    public ResourceDeletionStatus(String resource, String id, boolean success){
        this.resource = resource;
        this.id = id;
        this.success = success;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
