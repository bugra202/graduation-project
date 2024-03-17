package com.bugratasdemir.graduationprojectapi.repository;

import com.bugratasdemir.graduationprojectapi.entity.Restaurant;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends SolrCrudRepository<Restaurant,String> {
}
