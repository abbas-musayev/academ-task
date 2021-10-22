package az.crocusoft.crocusofttask2.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ExceptionResponse {

  public  Date time;
  public  String message;
  public  String details;

    public ExceptionResponse(Date time, String message, String details) {
        this.time = time;
        this.message = message;
        this.details = details;
    }
}
