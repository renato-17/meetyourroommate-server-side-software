package com.acme.meetyourroommate.domain.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("lessor")
public class Lessor extends Person{
}
