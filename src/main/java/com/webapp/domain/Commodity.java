package com.webapp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="commodities")
public class Commodity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cid;


	@Column(length=30)
	@NotNull
	private String name;
	@NotNull
	private Double price;
	
	private String image;

	public enum Types{
		器材健身,跑步,篮球,网球,足球,羽毛球,运动休闲;
		public static List<String> toList(){
			Types[] tp = Types.values();
			List<String> datas = new ArrayList<>();
			for(Types t : tp) {
				datas.add(t.name());
			}
			return datas;
		}
	};
	
	private Types ctype;
	private String description;
	
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Types getCtype() {
		return ctype;
	}
	public void setCtype(Types ctype) {
		this.ctype = ctype;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
