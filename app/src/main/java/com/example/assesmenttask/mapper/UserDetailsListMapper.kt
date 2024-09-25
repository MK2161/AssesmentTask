package com.example.assesmenttask.mapper

import com.example.assesmenttask.data.model.UserDetails
import com.example.assesmenttask.handler.CustomResponse
import com.example.assesmenttask.handler.ERROR_SERVER
import com.example.assesmenttask.handler.LocalException
import retrofit2.Response

class UserDetailsListMapper {
    companion object {
        fun map(userDetails: Response<ArrayList<UserDetails?>>): CustomResponse<MutableList<UserDetails?>, LocalException> {
            return if (userDetails.isSuccessful && userDetails.code() == 200) {
                CustomResponse.Success(
                        userDetails.body()?.map { userDetail ->
                            UserDetails(
                                login = userDetail?.login,
                                id = userDetail?.id,
                                nodeId =userDetail?.nodeId,
                                avatarUrl =userDetail?.avatarUrl,
                                gravatarId =userDetail?.gravatarId,
                                url =userDetail?.url,
                                htmlUrl = userDetail?.htmlUrl,
                                followersUrl =userDetail?.followersUrl,
                                followingUrl = userDetail?.followingUrl,
                                gistsUrl = userDetail?.gistsUrl,
                                starred_url = userDetail?.starred_url,
                                subscriptionUrl = userDetail?.subscriptionUrl,
                                organizationsUrl = userDetail?.organizationsUrl,
                                reposUrl = userDetail?.reposUrl,
                                eventsUrl = userDetail?.eventsUrl,
                                receivedEventsUrl = userDetail?.receivedEventsUrl,
                                type = userDetail?.type,
                                siteAdmin = userDetail?.siteAdmin
                            )
                        }?.toMutableList() ?: mutableListOf()
                )
            } else CustomResponse.Failure(LocalException(ERROR_SERVER))
        }
    }
}