/*
 * Copyright (c) 2015 Ushahidi Inc
 *
 * This program is free software: you can redistribute it and/or modify it under
 *  the terms of the GNU Affero General Public License as published by the Free
 *  Software Foundation, either version 3 of the License, or (at your option)
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT
 *  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 *  FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program in the file LICENSE-AGPL. If not, see
 *  https://www.gnu.org/licenses/agpl-3.0.html
 */

package com.ushahidi.android.data.repository.datasource.post;

import com.ushahidi.android.data.api.PostApi;
import com.ushahidi.android.data.database.PostDatabaseHelper;
import com.ushahidi.android.data.entity.PostEntity;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Data source for manipulating {@link com.ushahidi.android.data.entity.PostEntity} data to and
 * from
 * the API.
 *
 * @author Ushahidi Team <team@ushahidi.com>
 */
public class PostApiDataSource implements PostDataSource {

    PostApi mPostApi;

    PostDatabaseHelper mPostDatabaseHelper;

    public PostApiDataSource(@NonNull PostApi postApi, @NonNull PostDatabaseHelper
            postDatabaseHelper) {
        mPostApi = postApi;
        mPostDatabaseHelper = postDatabaseHelper;
    }

    @Override
    public Observable<Long> putPostEntity(List<PostEntity> postEntity) {
        // Do nothing. Not posting via the API ATM
        return null;
    }

    @Override
    public Observable<List<PostEntity>> getPostEntityList(Long deploymentId) {
        mPostDatabaseHelper.getPostList(deploymentId)
                .doOnNext(postEntities -> mPostDatabaseHelper
                        .putPosts(setDeploymentId(postEntities, deploymentId)));
        return null;
    }

    @Override
    public Observable<PostEntity> getPostEntityById(Long deploymentId, Long postEntityId) {
        // Do nothing. Not getting post by Id via the API ATM
        return null;
    }

    @Override
    public Observable<Boolean> deletePostEntity(PostEntity postEntity) {
        // Do nothing. Not deleting via the API ATM
        return null;
    }

    @Override
    public Observable<List<PostEntity>> search(Long deploymentId, String query) {
        // Do nothing. Not searching via the API ATM
        return null;
    }

    /**
     * Set the deployment ID for the TagModel since it's not set by the
     * API
     *
     * @param postEntities The TagModel to set the deployment Id on
     * @param deploymentId The ID of the deployment to set
     * @return observable
     */
    private List<PostEntity> setDeploymentId(List<PostEntity> postEntities,
            Long deploymentId) {
        List<PostEntity> postEntityList = new ArrayList<>(postEntities.size());
        for (PostEntity postEntity : postEntities) {
            postEntity.setDeploymentId(deploymentId);
            postEntityList.add(postEntity);
        }
        return postEntityList;
    }
}
