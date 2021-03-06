package com.clstephenson.homeinfo.api.service;

import com.clstephenson.homeinfo.model.Feature;

import java.util.List;
import java.util.Optional;

public interface FeatureService {
    List<Feature> getAll();

    List<Feature> findByPropertyId(long propertyId);

    List<Feature> findByPropertyIdAndLocationId(long propertyId, long locationId);

    List<Feature> findByLocationId(long locationId);

    Optional<Feature> findById(long id);

    Feature save(Feature feature);

    void deleteById(long id);

    boolean existsById(long id);
}
