package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "text")
public class newApiModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String title;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "contentmd",referencedColumnName = "id", nullable = false)
  @JsonProperty("contentmd")
  private contentModel contentmd;

  public newApiModel() {
  }

  public newApiModel(Long id, String title, contentModel contentmd) {
    this.id = id;
    this.title = title;
    this.contentmd = contentmd;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public contentModel getContentmd() {
    return this.contentmd;
  }

  public void setContentmd(contentModel contentmd) {
    this.contentmd = contentmd;
  }

  public newApiModel id(Long id) {
    this.id = id;
    return this;
  }

  public newApiModel title(String title) {
    this.title = title;
    return this;
  }

  public newApiModel contentmd(contentModel contentmd) {
    this.contentmd = contentmd;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof newApiModel)) {
            return false;
        }
        newApiModel newApiModel = (newApiModel) o;
        return Objects.equals(id, newApiModel.id) && Objects.equals(title, newApiModel.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", title='" + getTitle() + "'" +
      "}";
  }
}