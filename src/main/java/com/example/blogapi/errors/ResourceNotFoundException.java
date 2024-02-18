package com.example.blogapi.errors;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Class<?> resourceClass, String id){
        super("No se ha encontrado el recurso "+resourceClass.getSimpleName()+" con el identificador "+id);
    }
}
