package ro.alexmamo.firestoregooglemapsjetpackcompose.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ro.alexmamo.firestoregooglemapsjetpackcompose.core.Constants.LOCATIONS
import ro.alexmamo.firestoregooglemapsjetpackcompose.data.repository.LocationsRepositoryImpl
import ro.alexmamo.firestoregooglemapsjetpackcompose.domain.repository.LocationsRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideLocationsRef() = Firebase.firestore.collection(LOCATIONS)

    @Provides
    fun provideLocationsRepository(
        locationsRef: CollectionReference
    ): LocationsRepository = LocationsRepositoryImpl(locationsRef)
}