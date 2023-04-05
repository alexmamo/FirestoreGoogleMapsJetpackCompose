package ro.alexmamo.firestoregooglemapsjetpackcompose.presentation.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.alexmamo.firestoregooglemapsjetpackcompose.domain.model.Response.Loading
import ro.alexmamo.firestoregooglemapsjetpackcompose.domain.model.Response.Success
import ro.alexmamo.firestoregooglemapsjetpackcompose.domain.repository.AddLocationResponse
import ro.alexmamo.firestoregooglemapsjetpackcompose.domain.repository.DeleteLocationResponse
import ro.alexmamo.firestoregooglemapsjetpackcompose.domain.repository.LocationsRepository
import ro.alexmamo.firestoregooglemapsjetpackcompose.domain.repository.LocationsResponse
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repo: LocationsRepository
): ViewModel() {
    var locationsResponse by mutableStateOf<LocationsResponse>(Loading)
        private set
    var addLocationResponse by mutableStateOf<AddLocationResponse>(Success(false))
        private set
    var deleteLocationResponse by mutableStateOf<DeleteLocationResponse>(Success(false))
        private set

    init {
        getLocations()
    }

    private fun getLocations() = viewModelScope.launch {
        repo.getLocationsFromFirestore().collect { response ->
            locationsResponse = response
        }
    }

    fun addLocation(lat: Double, lng: Double) = viewModelScope.launch {
        addLocationResponse = Loading
        addLocationResponse = repo.addLocationToFirestore(lat, lng)
    }

    fun deleteLocation(locationId: String) = viewModelScope.launch {
        deleteLocationResponse = Loading
        deleteLocationResponse = repo.deleteLocationFromFirestore(locationId)
    }
}