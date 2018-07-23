package ru.job4j.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonView;

import javax.annotation.Generated;

/**
 * Created by Пользователь on 18.07.2018.
 */
@JsonPropertyOrder({"name", "firstName", "sex", "description"})
public class Person {

    @JsonProperty
    private String name;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String sex;
    @JsonProperty
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override

    public String toString() {
        return this.getName() + this.getFirstName() + this.description + this.getSex();
    }

    @Override

    public int hashCode() {
        return this.getName().hashCode() + this.getFirstName().hashCode() + this.description.hashCode() + this.getSex().hashCode();
    }
}
