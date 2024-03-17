package com.bugratasdemir.graduationproject.faker;

import com.bugratasdemir.graduationproject.dto.RecommentDTO;
import com.bugratasdemir.graduationproject.entity.User;
import com.bugratasdemir.graduationproject.enums.GenderState;
import com.bugratasdemir.graduationproject.enums.UserState;
import com.bugratasdemir.graduationproject.request.SuggestedRequest;
import com.bugratasdemir.graduationproject.request.UserSaveRequest;
import com.bugratasdemir.graduationproject.request.UserUpdateRequest;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class UserFaker {

    private final Faker faker;
    public User createUser() {
        User user = new User();
        user.setId(generateRandomId());
        user.setName(generateRandomUsername());
        user.setSurname(generateRandomUserSurname());
        user.setEmail(generateRandomUserEmail());
        user.setBirthDate(generateRandomDateOfBirth());
        user.setGender(GenderState.MALE);
        user.setStatus(UserState.ACTIVE);
        user.setLatitude(generateRandomLatitudeAndLongitude());
        user.setLongitude(generateRandomLatitudeAndLongitude());
        return user;
    }
    public UserSaveRequest createUserSaveRequest(){
        return UserSaveRequest.builder()
                .email(generateRandomUserEmail())
                .gender(GenderState.MALE)
                .status(UserState.ACTIVE)
                .surname(generateRandomUserSurname())
                .name(generateRandomUsername())
                .birthDate(generateRandomDateOfBirth())
                .latitude(generateRandomLatitudeAndLongitude())
                .longitude(generateRandomLatitudeAndLongitude()).build();


    }
    public UserUpdateRequest createUserUpdateRequest(User user){
        return UserUpdateRequest.builder()
                .id(user.getId())
                .email(generateRandomUserEmail())
                .gender(GenderState.MALE)
                .status(UserState.ACTIVE)
                .surname(generateRandomUserSurname())
                .name(generateRandomUsername())
                .birthDate(generateRandomDateOfBirth())
                .latitude(generateRandomLatitudeAndLongitude())
                .longitude(generateRandomLatitudeAndLongitude()).build();
    }
    public SuggestedRequest createSuggestedRequest(User user){
         return SuggestedRequest.builder()
                .restaurantRateAndId(generateRestaurantRateAndId(3))
                .longitude(user.getLongitude())
                .latitude(user.getLatitude()).build();

    }
    public List<Object[]> generateRestaurantRateAndId(int count) {
        List<Object[]> restaurantRateAndIdList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Object[] restaurantRateAndId = new Object[2];
            restaurantRateAndId[0] = generateRandomId();
            restaurantRateAndId[1] = faker.number().randomDouble(1, 1, 5);
            restaurantRateAndIdList.add(restaurantRateAndId);
        }
        return restaurantRateAndIdList;
    }
    public List<RecommentDTO> generateRecommentDTOList(int count) {
        List<RecommentDTO> recommentDTOList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            RecommentDTO recommentDTO = RecommentDTO.builder()
                    .id("1")
                    .name(faker.company().name())
                    .latitude(generateRandomLatitudeAndLongitude())
                    .longitude(generateRandomLatitudeAndLongitude())
                    .rate(faker.number().randomDouble(1, 1, 5))
                    .build();
            recommentDTOList.add(recommentDTO);
        }
        return recommentDTOList;
    }
    private Long generateRandomId() {
        return faker.number().randomNumber();
    }
    private Double generateRandomLatitudeAndLongitude() {
        return faker.number().randomDouble(1,2,3);
    }
    private String generateRandomUsername() {
        return faker.name().firstName();
    }
    private String generateRandomUserSurname(){
        return faker.name().lastName();
    }
    private String generateRandomUserEmail(){
        return faker.internet().safeEmailAddress();
    }
    public LocalDate generateRandomDateOfBirth() {
        Date date = faker.date().birthday();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
