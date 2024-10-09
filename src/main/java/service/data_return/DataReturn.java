package service.data_return;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DataReturn {


    String code;

    Boolean status;

    LocalDateTime timestamp = LocalDateTime.now();

    String errorMessage;

    Object data;


}
