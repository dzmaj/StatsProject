package com.mythstats.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Gametype {

	@Id
	private int id;
	
	private String name;
	
	public Gametype() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Gametype [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gametype other = (Gametype) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Gametype(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
