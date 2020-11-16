package mahajan.prateek.intuit.oms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by: pramahajan on 11/15/20 1:34 AM GMT+05:30
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class OMSBusinessException extends Exception {
    public OMSBusinessException(String message) {
        super(message);
    }

    public OMSBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
