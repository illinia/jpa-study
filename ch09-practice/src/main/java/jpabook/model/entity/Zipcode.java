package jpabook.model.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Zipcode {
    String zip;
    String plusFour;
}
