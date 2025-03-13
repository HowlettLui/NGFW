package win.TIA202.www.web.team.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

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
    private String gameNo;
    
    @Column(name = "game_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date gameDate;
    
    @Column(name = "game_location")
    private String gameLocation;
    
    @Column(name = "game_homescore")
    private String homeTeamScore;
    
    @Column(name = "game_awayscore")
    private String awayTeamScore;
}
