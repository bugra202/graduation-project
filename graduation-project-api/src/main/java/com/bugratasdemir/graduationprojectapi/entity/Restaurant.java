package com.bugratasdemir.graduationprojectapi.entity;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection = "n11core")
public class Restaurant {

    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    @Indexed(name = "name", type = "string")
    private String name;

    @Indexed(name = "latitude", type = "pdoubles")
    private double latitude;

    @Indexed(name = "longitude", type = "pdoubles")
    private double longitude;

    @Indexed(name = "rate", type = "pdoubles")
    private double rate;

}
