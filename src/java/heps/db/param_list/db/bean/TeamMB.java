/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.db.bean;

import heps.db.param_list.db.ejb.TeamFacade;
import heps.db.param_list.db.entity.Team;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Lvhuihui
 */
@ManagedBean
@ViewScoped
public class TeamMB implements Serializable{

   private List<Team> teamList;
    private TeamFacade ejbFacade;

    public TeamMB() {
        ejbFacade=new TeamFacade();
        teamList=ejbFacade.getAllTeam();
    }
     public List<Team> getTeamList() {      
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }
}
