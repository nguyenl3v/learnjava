package com.example.demo.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ct")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class contentModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column
  private String content;

  // @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contentmd")
  // private List<contentModel> apiModelList;

  public contentModel() {
  }

  public contentModel(Integer id, String content) {
    this.id = id;
    this.content = content;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public contentModel id(Integer id) {
    this.id = id;
    return this;
  }

  public contentModel content(String content) {
    this.content = content;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof contentModel)) {
            return false;
        }
        contentModel contentModel = (contentModel) o;
        return Objects.equals(id, contentModel.id) && Objects.equals(content, contentModel.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, content);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", content='" + getContent() + "'" +
      "}";
  }

}