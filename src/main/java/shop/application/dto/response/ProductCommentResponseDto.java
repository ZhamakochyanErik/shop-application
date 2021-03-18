package shop.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCommentResponseDto {

    private long id;

    private String comment;

    private LocalDateTime createdDate;

    private UserResponseDto user;
}
