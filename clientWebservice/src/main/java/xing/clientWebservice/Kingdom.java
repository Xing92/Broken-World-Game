package xing.clientWebservice;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "kingdom")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Kingdom {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JsonBackReference
	private User user;
	@OneToOne(fetch = FetchType.EAGER)
	private Move move;
	private String name;
	private String race;
	private int people;
	private int army;
	private int miners;
	private int land;
	private int attacksWon;
	private int attacksLost;
	private int defendsWon;
	private int defendsLost;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public int getId() {
		return id;
	}

	public int getAttacksWon() {
		return attacksWon;
	}

	public void incrementAttacksWon() {
		this.attacksWon++;
	}

	public int getAttacksLost() {
		return attacksLost;
	}

	public void incrementAttacksLost() {
		this.attacksLost++;
	}

	public int getDefendsWon() {
		return defendsWon;
	}

	public void incrementDefendsWon() {
		this.defendsWon++;
	}

	public int getDefendsLost() {
		return defendsLost;
	}

	public void incrementDefendsLost() {
		this.defendsLost++;;
	}

	public int getLand() {
		return land;
	}

	public void setLand(int land) {
		this.land = land;
	}
	
	public void changeLand(int land){
		this.land += land;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getArmy() {
		return army;
	}

	public void setArmy(int army) {
		this.army = army;
	}

	public int getMiners() {
		return miners;
	}

	public void setMiners(int miners) {
		this.miners = miners;
	}
	
	public void changeMiners(int miners){
		this.miners += miners;
	}
	
	public void changeArmy(int army){
		this.army += army;
	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
		if(move != null)
		move.setKingdom(this);
	}
	
	public boolean canMove(){
		return move==null ? true : false;
	}
	

}
