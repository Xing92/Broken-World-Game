package xing.brokenworldserver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "user")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String login;
	private String password;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonManagedReference
	private List<Kingdom> kingdoms = new ArrayList<Kingdom>();

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addKingdom(Kingdom kingdom) {
		kingdoms.add(kingdom);
		kingdom.setUser(this);
	}

	public List<Kingdom> getKingdoms() {
		return kingdoms;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", kingdoms=" + kingdoms.size() + "]";
	}
	
	
}
