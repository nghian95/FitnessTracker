package com.nghianguyen.fitnesstracker.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Role class that is used in conjunction with User class to determine what
 * web pages will be available to them. Admin and User - to be implemented still.
 */
@Entity
public class Role {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String name;

   public Role() {
   }

   public Role(String name) {
       this.name = name;
   }
   public Long getId() {
       return id;
   }
   public void setId(Long id) {
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
       return "Role{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
   }

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	   
   
}
