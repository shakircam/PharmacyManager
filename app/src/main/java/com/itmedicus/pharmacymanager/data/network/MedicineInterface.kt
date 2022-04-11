package com.itmedicus.pharmacymanager.data.network

import com.itmedicus.pharmacymanager.model.response.ReceiveGeneric
import com.itmedicus.pharmacymanager.model.credential.*
import com.itmedicus.pharmacymanager.model.response.LoginResponse
import com.itmedicus.pharmacymanager.model.response.ReceiveBrand
import com.itmedicus.pharmacymanager.model.response.ReceiveCompany
import com.itmedicus.pharmacymanager.model.response.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface MedicineInterface {


    @POST("api/register")
    suspend fun registration(@Body userCredential: UserCredential, @Header("P-Auth-Token") token:String): Response<RegistrationResponse>

    @POST("api/login")
    suspend fun login(@Body loginCredential: LoginCredential, @Header("P-Auth-Token") token:String): Response<LoginResponse>

    @POST("api/company")
    suspend fun getCompany(@Body companyCredential: CompanyCredential, @Header("X-Auth-Token") token: String): Response<ReceiveCompany>

    @POST("api/generic")
    suspend fun getGenerics(@Body companyBody: GenericCredential, @Header("X-Auth-Token") token: String): Response<ReceiveGeneric>

    @POST("api/brand")
    suspend fun getBrands(@Body companyBody: BrandCredential, @Header("X-Auth-Token") token: String): Response<ReceiveBrand>
}