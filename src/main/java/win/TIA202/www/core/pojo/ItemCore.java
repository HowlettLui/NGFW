package win.TIA202.www.core.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties("hibernateLazyInitializer")
public class ItemCore implements Serializable {
    private static final long serialVersionUID = 5409958585139695167L;

    private boolean result;
    private String message;

}
