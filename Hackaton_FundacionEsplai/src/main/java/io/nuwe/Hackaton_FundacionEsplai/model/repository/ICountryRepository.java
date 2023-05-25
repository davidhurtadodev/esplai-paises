package io.nuwe.Hackaton_FundacionEsplai.model.repository;

import io.nuwe.Hackaton_FundacionEsplai.model.domain.CountryC;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICountryRepository extends MongoRepository<CountryC, ObjectId> {
    Optional<CountryC> findByCountry(String country);
}
