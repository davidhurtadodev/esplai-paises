package io.nuwe.Hackaton_FundacionEsplai.model.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comparator {

    @Field(name = "userCountry")
    @Schema(description = "Country of the user")
    private CountryC userCountryC;

    @Field(name = "otherCountry")
    @Schema(description = "Country to compare with")
    private CountryC otherCountryC;

}
