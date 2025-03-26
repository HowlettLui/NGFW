package win.TIA202.www.core.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties("hibernateLazyInitializer")
public class CartCore implements Serializable {
    private static final long serialVersionUID = -2068445282926630557L;

    private boolean result;
    private String message;
}
