package win.TIA202.www.web.team.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventExtendedPropsDto {
    private String homeTeam;
    private String awayTeam;
    private String homeScore;
    private String awayScore;
    private String Location;
}
