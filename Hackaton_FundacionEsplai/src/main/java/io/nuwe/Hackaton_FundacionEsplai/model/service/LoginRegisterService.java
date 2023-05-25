package io.nuwe.Hackaton_FundacionEsplai.model.service;

import io.nuwe.Hackaton_FundacionEsplai.model.domain.Comparator;
import io.nuwe.Hackaton_FundacionEsplai.model.domain.CountryC;
import io.nuwe.Hackaton_FundacionEsplai.model.domain.Role;
import io.nuwe.Hackaton_FundacionEsplai.model.domain.User;
import io.nuwe.Hackaton_FundacionEsplai.model.dto.*;
import io.nuwe.Hackaton_FundacionEsplai.model.exception.EmailDuplicatedException;
import io.nuwe.Hackaton_FundacionEsplai.model.exception.PasswordIncorrectException;
import io.nuwe.Hackaton_FundacionEsplai.model.exception.UserDuplicatedException;
import io.nuwe.Hackaton_FundacionEsplai.model.exception.UserNotFoundException;
import io.nuwe.Hackaton_FundacionEsplai.model.repository.ICountryRepository;
import io.nuwe.Hackaton_FundacionEsplai.model.repository.IUserRepository;
import io.nuwe.Hackaton_FundacionEsplai.security.JwtGenerator;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginRegisterService {

    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final ICountryRepository countryRepository;

    @Autowired
    public LoginRegisterService(ModelMapper modelMapper, AuthenticationManager authenticationManager, IUserRepository userRepository, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator, ICountryRepository countryRepository) {
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.countryRepository = countryRepository;
    }

    /**
     * Method to create a user register in the database. It is used in the register() method of the LoginRegisterController.
     *
     * @param registerDTO
     * @return RegisterUserDTO
     */
    public RegisterUserDTO createRegister(RegisterDTO registerDTO) {

        RegisterDTO registerEmailValidated = validateRegisterEmail(registerDTO);
        RegisterDTO registerEmailAndNameValidated = validateRegisterName(registerEmailValidated);

        User user = new User(registerEmailAndNameValidated.getName(), registerEmailAndNameValidated.getEmail(), passwordEncoder.encode(registerEmailAndNameValidated.getPassword()));

        assignRoleToUser(user);
        userRepository.save(user);

        user.setCountryC(new CountryC(registerDTO.getCountry(), "Middle East/Central Asia", "29.82", "0.46", "$614.66", "0.3",
                "0.2", "0.08", "0.18", "0", "0.79", "0.24", "0.2", "0.02", "0", "0.04", "0.5", "-0.3", "0.46", "1.6", "6"));

        return convertUserToDTO(user);
    }

    /**
     * Method to authenticate the user. It is used in the login() method of the LoginRegisterController.
     *
     * @param loginDTO
     * @return AuthenticationResponseDTO
     */
    public AuthenticationResponseDTO authenticateLogin(LoginDTO loginDTO) {
        LoginDTO loginDTOValidated = validateLoginEmailAndPassword(loginDTO);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTOValidated.getEmail(), loginDTOValidated.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        if (!jwtGenerator.isTokenValid(token)) {
            throw new AuthenticationCredentialsNotFoundException("Token generated is not valid");
        }
        return new AuthenticationResponseDTO(loginDTOValidated.getEmail(), passwordEncoder.encode(loginDTOValidated.getPassword()), token);
    }

    /**
     * Method to retrieve all users stored in the database. It is used in the users() method of the LoginRegisterController.
     *
     * @return List<RegisterUserDTO>
     */
    public List<RegisterUserDTO2> getAllUsers() {
        if (userRepository.findAll().isEmpty()) {
            throw new UserNotFoundException("There are no users introduced in the database");
        } else {
            List<User> usersFromDB = userRepository.findAll();

            return convertUserListToDTO(usersFromDB);
        }
    }

    /**
     * Method to retrieve the country of the user. It is used in the countryUser() method of the LoginRegisterController.
     *
     * @param id
     * @return CountryDTO
     */
    public CountryDTO getUserCountry(ObjectId id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found in the database"));
        user.setCountryC(new CountryC("Afghanistan", "Middle East/Central Asia", "29.82", "0.46", "$614.66", "0.3",
                "0.2", "0.08", "0.18", "0", "0.79", "0.24", "0.2", "0.02", "0", "0.04", "0.5", "-0.3", "0.46", "1.6", "6"));

        return modelMapper.map(user.getCountryC(), CountryDTO.class);
    }

    /**
     * Method to compare the country of the user with another country stored in the database. It is used in the countriesComparator() method of the LoginRegisterController.
     *
     * @param id
     * @param idOtherCountry
     * @return ComparatorDTO
     */
    public ComparatorDTO compareCountries(ObjectId id, ObjectId idOtherCountry) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found in the database"));
        CountryC countryC = countryRepository.findById(idOtherCountry).orElseThrow(() -> new IllegalArgumentException("Sorry, the country is not registered in our database. Try again please"));
        Comparator comparator = new Comparator(user.getCountryC(), countryC);

        return modelMapper.map(comparator, ComparatorDTO.class);
    }

    /**
     * Internal method to check if the email of the User to be registered is unique, so that it is not repeated in the database.
     * Used in the createRegister() method.
     */
    private RegisterDTO validateRegisterEmail(RegisterDTO registerDTO) {

        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new EmailDuplicatedException("Email introduced already exists");
        } else {
            return registerDTO;
        }
    }

    /**
     * Internal method to check if the name of the User to be registered is unique, so that it is not repeated in the database.
     * Used in the createRegister() method.
     */
    private RegisterDTO validateRegisterName(RegisterDTO registerDTO) {

        if (userRepository.existsByName(registerDTO.getName())) {
            throw new UserDuplicatedException("User's name must be unique");
        } else {
            return registerDTO;
        }
    }

    /**
     * Internal method to assign the role "USER" to a user. Used in the createRegister() method.
     */
    private void assignRoleToUser(User user) {

        Role role;
        Optional<User> userRole = userRepository.findByRole("USER");

        if (userRole.isPresent()) {
            role = userRole.get().getRole();
        } else {
            role = new Role("USER");
        }
        user.setRole(role);
    }

    /**
     * Internal method to validate the email and password in the database. It is used in the authenticateLogin() method.
     */
    private LoginDTO validateLoginEmailAndPassword(LoginDTO loginDTO) {

        RegisterUserDTO registerUserDTO = findEmailInDB(loginDTO);
        String encodedPassword = registerUserDTO.getPassword();
        String password = loginDTO.getPassword();

        if (passwordEncoder.matches(password, encodedPassword)) {
            return loginDTO;
        } else {
            throw new PasswordIncorrectException("Password incorrect, please try again");
        }
    }

    /**
     * Internal method to find a user by its email in the database. Used in the validateLoginEmailAndPassword() method.
     */
    private RegisterUserDTO findEmailInDB(LoginDTO loginDTO) {

        User user;
        Optional<User> emailFromDB = userRepository.findByEmail(loginDTO.getEmail());

        if (emailFromDB.isPresent()) {
            user = emailFromDB.get();
        } else {
            throw new UserNotFoundException("User's email not found or incorrect");
        }

        return convertUserToDTO(user);
    }

    /**
     * Internal method for converting a user to a DTO. Used in the createRegister() and findEmailInDB methods.
     */
    private RegisterUserDTO convertUserToDTO(User user) {
        return modelMapper.map(user, RegisterUserDTO.class);
    }

    private RegisterUserDTO2 convertUserToDTO2(User user) {
        return modelMapper.map(user, RegisterUserDTO2.class);
    }

    /**
     * Internal method for converting all users to a DTO list. Used in the createRegister() method.
     */
    private List<RegisterUserDTO2> convertUserListToDTO(List<User> users) {
        return users.stream().map(this::convertUserToDTO2).collect(Collectors.toList());
    }

    /**
     * Method to read the CSV File with all the countries data.
     */
    public void importCountriesFromCSV() {
        String csvFilePath = "src\\countries.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                String country = fields[3];
                String region = fields[17];
                String population = fields[16];
                String hdi = fields[15];
                String gdp = fields[12];
                String croplandFootprint = fields[5];
                String grazing = fields[13];
                String forest = fields[10];
                String carbon = fields[1];
                String fish = fields[8];
                String totalEcological = fields[19];
                String cropland = fields[4];
                String grazingLand = fields[14];
                String forestLand = fields[11];
                String fishing = fields[9];
                String urbanLand = fields[20];
                String totalBiocapacity = fields[18];
                String biocapacityDeficit = fields[0];
                String earthRequired = fields[7];
                String countriesRequired = fields[2];
                String dataQuality = fields[6];

                CountryC countryReaded = new CountryC(country, region, population, hdi, gdp, croplandFootprint, grazing, forest, carbon, fish,
                        totalEcological, cropland, grazingLand, forestLand, fishing, urbanLand, totalBiocapacity, biocapacityDeficit, earthRequired,
                        countriesRequired, dataQuality);
                countryRepository.save(countryReaded);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

