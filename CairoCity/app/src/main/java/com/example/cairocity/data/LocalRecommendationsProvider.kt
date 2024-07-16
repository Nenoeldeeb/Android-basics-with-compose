package com.example.cairocity.data

import com.example.cairocity.R
import com.example.cairocity.model.Recommendation
import com.example.cairocity.utils.RecommendationCategory

object LocalRecommendationsProvider {
	
	private val recommendationsData = listOf(
		Recommendation(
			category = RecommendationCategory.CoffeeShops,
			nameResourceId = R.string.coffee_shop_name_1,
			addressResourceId = R.string.coffee_shop_address_1,
			descriptionResourceId = R.string.coffee_shop_description_1,
			imageResourceId = R.drawable.coffee_shop_1,
			thumbnailResourceId = R.drawable.coffee_shop_1_thumb
		),
		Recommendation(
			category = RecommendationCategory.CoffeeShops,
			nameResourceId = R.string.coffee_shop_name_2,
			addressResourceId = R.string.coffee_shop_address_2,
			descriptionResourceId = R.string.coffee_shop_description_2,
			imageResourceId = R.drawable.coffee_shop_2,
			thumbnailResourceId = R.drawable.coffee_shop_2_thumb
		),
		Recommendation(
			category = RecommendationCategory.CoffeeShops,
			nameResourceId = R.string.coffee_shop_name_3,
			addressResourceId = R.string.coffee_shop_address_3,
			descriptionResourceId = R.string.coffee_shop_description_3,
			imageResourceId = R.drawable.coffee_shop_3,
			thumbnailResourceId = R.drawable.coffee_shop_3_thumb
		),
		Recommendation(
			category = RecommendationCategory.CoffeeShops,
			nameResourceId = R.string.coffee_shop_name_4,
			addressResourceId = R.string.coffee_shop_address_4,
			descriptionResourceId = R.string.coffee_shop_description_4,
			imageResourceId = R.drawable.coffee_shop_4,
			thumbnailResourceId = R.drawable.coffee_shop_4_thumb
		),
		Recommendation(
			category = RecommendationCategory.CoffeeShops,
			nameResourceId = R.string.coffee_shop_name_5,
			addressResourceId = R.string.coffee_shop_address_5,
			descriptionResourceId = R.string.coffee_shop_description_5,
			imageResourceId = R.drawable.coffee_shop_5,
			thumbnailResourceId = R.drawable.coffee_shop_5_thumb
		),
		Recommendation(
			category = RecommendationCategory.Restaurants,
			nameResourceId = R.string.restaurant_name_1,
			addressResourceId = R.string.restaurant_address_1,
			descriptionResourceId = R.string.restaurant_description_1,
			imageResourceId = R.drawable.restaurant_1,
			thumbnailResourceId = R.drawable.restaurant_1_thumb
		),
		Recommendation(
			category = RecommendationCategory.Restaurants,
			nameResourceId = R.string.restaurant_name_2,
			addressResourceId = R.string.restaurant_address_2,
			descriptionResourceId = R.string.restaurant_description_2,
			imageResourceId = R.drawable.restaurant_2,
			thumbnailResourceId = R.drawable.restaurant_2_thumb
		),
		Recommendation(
			category = RecommendationCategory.Restaurants,
			nameResourceId = R.string.restaurant_name_3,
			addressResourceId = R.string.restaurant_address_3,
			descriptionResourceId = R.string.restaurant_description_3,
			imageResourceId = R.drawable.restaurant_3,
			thumbnailResourceId = R.drawable.restaurant_3_thumb
		),
		Recommendation(
			category = RecommendationCategory.Restaurants,
			nameResourceId = R.string.restaurant_name_4,
			addressResourceId = R.string.restaurant_address_4,
			descriptionResourceId = R.string.restaurant_description_4,
			imageResourceId = R.drawable.restaurant_4,
			thumbnailResourceId = R.drawable.restaurant_4_thumb
		),
		Recommendation(
			category = RecommendationCategory.Restaurants,
			nameResourceId = R.string.restaurant_name_5,
			addressResourceId = R.string.restaurant_address_5,
			descriptionResourceId = R.string.restaurant_description_5,
			imageResourceId = R.drawable.restaurant_5,
			thumbnailResourceId = R.drawable.restaurant_5_thumb
		),
		Recommendation(
			category = RecommendationCategory.KidsAreas,
			nameResourceId = R.string.kids_area_name_1,
			addressResourceId = R.string.kids_area_address_1,
			descriptionResourceId = R.string.kids_area_description_1,
			imageResourceId = R.drawable.kids_area_1,
			thumbnailResourceId = R.drawable.kids_area_1_thumb
		),
		Recommendation(
			category = RecommendationCategory.KidsAreas,
			nameResourceId = R.string.kids_area_name_2,
			addressResourceId = R.string.kids_area_address_2,
			descriptionResourceId = R.string.kids_area_description_2,
			imageResourceId = R.drawable.kids_area_2,
			thumbnailResourceId = R.drawable.kids_area_2_thumb
		),
		Recommendation(
			category = RecommendationCategory.KidsAreas,
			nameResourceId = R.string.kids_area_name_3,
			addressResourceId = R.string.kids_area_address_3,
			descriptionResourceId = R.string.kids_area_description_3,
			imageResourceId = R.drawable.kids_area_3,
			thumbnailResourceId = R.drawable.kids_area_3_thumb
		),
		Recommendation(
			category = RecommendationCategory.KidsAreas,
			nameResourceId = R.string.kids_area_name_4,
			addressResourceId = R.string.kids_area_address_4,
			descriptionResourceId = R.string.kids_area_description_4,
			imageResourceId = R.drawable.kids_area_4,
			thumbnailResourceId = R.drawable.kids_area_4_thumb
		),
		Recommendation(
			category = RecommendationCategory.KidsAreas,
			nameResourceId = R.string.kids_area_name_5,
			addressResourceId = R.string.kids_area_address_5,
			descriptionResourceId = R.string.kids_area_description_5,
			imageResourceId = R.drawable.kids_area_5,
			thumbnailResourceId = R.drawable.kids_area_5_thumb
		),
		Recommendation(
			category = RecommendationCategory.Parks,
			nameResourceId = R.string.park_name_1,
			addressResourceId = R.string.park_address_1,
			descriptionResourceId = R.string.park_description_1,
			imageResourceId = R.drawable.park_1,
			thumbnailResourceId = R.drawable.park_1_thumb
		),
		Recommendation(
			category = RecommendationCategory.Parks,
			nameResourceId = R.string.park_name_2,
			addressResourceId = R.string.park_address_2,
			descriptionResourceId = R.string.park_description_2,
			imageResourceId = R.drawable.park_2,
			thumbnailResourceId = R.drawable.park_2_thumb
		),
		Recommendation(
			category = RecommendationCategory.Parks,
			nameResourceId = R.string.park_name_3,
			addressResourceId = R.string.park_address_3,
			descriptionResourceId = R.string.park_description_3,
			imageResourceId = R.drawable.park_3,
			thumbnailResourceId = R.drawable.park_3_thumb
		),
		Recommendation(
			category = RecommendationCategory.Parks,
			nameResourceId = R.string.park_name_4,
			addressResourceId = R.string.park_address_4,
			descriptionResourceId = R.string.park_description_4,
			imageResourceId = R.drawable.park_4,
			thumbnailResourceId = R.drawable.park_4_thumb
		),
		Recommendation(
			category = RecommendationCategory.Parks,
			nameResourceId = R.string.park_name_5,
			addressResourceId = R.string.park_address_5,
			descriptionResourceId = R.string.park_description_5,
			imageResourceId = R.drawable.park_5,
			thumbnailResourceId = R.drawable.park_5_thumb
		),
		Recommendation(
			category = RecommendationCategory.ShoppingCenters,
			nameResourceId = R.string.shopping_center_name_1,
			addressResourceId = R.string.shopping_center_address_1,
			descriptionResourceId = R.string.shopping_center_description_1,
			imageResourceId = R.drawable.shopping_center_1,
			thumbnailResourceId = R.drawable.shopping_center_1_thumb
		),
		Recommendation(
			category = RecommendationCategory.ShoppingCenters,
			nameResourceId = R.string.shopping_center_name_2,
			addressResourceId = R.string.shopping_center_address_2,
			descriptionResourceId = R.string.shopping_center_description_2,
			imageResourceId = R.drawable.shopping_center_2,
			thumbnailResourceId = R.drawable.shopping_center_2_thumb
		),
		Recommendation(
			category = RecommendationCategory.ShoppingCenters,
			nameResourceId = R.string.shopping_center_name_3,
			addressResourceId = R.string.shopping_center_address_3,
			descriptionResourceId = R.string.shopping_center_description_3,
			imageResourceId = R.drawable.shopping_center_3,
			thumbnailResourceId = R.drawable.shopping_center_3_thumb
		),
		Recommendation(
			category = RecommendationCategory.ShoppingCenters,
			nameResourceId = R.string.shopping_center_name_4,
			addressResourceId = R.string.shopping_center_address_4,
			descriptionResourceId = R.string.shopping_center_description_4,
			imageResourceId = R.drawable.shopping_center_4,
			thumbnailResourceId = R.drawable.shopping_center_4_thumb
		),
		Recommendation(
			category = RecommendationCategory.ShoppingCenters,
			nameResourceId = R.string.shopping_center_name_5,
			addressResourceId = R.string.shopping_center_address_5,
			descriptionResourceId = R.string.shopping_center_description_5,
			imageResourceId = R.drawable.shopping_center_5,
			thumbnailResourceId = R.drawable.shopping_center_5_thumb
		)
	)
	
	fun getRecommendationsByCategory(category: RecommendationCategory) =
		recommendationsData.filter { it.category == category }
	
	fun getDefaultRecommendation() = recommendationsData[0]
}