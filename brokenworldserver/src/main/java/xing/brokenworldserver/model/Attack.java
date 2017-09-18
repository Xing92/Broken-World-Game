package xing.brokenworldserver.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attack")
public class Attack {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int people;
	@OneToOne//(cascade = CascadeType.ALL)
	private Kingdom source;
	@OneToOne//(cascade = CascadeType.ALL)
	private Kingdom destination;

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public Kingdom getSource() {
		return source;
	}

	public void setSource(Kingdom source) {
		this.source = source;
	}

	public Kingdom getDestination() {
		return destination;
	}

	public void setDestination(Kingdom destination) {
		this.destination = destination;
	}

	public int getId() {
		return id;
	}

}
