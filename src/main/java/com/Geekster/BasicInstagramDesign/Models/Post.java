package com.Geekster.BasicInstagramDesign.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    @NotBlank(message = "Post data is required")
    private String postData;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
