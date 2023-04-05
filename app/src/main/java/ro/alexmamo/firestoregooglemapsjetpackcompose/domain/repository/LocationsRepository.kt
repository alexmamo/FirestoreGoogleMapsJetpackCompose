package ro.alexmamo.firestoregooglemapsjetpackcompose.domain.repository

import kotlinx.coroutines.flow.Flow
import ro.alexmamo.firestoregooglemapsjetpackcompose.domain.model.Location
import ro.alexmamo.firestoregooglemapsjetpackcompose.domain.model.Response

typealias Locations = List<Location>
typealias LocationsResponse = Response<Locations>
typealias AddLocationResponse = Response<Boolean>
typealias DeleteLocationResponse = Response<Boolean>

interface LocationsRepository {
    fun getLocationsFromFirestore(): Flow<LocationsResponse>

    suspend fun addLocationToFirestore(lat: Double, lng: Double): AddLocationResponse

    suspend fun deleteLocationFromFirestore(locationId: String): DeleteLocationResponse
}