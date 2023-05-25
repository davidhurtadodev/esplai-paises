package io.nuwe.Hackaton_FundacionEsplai.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {

    @Schema(description = "Region of the country")
    private String region;

    @Schema(description = "Population of the country")
    private String population;

    @Schema(description = "HDI of the country")
    private String hdi;

    @Schema(description = "GDP of the country")
    private String gdp;

    @Schema(description = "Cropland Footprint of the country")
    private String croplandFootprint;

    @Schema(description = "Grazing Footprint of the country")
    private String grazing;

    @Schema(description = "Forest Footprint of the country")
    private String forest;

    @Schema(description = "Carbon Footprint of the country")
    private String carbon;

    @Schema(description = "Fish Footprint of the country")
    private String fish;

    @Schema(description = "Total Ecological Footprint of the country")
    private String totalEcological;

    @Schema(description = "Cropland of the country")
    private String cropland;

    @Schema(description = "Grazing Land of the country")
    private String grazingLand;

    @Schema(description = "Forest Land of the country")
    private String forestLand;

    @Schema(description = "Fishing Water of the country")
    private String fishing;

    @Schema(description = "Urban Land of the country")
    private String urbanLand;

    @Schema(description = "Total Biocapacity of the country")
    private String totalBiocapacity;

    @Schema(description = "Biocapacity Deficit or Reserve of the country")
    private String biocapacityDeficit;

    @Schema(description = "Earths Required of the country")
    private String earthRequired;

    @Schema(description = "Countries Required of the country")
    private String countriesRequired;

    @Schema(description = "Data Quality of the country")
    private String dataQuality;

}
