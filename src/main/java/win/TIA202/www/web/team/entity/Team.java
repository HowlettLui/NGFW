package win.TIA202.www.web.team.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "team")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Integer id;
    
    @Column(name = "team_name", nullable = false)
    private String teamName;
    
    @Column(name = "team_logo")
    private String teamLogo;
    
    @Column(name = "team_link")
    private String teamLink;
    
    @Column(name = "team_court")
    private String teamCourt;
    
    @Column(name = "description")
    private String description;
}
