package win.TIA202.www.web.team.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private String title;
    private Date start;
    private EventExtendedPropsDto extendedProps;
}
