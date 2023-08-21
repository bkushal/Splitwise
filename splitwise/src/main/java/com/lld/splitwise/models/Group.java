package com.lld.splitwise.models;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Group extends BaseModel {
	
	private String description;
	
	@ManyToMany
	private List<User> members;
	private String name;
	
	@ManyToOne
	private User createdBy;

}
