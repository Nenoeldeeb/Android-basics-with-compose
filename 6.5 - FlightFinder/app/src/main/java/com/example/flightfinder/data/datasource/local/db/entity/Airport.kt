package com.example.flightfinder.data.datasource.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
	@PrimaryKey val id: Int,
	@ColumnInfo(name = "name") val airportName: String,
	@ColumnInfo(name = "iata_code") val airportCode: String,
	@ColumnInfo(name = "passengers") val passengers: Int,
)