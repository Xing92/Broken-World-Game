package xing.clientWebservice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "move")
public class Move {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne(fetch = FetchType.EAGER)
	private Kingdom kingdom;
	private int changeMiners;
	private int changeArmy;

	public Kingdom getKingdom() {
		return kingdom;
	}

	public void setKingdom(Kingdom kingdom) {
		this.kingdom = kingdom;
	}

	public int getChangeMiners() {
		return changeMiners;
	}

	public void setChangeMiners(int changeMiners) {
		this.changeMiners = changeMiners;
	}

	public int getChangeArmy() {
		return changeArmy;
	}

	public void setChangeArmy(int changeArmy) {
		this.changeArmy = changeArmy;
	}

	public int getId() {
		return id;
	}

}
