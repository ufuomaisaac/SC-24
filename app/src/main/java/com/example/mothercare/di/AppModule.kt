package com.example.mothercare.di

import com.example.mothercare.BuildConfig
import com.example.mothercare.MyApp
import com.google.ai.client.generativeai.GenerativeModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFireBaseAuth(): FirebaseAuth =
        Firebase.auth


    /*@Provides
    //@Singleton
    fun summarizeGenerativeModel(): GenerativeModel =
    // Initialize a GenerativeModel with the `gemini-pro` AI model
        // for text generation
        GenerativeModel(
        modelName = "gemini-1.0-pro",
        apiKey = BuildConfig.apikey,
        generationConfig = MyApp.config
    )*/

    @Provides
    //@Singleton
    fun chatGenerativeModel(): GenerativeModel =
        // Initialize a GenerativeModel with the `gemini-pro` AI model for chat
        GenerativeModel(
            modelName = "gemini-1.0-pro",
            apiKey = BuildConfig.apikey,
            generationConfig = MyApp.config
        )


   // @Provides
   // @Singleton
   /* fun PhotoReasoningGenerativeModel(): GenerativeModel =
    // Initialize a GenerativeModel with the `gemini-pro-vision` AI model
        // for multimodal text generation
        GenerativeModel(
            modelName = "gemini-1.0-pro",
            apiKey = BuildConfig.apikey,
            generationConfig = MyApp.config
        )*/


}