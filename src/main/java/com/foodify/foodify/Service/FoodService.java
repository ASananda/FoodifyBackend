package com.foodify.foodify.Service;

import com.foodify.foodify.IO.FoodRequest;
import com.foodify.foodify.IO.FoodResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodService {
    String upload(MultipartFile file);

    FoodResponse addFood(FoodRequest request, MultipartFile file);

    List<FoodResponse> readFoods();

    FoodResponse readFood(String id);

    boolean deleteFood(String id);

}
