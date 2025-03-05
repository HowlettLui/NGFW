package win.TIA202.www.web.team.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "game")
@Setter
@Getter  
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "team_idh")
    private Team homeTeam;
    
    @ManyToOne
    @JoinColumn(name = "team_ida")
    private Team awayTeam;
    
    @Column(name = "game_num")
    private Integer gameNo;
    
    @Column(name = "game_date")
    private Date gameDate;
    
    @Column(name = "game_location")
    private String gameLocation;
    
    @Column(name = "game_homescore")
    private Integer homeTeamScore;
    
    @Column(name = "game_awayscore")
    private Integer awayTeamScore;
}
