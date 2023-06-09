package io.nuwe.Hackaton_FundacionEsplai.controller;

import io.nuwe.Hackaton_FundacionEsplai.model.dto.*;
import io.nuwe.Hackaton_FundacionEsplai.model.exception.EmailDuplicatedException;
import io.nuwe.Hackaton_FundacionEsplai.model.exception.PasswordIncorrectException;
import io.nuwe.Hackaton_FundacionEsplai.model.exception.UserDuplicatedException;
import io.nuwe.Hackaton_FundacionEsplai.model.exception.UserNotFoundException;
import io.nuwe.Hackaton_FundacionEsplai.model.service.LoginRegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Environment API", description = "API operations pertaining to Environment Server")
public class EnvironmentController {

    private final LoginRegisterService loginRegisterService;

    @Autowired
    public EnvironmentController(LoginRegisterService loginRegisterService) {
        this.loginRegisterService = loginRegisterService;
    }

    @PostMapping(value = "/register", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Register a user", description = "Registers a new user in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = RegisterUserDTO.class))}),
            @ApiResponse(responseCode = "409", description = "User's email not valid", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "409", description = "User's name not valid", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "400", description = "Sorry, the country is not registered in our database. Try again please", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while registering the user", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<RegisterUserDTO> register(@RequestBody RegisterDTO registerDTO) throws Exception {

        try {
            loginRegisterService.importCountriesFromCSV();
            return new ResponseEntity<>(loginRegisterService.createRegister(registerDTO), HttpStatus.CREATED);
        } catch (EmailDuplicatedException | UserDuplicatedException | IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while registering the user", e.getCause());
        }
    }

    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Login a user", description = "Login a user in the application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuthenticationResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "User's email not found", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "401", description = "User's password incorrect", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "404", description = "User's token not valid", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while logging in the user", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody LoginDTO loginDTO) throws Exception {

        try {
            return new ResponseEntity<>(loginRegisterService.authenticateLogin(loginDTO), HttpStatus.OK);
        } catch (UserNotFoundException | PasswordIncorrectException | AuthenticationCredentialsNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while logging in the user", e.getCause());
        }
    }

    @GetMapping(value = "/users", produces = "application/json")
    @Operation(summary = "Get all users", description = "Returns a list with all users stored in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users retrieved successfully", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = RegisterUserDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "There are no users introduced in the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while retrieving the users from the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<List<RegisterUserDTO2>> users() throws Exception {

        try {
            return new ResponseEntity<>(loginRegisterService.getAllUsers(), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while retrieving the users from the database", e.getCause());
        }
    }

    @GetMapping(value = "/users/{id}", produces = "application/json")
    @Operation(summary = "Get user's country", description = "Returns the country of the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country retrieved successfully", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CountryDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "User not found in the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while retrieving the country from the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<CountryDTO> countryUser(@Parameter(description = "The id of the user")@PathVariable ObjectId id) throws Exception {

        try {
            return new ResponseEntity<>(loginRegisterService.getUserCountry(id), HttpStatus.OK);
        } catch(UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while retrieving the country from the database", e.getCause());
        }
    }

    //TODO --> PENDENT
    @GetMapping(value = "/users/{id}/compare/{idOtherCountry}", produces = "application/json")
    @Operation(summary = "Get comparison between two countries", description = "Returns the country of the user and another country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Countries retrieved successfully", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CountryDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "User not found in the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "400", description = "Sorry, the country is not registered in our database. Try again please", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while retrieving the countries from the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<ComparatorDTO> countriesComparator(@Parameter(description = "The id of the user") @PathVariable ObjectId id, @Parameter(description = "The id of the country to compare with") @PathVariable ObjectId idOtherCountry) throws Exception {

        try {
            return new ResponseEntity<>(loginRegisterService.compareCountries(id, idOtherCountry), HttpStatus.OK);
        } catch(UserNotFoundException | IllegalArgumentException e) {
            throw e;
        }  catch (Exception e) {
            throw new Exception("Internal Server Error while retrieving the countries from the database", e.getCause());
        }
    }

}
