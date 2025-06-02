package com.foodify.foodify.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.foodify.foodify.Entity.FoodEntity;
import com.foodify.foodify.IO.FoodRequest;
import com.foodify.foodify.IO.FoodResponse;
import com.foodify.foodify.Repository.FoodRepository;
import com.foodify.foodify.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private FoodRepository foodRepository;
    @Override
    public String upload(MultipartFile file){
        try {
            return this.cloudinary.uploader().upload(file.getBytes(),
                            Map.of("public_id", UUID.randomUUID().toString()))
                    .get("url")
                    .toString();
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed!!");
        }
    }

    @Override
    public FoodResponse addFood(FoodRequest request, MultipartFile file) {
        FoodEntity newFoodEntity=convertToEntity(request);
        String imageUrl=upload(file);
        newFoodEntity.setImageUrl(imageUrl);
        newFoodEntity=foodRepository.save(newFoodEntity);
        return convertToResponse(newFoodEntity);
    }

    @Override
    public List<FoodResponse> readFoods() {
        List<FoodEntity> foodList=foodRepository.findAll();
        return foodList.stream().map(object -> convertToResponse(object)).collect(Collectors.toList());
    }

    @Override
    public FoodResponse readFood(String id) {
        FoodEntity exisitingFood=foodRepository.findById(id).orElseThrow(()->new RuntimeException("Food not found!!!"));
        return convertToResponse(exisitingFood);
    }

    @Override
    public boolean deleteFood(String foodId) {
        FoodEntity food = foodRepository.findById(foodId).orElseThrow(() -> new RuntimeException("Food not found"));

        // Extract public_id from image URL
        String imageUrl = food.getImageUrl();
        String publicId = extractPublicIdFromUrl(imageUrl);

        // Delete image from Cloudinary
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image from Cloudinary", e);
        }

        // Delete food from DB
        foodRepository.deleteById(foodId);

        return true;
    }

    private String extractPublicIdFromUrl(String imageUrl) {
        try {
            // Remove base Cloudinary URL
            String[] parts = imageUrl.split("/");
            StringBuilder publicId = new StringBuilder();

            // Extract from /upload/ to the end, then remove the extension
            int uploadIndex = Arrays.asList(parts).indexOf("upload");
            for (int i = uploadIndex + 1; i < parts.length; i++) {
                publicId.append(parts[i]);
                if (i != parts.length - 1) publicId.append("/");
            }

            // Remove extension
            int dotIndex = publicId.lastIndexOf(".");
            if (dotIndex != -1) {
                publicId = new StringBuilder(publicId.substring(0, dotIndex));
            }

            return publicId.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract publicId from imageUrl", e);
        }
    }

    private FoodEntity convertToEntity(FoodRequest request){
        return FoodEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice()).build();
    }
    private FoodResponse convertToResponse(FoodEntity entity){
        return FoodResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .imageUrl(entity.getImageUrl()).build();
    }
}
