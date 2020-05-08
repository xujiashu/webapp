package com.webapp.domain;

import java.time.LocalDate;
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
@Table(name="newss")
public class News {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer nid;
	@Column(length=30)
	@NotNull
	private String title;
	@NotNull
	private String author;
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
	
	private Types ntype;
	private String content;
	private LocalDate lastupdate;
	
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Types getNtype() {
		return ntype;
	}
	public void setNtype(Types ntype) {
		this.ntype = ntype;
	}


	public LocalDate getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(LocalDate lastupdate) {
		this.lastupdate = lastupdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
