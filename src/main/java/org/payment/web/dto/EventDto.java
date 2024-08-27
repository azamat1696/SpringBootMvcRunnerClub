package org.payment.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.payment.web.models.Club;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
     private Long id;
     @NotEmpty(message = "Name is required")
     private String name;
     @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
     private LocalDateTime startTime;
     @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
     private LocalDateTime endTime;
     @NotEmpty
     private String type;
     private String photoUrl;
     private LocalDateTime createdOn;
     private LocalDateTime updatedOn;
     private Club club;
}
