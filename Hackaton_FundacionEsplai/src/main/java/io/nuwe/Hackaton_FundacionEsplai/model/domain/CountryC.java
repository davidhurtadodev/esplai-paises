package io.nuwe.Hackaton_FundacionEsplai.model.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "countries")
public class CountryC {

    @Field(name = "Country")
    @Schema(description = "Name of the country", example = "Spain")
    private String country;

    @Field(name = "Region")
    @Schema(description = "Region of the country")
    private String region;

    @Field(name = "Population (milions)")
    @Schema(description = "Population of the country")
    private String population;

    @Field(name = "HDI")
    @Schema(description = "HDI of the country")
    private String hdi;

    @Field(name = "GDP per Capita")
    @Schema(description = "GDP of the country")
    private String gdp;

    @Field(name = "Cropland Footprint")
    @Schema(description = "Cropland Footprint of the country")
    private String croplandFootprint;

    @Field(name = "Grazing Footprint")
    @Schema(description = "Grazing Footprint of the country")
    private String grazing;

    @Field(name = "Forest Footprint")
    @Schema(description = "Forest Footprint of the country")
    private String forest;

    @Field(name = "Carbon Footprint")
    @Schema(description = "Carbon Footprint of the country")
    private String carbon;

    @Field(name = "Fish Footprint")
    @Schema(description = "Fish Footprint of the country")
    private String fish;

    @Field(name = "Total Ecological Footprint")
    @Schema(description = "Total Ecological Footprint of the country")
    private String totalEcological;

    @Field(name = "Cropland")
    @Schema(description = "Cropland of the country")
    private String cropland;

    @Field(name = "Grazing Land")
    @Schema(description = "Grazing Land of the country")
    private String grazingLand;

    @Field(name = "Forest Land")
    @Schema(description = "Forest Land of the country")
    private String forestLand;

    @Field(name = "Fishing Water")
    @Schema(description = "Fishing Water of the country")
    private String fishing;

    @Field(name = "Urban Land")
    @Schema(description = "Urban Land of the country")
    private String urbanLand;

    @Field(name = "Total Biocapacity")
    @Schema(description = "Total Biocapacity of the country")
    private String totalBiocapacity;

    @Field(name = "Biocapacity Deficit or Reserve")
    @Schema(description = "Biocapacity Deficit or Reserve of the country")
    private String biocapacityDeficit;

    @Field(name = "Earths Required")
    @Schema(description = "Earths Required of the country")
    private String earthRequired;

    @Field(name = "Countries Required")
    @Schema(description = "Countries Required of the country")
    private String countriesRequired;

    @Field(name = "Data Quality")
    @Schema(description = "Data Quality of the country")
    private String dataQuality;

}
