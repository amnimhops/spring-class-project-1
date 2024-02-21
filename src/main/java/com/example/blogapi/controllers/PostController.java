package com.example.blogapi.controllers;

import com.example.blogapi.errors.ResourceNotFoundException;
import com.example.blogapi.errors.ServiceException;
import com.example.blogapi.model.*;
import com.example.blogapi.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.rowset.serial.SerialException;
import java.util.List;
import java.util.Optional;

/**
 * Controlador de publicaciones.
 *
 * La tarea consiste en añadir las anotaciones necesarias para convertir
 * esta clase en un controlador Spring, implementar y anotar los métodos
 * de servicio para adaptar el controlador a los endpoints publicados en
 * la API y crear todas las clases que faltan.
 */
@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //añade una nueva publicacion
    @PostMapping("/")
    public ResponseEntity<?> createPost(CreatePostInput postInput){
        Post newPost = postService.createPost(postInput);
        return ResponseEntity.ok(newPost);
    }

    //buscar publicaciones por filtro
    @PostMapping("/search")
    public ResponseEntity<List<Post>> findPost(PostFilter postFilter){
        List<Post> publicFilter = postService.findPosts(postFilter);

        return ResponseEntity.ok(publicFilter);
    }

    //Identificador de la publicación donde se desea comentar
    //duda entre requestparam y pathvariable
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addCommentToPost(@RequestParam("id") String postId,
                                                    CreateCommentInput commentInput){
        try {
            Comment newComment = postService.addComment(postId,commentInput);
            return ResponseEntity.ok(newComment);
        }catch (ResourceNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    //Obtener la lista de comentarios de un post por si id
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable String id){
        try{
            List<Comment> comments = postService.getPostComments(id);
            return ResponseEntity.ok(comments);
        }catch (ResourceNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    //Eliminar publicacion segun id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable String id){
        boolean delete = postService.deletePost(id);
        if(delete){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //Editar publicacion mediante el id
    public ResponseEntity<Post> updatePostById(@PathVariable String id,
                                               EditPostInput editPostInput){
        try{
            Post updatePost = postService.editPost(id,editPostInput);
            return ResponseEntity.ok(updatePost);
        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch (ResourceNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    //Acceder al contenido de la publicacion mediante el id
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id){
        try {
            Post post = postService.findPostById(id).orElseThrow(
                    () -> new ResourceNotFoundException(Post.class,id));
            return ResponseEntity.ok(post);
        }catch (ResourceNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }
}
