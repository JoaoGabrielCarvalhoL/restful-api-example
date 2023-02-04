package br.com.carv.restful.model.dto.response;

import org.springframework.hateoas.RepresentationModel;

public class PersonResponse extends RepresentationModel<PersonResponse> {

    private Long key;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    public PersonResponse() {
    }

    public PersonResponse(Long key, String firstName, String lastName, String address, String gender) {
        this.key = key;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    public Long getId() {
        return key;
    }

    public void setId(Long id) {
        this.key = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
