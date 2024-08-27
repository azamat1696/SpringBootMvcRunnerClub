package org.payment.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.payment.web.models.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ClubDto {
     private Long id;
     @NotEmpty(message = "Title is required")
     private String title;
     @NotEmpty (message = "Photo URL is required")
     private String photoUrl;
     @NotEmpty (message = "Content is required")
     private String content;
     private UserEntity createdBy;
     private LocalDateTime createdOn;
     private LocalDateTime updatedOn;
     private List<EventDto> eventDtoList;
}
