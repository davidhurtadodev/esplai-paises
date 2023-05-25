package io.nuwe.Hackaton_FundacionEsplai.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComparatorDTO {

    @Schema(description = "Country of the user")
    private CountryDTO userCountry;

    @Schema(description = "Country to compare with")
    private CountryDTO otherCountry;
}
