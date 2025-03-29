package win.TIA202.www.web.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RequestDataAndUser implements Serializable {
    private static final long serialVersionUID = 7984750631792179395L;

    private User requestData;
    private User user;
}
