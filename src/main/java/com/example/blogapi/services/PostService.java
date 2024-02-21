package com.example.blogapi.services;

import com.example.blogapi.model.*;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz del servicio de publicaciones.
 *
 * @implNote Observarás que hay errores de código debido
 * a que faltan las definiciones de las clases que modelan
 * los posts, comentarios y DTOs de entrada y salida. Parte
 * del trabajo consiste en crear dichas clases en los paquetes
 * apropiados.
 */
public interface PostService {
    /**
     * Devuelve un post dado su identificador
     * @param id Identificador de la publicación
     * @return El post solicitado
     */
    Optional<Post> findPostById(String id);
    /**
     * Añade una nueva publicación al sistema
     * @param input Información de la publicación
     * @return Los datos de la nueva publicación
     */
    Post createPost(CreatePostInput input);

    /**
     * Modifica los atributos de una publicación
     * @param input Objeto con los nuevos valores de la publicación
     * @return La información de la publicación tras la aplicación
     * de los cambios
     */
    Post editPost(String id, EditPostInput input);

    /**
     * Borra una publicación del sistema
     * @param id Identificador de la publicación que será eliminada
     * @return Un booleano que indica si se ha conseguido eliminar
     * la publicación indicada.
     */
    boolean deletePost(String id);

    /**
     * Busca todas las publicaciones que cumplan con el filtro indicado
     * @param filter Filtro que se aplicará para localizar las publicaciones
     * @return Las publicaciones cque cumplen el criterio.
     */
    List<Post> findPosts(PostFilter filter);

    /**
     * Devuelve todos los comentarios relacionados con una publicación
     * @param id Identificador de la publicación
     * @return La lista de comentarios efectuados
     */
    List<Comment> getPostComments(String id);

    /**
     * Añade un comentario a la publicación indicada
     * @param id Identificador de la publicación
     * @param input Datos del comentario
     * @return Información del comentario registrado
     */
    Comment addComment(String id, CreateCommentInput input);
}
